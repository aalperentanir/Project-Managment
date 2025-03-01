package com.aalperen.controller;

import com.aalperen.entity.User;
import com.aalperen.enums.SubType;
import com.aalperen.response.PaymentResponse;
import com.aalperen.service.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Autowired
    private UserService userService;

    @PostMapping("/{subType}")
    public ResponseEntity<PaymentResponse> createPaymentLink(@PathVariable SubType subType,
                                                             @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserProfile(jwt);
        int amount = 5;

        if (subType.equals(SubType.ANNUALLY)){
            amount *=12;
            amount = (int) (amount*0.2);
        }

        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:5173/upgrade_plan/success?subType="+subType)
                .setCancelUrl("http://localhost:5173/upgrade_plan/fail")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) amount*100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Project Managment")
                                        .build())
                                .build()

                        )
                        .build()
                )
                .build();

        Session session = Session.create(params);

        PaymentResponse res = new PaymentResponse();
        res.setPayment_url(session.getUrl());

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
