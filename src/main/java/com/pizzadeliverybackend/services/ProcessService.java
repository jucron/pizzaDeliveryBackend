package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;

public interface ProcessService {

    void startProcess(String key, String username, ClientOrder order);
    void completeTask();
    String getTask(String username);
}
