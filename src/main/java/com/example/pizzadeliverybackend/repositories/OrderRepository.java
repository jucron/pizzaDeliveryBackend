package com.example.pizzadeliverybackend.repositories;

import com.example.pizzadeliverybackend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
