package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
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
    public void startProcess(String key, String username, ClientOrder order) {
        //Create Order for this username
        ClientOrder orderSaved = orderService.createOrder(order);
        //start process with username, orderId and orderStatus variables
        Map<String, Object> variables = Map
                .of("username",username,"orderId",order.getId(),"orderStatus",order.getStatus());

        cmmnRuntimeService.createCaseInstanceBuilder()
                .variables(variables).caseDefinitionKey(key).start();
    }

    @Override
    public void completeTask() {

    }

    @Override
    public String getTask(String username) {
        return taskService.createTaskQuery().taskCandidateOrAssigned(username).singleResult().getId();

    }
}
