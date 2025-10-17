package com.huttonsy.firstspringboot;

import org.springframework.stereotype.Service;

@Service("paypal")
public class PaypalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount){
        System.out.println("PayPal payment" );
        System.out.println("Amount: " + amount);
    }
}
