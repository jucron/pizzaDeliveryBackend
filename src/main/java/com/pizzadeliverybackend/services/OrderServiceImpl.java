package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public EntityList<ClientOrder> getOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(orderRepository.findAll());
    }

    @Override
    public EntityList<ClientOrder> getActiveOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(
                        orderRepository.findByStatus("confirmed"));
    }

    @Override
    public void changeOrderStatus(Long orderId, String orderStatus) {
        ClientOrder orderToBeUpdated = orderRepository.findById(orderId).get();
        orderToBeUpdated.setStatus(orderStatus);
        orderRepository.save(orderToBeUpdated);
    }

    @Override
    public void createOrder(ClientOrder order) {
        order.setOrderTime(LocalTime.now());
//        order.setId(UUID.randomUUID());
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
