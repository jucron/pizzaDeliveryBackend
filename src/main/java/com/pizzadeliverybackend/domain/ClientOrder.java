package com.pizzadeliverybackend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class ClientOrder {

    @Id
    @GeneratedValue
    private UUID id;

    private String clientName;
    private String pizzaFlavor;
    private String address;
    private String status;
    private LocalTime orderTime;
    private boolean paid;


}
