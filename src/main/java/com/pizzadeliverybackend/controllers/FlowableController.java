package com.pizzadeliverybackend.controllers;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.Response;
import com.pizzadeliverybackend.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flowable/client/")
@RequiredArgsConstructor
@CrossOrigin
public class FlowableController {
    private final OrderService orderService;
    public static int taskCount = 0;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response createOrder(@RequestBody ClientOrder order) {
        //todo: Create Flowable Process
        return new Response()
                .withMessage(orderService.createOrder(order));
    }

    @GetMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    public Response getAccountTask (@PathVariable String username) {
        //todo: find Flowable process by username:
        taskCount = (taskCount>3) ? 1 : taskCount+1;

        return new Response()
                .withMessage("task_"+taskCount);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void completeTask(@RequestBody Object variables) {
        //todo: Complete Task in Flowable Process
    }

}
