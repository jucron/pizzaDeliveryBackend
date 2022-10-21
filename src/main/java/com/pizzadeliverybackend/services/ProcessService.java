package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;

import java.util.Map;

public interface ProcessService {

    void startProcess(String key, String username, ClientOrder order);
    void completeTask(String username, Object object);
    Map<String, Object> getProcessData(String username);
}
