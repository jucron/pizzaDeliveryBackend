package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;


public interface OrderService {
    EntityList<ClientOrder> getOrders();

    EntityList<ClientOrder> getActiveOrders();

    void changeOrderStatus(Long orderId, String orderStatus);

    void createOrder(ClientOrder order);

    void deleteOrder(Long orderId);
}
