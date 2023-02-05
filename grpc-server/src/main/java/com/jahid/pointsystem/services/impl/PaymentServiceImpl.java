package com.jahid.pointsystem.services.impl;

import com.example.pointsystem.services.PaymentRequest;
import com.example.pointsystem.services.PaymentResponse;
import com.example.pointsystem.services.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    @Override
    public void makePayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        double finalPrice = Double.valueOf(request.getPrice()) * request.getPriceModifier();
        PaymentResponse response = PaymentResponse.newBuilder()
                .setFinalPrice(String.valueOf(finalPrice))
                .setPoints(5)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
