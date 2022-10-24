package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.OrderHistory;
import com.pizzadeliverybackend.model.OrderMinimal;
import com.pizzadeliverybackend.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.cmmn.api.CmmnRuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    private final CmmnRuntimeService cmmnRuntimeService;
    private final TaskService taskService;
    private final OrderService orderService;

    private final String usernameKey = "username";
//    private final String orderStatusKey = "orderStatus";
    private final String orderIdKey = "username";

    @Override
    public void startProcess(String caseKey, String username) {
        log.info("startProcess initiated with caseKey: "+caseKey + " and username: "+username);
        //start process with caseKey identifier and username variable
        cmmnRuntimeService.createCaseInstanceBuilder()
                .variables(Map.of(usernameKey,username)).caseDefinitionKey(caseKey).start();
    }

    @Override
    public void completeTask(String username, Object object) {
        //get process data related to this username:
        String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getId();
        String taskIdFromThisUsername = taskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getId();
        //Execute internal changes differently from each task:
        switch (taskIdFromThisUsername) {
            case "executeOrder": //Create Order for this username
                ClientOrder orderSaved = orderService.createOrder((ClientOrder) object);
                Map<String, Object> processVariables = cmmnRuntimeService.getVariables(caseIdFromThisUsername);
                processVariables.put(orderIdKey,orderSaved.getId());
                cmmnRuntimeService.setVariables(caseIdFromThisUsername,processVariables);
                break;
            case "endFollowUp": //no Data to process at this stage
                break;
            case "sendFeedback": //register feedback in repo
                orderService.updateHistoryOrder(
                        (String) cmmnRuntimeService.getVariables(caseIdFromThisUsername).get(orderIdKey),
                        (OrderHistory) object);
                break;
        }
        //Complete task in Flowable:
        taskService.complete(taskIdFromThisUsername);
    }

    @Override
    public Response getTaskId(String username) {
        try {
            String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getId();
            String taskIdFromThisUsername = taskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getId();
            log.info("taskId found: "+taskIdFromThisUsername);
            return new Response().withMessage(taskIdFromThisUsername);
        } catch (Exception e) {
            log.info("no task found yet, returning Response with null message");
            return new Response().withMessage(null);
        }
    }

    @Override
    public Response getOrderStatus(String username) {
        try {
            Map<String, Object> processData = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getCaseVariables();
            return new Response().withMessage(
                    orderService.getOrder(processData.get(orderIdKey).toString()).getStatus()
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public OrderMinimal getOrder(String username) {
        try {
            Map<String, Object> processData = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getCaseVariables();
            return orderService.getOrder(processData.get(orderIdKey).toString());
        } catch (Exception e) {
            return null;
        }
    }
}
