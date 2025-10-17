package com.huttonsy.firstspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstSpringBootApplication {

    public static void main(String[] args)
    {
      ApplicationContext context =  SpringApplication.run(FirstSpringBootApplication.class, args);
        var orderService = context.getBean(OrderService.class);
      //var  orderService = new OrderService(new StripePaymentService());
        orderService.placeOrder();
    }

}
