package com.pizzadeliverybackend.repositories;

import com.pizzadeliverybackend.domain.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ClientOrder, Long> {
    List<ClientOrder> findByStatus(String confirmed);
}
