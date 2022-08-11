package com.pizzadeliverybackend.services;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.domain.EntityList;
import com.pizzadeliverybackend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
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
        List<ClientOrder> repositoryAll = orderRepository.findAll();
        return new EntityList<ClientOrder>()
                .withCount(repositoryAll.size())
                .withEntityList(repositoryAll);
    }

    @Override
    public EntityList<ClientOrder> getConfirmedOrders() {
        List<ClientOrder> repositoryByStatus = orderRepository.findByStatus("confirmed");
        return new EntityList<ClientOrder>()
                .withCount(repositoryByStatus.size())
                .withEntityList(repositoryByStatus);
    }

    @Override
    public EntityList<ClientOrder> getAcceptedOrders() {
        List<ClientOrder> clientOrderList = Stream.of(
                        orderRepository.findByStatus("accepted"),
                        orderRepository.findByStatus("baking"),
                        orderRepository.findByStatus("pizzaReady"),
                        orderRepository.findByStatus("delivering"),
                        orderRepository.findByStatus("pizzaDelivered")
                )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new EntityList<ClientOrder>()
                .withCount(clientOrderList.size())
                .withEntityList(clientOrderList);
    }

    @Override
    public EntityList<ClientOrder> getFinishedOrders() {
        List<ClientOrder> repositoryByStatus = orderRepository.findByStatus("finished");
        return new EntityList<ClientOrder>()
                .withCount(repositoryByStatus.size())
                .withEntityList(repositoryByStatus);
    }

    @Override
    public ClientOrder getOrder(String orderId) {
        return orderRepository.findById(UUID.fromString(orderId)).get();
    }


    @Override
    public void changeOrderStatus(String orderId, String orderStatus) {
        ClientOrder orderToBeUpdated = orderRepository.findById(UUID.fromString(orderId)).get();
        orderToBeUpdated.setStatus(orderStatus);
        orderRepository.save(orderToBeUpdated);
    }

    @Override
    public String createOrder(ClientOrder order) {
        order.setOrderTime(LocalTime.now());
        ClientOrder orderSaved = orderRepository.save(order);
        return orderSaved.getId().toString();
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(UUID.fromString(orderId));
    }
}
