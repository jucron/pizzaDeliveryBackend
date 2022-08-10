package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public EntityList<ClientOrder> getOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(orderRepository.findAll());
    }

    @Override
    public EntityList<ClientOrder> getConfirmedOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(
                        orderRepository.findByStatus("confirmed"));
    }

    @Override
    public EntityList<ClientOrder> getAcceptedOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(Stream.of(
                                        orderRepository.findByStatus("accepted"),
                                        orderRepository.findByStatus("baking"),
                                        orderRepository.findByStatus("pizzaReady"),
                                        orderRepository.findByStatus("delivering"),
                                        orderRepository.findByStatus("pizzaDelivered")
                                )
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList())
                );
    }

    @Override
    public EntityList<ClientOrder> getFinishedOrders() {
        return new EntityList<ClientOrder>()
                .withEntityList(
                        orderRepository.findByStatus("finished"));
    }


    @Override
    public void changeOrderStatus(String orderId, String orderStatus) {
        ClientOrder orderToBeUpdated = orderRepository.findById(UUID.fromString(orderId)).get();
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
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
