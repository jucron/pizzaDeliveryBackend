package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.OrderHistory;
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

    @Override
    public void startProcess(String caseKey, String username, ClientOrder order) {

        //start process with username, orderId and orderStatus variables
        Map<String, Object> variables = Map
                .of("username",username,"orderId",order.getId(),"orderStatus",order.getStatus());

        cmmnRuntimeService.createCaseInstanceBuilder()
                .variables(variables).caseDefinitionKey(caseKey).start();
    }

    @Override
    public void completeTask(String username, Object object) {
        //get process data related to this username:
        String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals("username",username).singleResult().getId();
        String taskIdFromThisUsername = taskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getId();
        //Execute internal changes differently from each task:
        switch (taskIdFromThisUsername) {
            case "executeOrder": //Create Order for this username
                ClientOrder orderSaved = orderService.createOrder((ClientOrder) object);
                Map<String, Object> processVariables = cmmnRuntimeService.getVariables(caseIdFromThisUsername);
                processVariables.put("orderId",orderSaved.getId());
                cmmnRuntimeService.setVariables(caseIdFromThisUsername,processVariables);
                break;
            case "endFollowUp":
                //nothing to do in this stage
                break;
            case "sendFeedback":
                //register feedback in repo
                orderService.updateHistoryOrder(
                        (String) cmmnRuntimeService.getVariables(caseIdFromThisUsername).get("orderId"),
                        (OrderHistory) object);
                break;
        }
        //Complete task in Flowable:
        taskService.complete(taskIdFromThisUsername);

    }

    @Override
    public Map<String, Object> getProcessData(String username) {
        String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals("username",username).singleResult().getId();
        String taskIdFromThisUsername = taskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getId();
        Map<String, Object> processData = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals("username",username).singleResult().getCaseVariables();
        //Updating status from Repository:

        //Deciding which variables should be sent back
        processData.put("taskId",taskIdFromThisUsername);
        return processData;
    }
}
