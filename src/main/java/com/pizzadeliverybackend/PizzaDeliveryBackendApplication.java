package com.pizzadeliverybackend;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PizzaDeliveryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaDeliveryBackendApplication.class, args);
    }


}
@Component
class Bootstrap implements CommandLineRunner {
    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        orderService.createOrder(new ClientOrder()
                .withClientName("John Doe")
                .withAddress("john's_address")
                .withPizzaFlavor("ham&cheese")
                .withStatus("confirmed")
                .withPaid(true));
        orderService.createOrder(new ClientOrder()
                .withClientName("Mary Doe")
                .withAddress("mary's_address")
                .withPizzaFlavor("tune")
                .withStatus("confirmed")
                .withPaid(true));
        orderService.createOrder(new ClientOrder()
                .withClientName("Larry Doe")
                .withAddress("larry's_address")
                .withPizzaFlavor("mozzarella")
                .withStatus("confirmed")
                .withPaid(false));
    }
}
