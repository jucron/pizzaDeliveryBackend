package com.pizzadeliverybackend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String deliveryFeedback;
    private int clientFeedbackScore;
    private String clientFeedbackNotes;
    private LocalTime deliveryTime;


}
