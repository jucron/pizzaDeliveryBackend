package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.domain.OrderHistory;


public interface OrderService {
    EntityList<ClientOrder> getOrders();

    EntityList<ClientOrder> getConfirmedOrders();

    void changeOrderStatus(String orderId, String orderStatus);

    ClientOrder createOrder(ClientOrder order);

    void deleteOrder(String orderId);

    EntityList<ClientOrder> getAcceptedOrders();

    EntityList<ClientOrder> getFinishedOrders();

    ClientOrder getOrder(String orderId);

    void updateHistoryOrder(String orderId, OrderHistory orderHistory);

    ClientOrder getOrderByUsername(String username);
}
