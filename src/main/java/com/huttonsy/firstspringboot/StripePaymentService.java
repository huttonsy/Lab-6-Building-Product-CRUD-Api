package com.huttonsy.firstspringboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("stripe")
@Primary
public class StripePaymentService  implements PaymentService {
    @Override
    public void processPayment(double amount){
        System.out.println("Stripe payment" );
        System.out.println("Amount: " + amount);
    }
}
