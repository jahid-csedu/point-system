package com.jahid.grpcclient.service;

import com.example.pointsystem.services.AdditionalItem;
import com.example.pointsystem.services.PaymentRequest;
import com.example.pointsystem.services.PaymentResponse;
import com.example.pointsystem.services.PaymentServiceGrpc;
import com.google.protobuf.Descriptors;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class PaymentService {

    @GrpcClient("payment-client-service")
    private PaymentServiceGrpc.PaymentServiceBlockingStub client;

    public Map<Descriptors.FieldDescriptor, Object> makePayment(String paymentMethod) {
        AdditionalItem additionalItem = AdditionalItem.newBuilder()
                .setLast4("1234")
                .build();
        PaymentRequest paymentRequest = PaymentRequest.newBuilder()
                .setPrice("100.00")
                .setPriceModifier(0.95)
                .setPaymentMethod(paymentMethod)
                .setDatetime(new Date().toString())
                .setAdditionalItem(additionalItem)
                .build();
        PaymentResponse response = client.makePayment(paymentRequest);
        return response.getAllFields();
    }
}
