package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.domain.Order;

public class OrderServiceImpl implements OrderService {
    @Override
    public EntityList<Order> getOrders() {
        return null;
    }

    @Override
    public EntityList<Order> getActiveOrders() {
        return null;
    }

    @Override
    public void changeOrderStatus(String orderId, String orderStatus) {

    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void deactivateOrder(String orderId) {

    }
}
