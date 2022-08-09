package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.domain.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    EntityList<Order> getOrders();

    EntityList<Order> getActiveOrders();

    void changeOrderStatus(String orderId, String orderStatus);

    void createOrder(Order order);

    void deactivateOrder(String orderId);
}
