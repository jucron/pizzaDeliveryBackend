package com.pizzadeliverybackend;

import com.pizzadeliverybackend.domain.ClientOrder;
import com.pizzadeliverybackend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        createExamples(30);
    }
    private void createExamples(int n) {
        List<String> statusList = new ArrayList<>(List.of(
                "confirmed","accepted","baking","pizzaReady","delivering","pizzaDelivered","finished"));
        List<String> flavorList = new ArrayList<>(List.of(
                "Four cheese","Ham & Cheese","Tuna","Pepperoni","Veggie Pizza","Margherita","Hawaiian Pizza"));
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            orderService.createOrder(new ClientOrder()
                    .withClientName("name_"+i)
                    .withAddress("address_"+i)
                    .withPizzaFlavor(flavorList.get(random.nextInt(flavorList.size())))
                    .withStatus(statusList.get(random.nextInt(statusList.size())))
                    .withPaid(random.nextBoolean()));
        }

    }
}
