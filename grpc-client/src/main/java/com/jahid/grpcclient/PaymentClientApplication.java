package com.jahid.grpcclient;

import com.example.pointsystem.services.PaymentServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PaymentClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentClientApplication.class, args);
    }
}
