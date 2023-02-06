package com.jahid.grpcclient.service;

import com.example.pointsystem.services.PaymentResponse;
import com.example.pointsystem.services.PaymentServiceGrpc;
import com.google.protobuf.Descriptors;
import com.jahid.grpcclient.request.MakePaymentRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentService {

    @GrpcClient("payment-client-service")
    private PaymentServiceGrpc.PaymentServiceBlockingStub client;

    public Map<Descriptors.FieldDescriptor, Object> makePayment(MakePaymentRequest paymentRequest) {
        PaymentResponse response = client.makePayment(paymentRequest.toGrpc());
        return response.getAllFields();
    }
}
