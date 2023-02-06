package com.jahid.pointsystem.services.impl;

import com.example.pointsystem.services.QueryRequest;
import com.example.pointsystem.services.QueryResponse;
import com.example.pointsystem.services.QueryServiceGrpc;
import com.example.pointsystem.services.Sales;
import com.jahid.pointsystem.models.SalesData;
import com.jahid.pointsystem.repositories.PaymentRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class QueryServiceImpl extends QueryServiceGrpc.QueryServiceImplBase {
    private final PaymentRepository paymentRepository;
    @Override
    public void querySales(QueryRequest request, StreamObserver<QueryResponse> responseObserver) {
        QueryResponse.Builder responseBuilder = QueryResponse.newBuilder();

        List<Sales> saleData = getSaleData(request);
        saleData.forEach(responseBuilder::addSales);

        QueryResponse response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private List<Sales> getSaleData(QueryRequest request) {
        LocalDateTime startDateTime = Util.getLocalDateTime(request.getStartDateTime());
        LocalDateTime endDateTime = Util.getLocalDateTime(request.getEndDateTime());
        List<LocalDateTime> startDates = new ArrayList<>();
        List<LocalDateTime> endDates = new ArrayList<>();
        for(LocalDateTime time = startDateTime; time.isBefore(endDateTime); time = time.plusHours(1)) {
            startDates.add(time);
            endDates.add(time.plusHours(1));
        }
        List<SalesData> hourlySalesData = paymentRepository.findHourlySalesData(startDateTime, endDateTime);
        return hourlySalesData.stream()
                .map(salesData -> Sales.newBuilder()
                        .setDatetime(Util.getStringDate(salesData.getDatetime()))
                        .setSales(String.valueOf(salesData.getSaleAmount()))
                        .setPoints(salesData.getPoints())
                        .build())
                .collect(Collectors.toList());
    }
}
