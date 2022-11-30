package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.model.Response;
import org.flowable.cmmn.api.CmmnRuntimeService;
import org.flowable.cmmn.api.CmmnTaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ProcessServiceImplTest {

    private final String usernameKey = "username";
    private final String username = "john";
    private final String orderIdKey = "order";
//    @Autowired
    private ProcessService processService;
    @Autowired
    private CmmnRuntimeService cmmnRuntimeService;
    @Autowired
    private CmmnTaskService cmmnTaskService;
    @Mock
    private OrderService orderService;

    @BeforeAll
    void setUp() {
        String caseKey = "pizzaOrderCase";
        String username = "john";
        processService = new ProcessServiceImpl(cmmnRuntimeService, cmmnTaskService, orderService);
        processService.startProcess(caseKey,username);
    }

    @Test
    void startProcess() {
        String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getId();
        assertNotNull(caseIdFromThisUsername);
    }

    @Test
    void completeTask() {
        //given
        UUID orderId = UUID.randomUUID();
        ClientOrder order = new ClientOrder().withId(orderId);
//        given(orderService.createOrder(any())).willReturn(new ClientOrder().withId(orderId));
        //when
        processService.completeTask(username,order);
        //then
        String caseIdFromThisUsername = cmmnRuntimeService.createCaseInstanceQuery().variableValueEquals(usernameKey,username).singleResult().getId();
        Map<String, Object> processVariables = cmmnRuntimeService.getVariables(caseIdFromThisUsername);

        assertEquals(order.getId().toString(),(processVariables.get(orderIdKey))); //assert order is stored in variables
        String taskIdFromThisUsername = cmmnTaskService.createTaskQuery().caseInstanceId(caseIdFromThisUsername).singleResult().getTaskDefinitionKey();
        assertEquals("endFollowUp",taskIdFromThisUsername);
    }

    @Test
    void getTaskDefKey() {
        Response response = processService.getTaskDefKey(username);
        assertEquals("endFollowUp",response.getMessage());
    }

    @Test
    void getOrderStatus() {
    }

    @Test
    void getOrder() {
    }
}
