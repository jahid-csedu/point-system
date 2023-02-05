package com.jahid.grpcclient.controller;

import com.google.protobuf.Descriptors;
import com.jahid.grpcclient.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public Map<Descriptors.FieldDescriptor, Object> makePayment(@RequestParam String paymentMethod) {
        return paymentService.makePayment(paymentMethod);
    }
}
