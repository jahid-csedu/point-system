package com.jahid.grpcclient.controller;

import com.example.pointsystem.services.PaymentRequest;
import com.google.protobuf.Descriptors;
import com.jahid.grpcclient.request.MakePaymentRequest;
import com.jahid.grpcclient.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public Map<Descriptors.FieldDescriptor, Object> makePayment(@RequestBody MakePaymentRequest paymentRequest) {
        return paymentService.makePayment(paymentRequest);
    }
}
