package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;


public interface OrderService {
    EntityList<ClientOrder> getOrders();

    EntityList<ClientOrder> getConfirmedOrders();

    void changeOrderStatus(String orderId, String orderStatus);

    void createOrder(ClientOrder order);

    void deleteOrder(String orderId);

    EntityList<ClientOrder> getAcceptedOrders();

    EntityList<ClientOrder> getFinishedOrders();
}
