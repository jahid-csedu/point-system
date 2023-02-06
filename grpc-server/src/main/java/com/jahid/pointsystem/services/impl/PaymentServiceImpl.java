package com.jahid.pointsystem.services.impl;

import com.example.pointsystem.services.PaymentRequest;
import com.example.pointsystem.services.PaymentResponse;
import com.example.pointsystem.services.PaymentServiceGrpc;
import com.jahid.pointsystem.entities.CourierService;
import com.jahid.pointsystem.entities.Payment;
import com.jahid.pointsystem.entities.PaymentMethod;
import com.jahid.pointsystem.enums.AdditionalInfo;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.services.CourierServiceService;
import com.jahid.pointsystem.services.PaymentMethodService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.commons.lang3.StringUtils;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {
    private final PaymentMethodService paymentMethodService;
    private final CourierServiceService courierServiceService;
    private final PersistenceService persistenceService;

    @Override
    public void makePayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        PaymentMethod paymentMethod = paymentMethodService.findByName(request.getPaymentMethod());
        validateRequest(request, paymentMethod);

        Payment payment = persistenceService.prepareAndSaveData(request, paymentMethod);

        PaymentResponse response = PaymentResponse.newBuilder()
                .setFinalPrice(String.valueOf(payment.getFinalPrice()))
                .setPoints(payment.getPoints())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private void validateRequest(PaymentRequest request, PaymentMethod paymentMethod) {
        AdditionalInfo additionalInfo = paymentMethod.getAdditionalInfo();
        if(AdditionalInfo.CARD == additionalInfo) {
            if(StringUtils.isBlank(request.getAdditionalItem().getLast4())) {
                throw new SystemException(Error.CARD_NO_EMPTY);
            }
        }else if (AdditionalInfo.BANK == additionalInfo) {
            if(StringUtils.isBlank(request.getAdditionalItem().getBankName())) {
                throw new SystemException(Error.BANK_NAME_EMPTY);
            }
            if(StringUtils.isBlank(request.getAdditionalItem().getAccountNumber())) {
                throw new SystemException(Error.ACCOUNT_NUMBER_EMPTY);
            }
        }else if (AdditionalInfo.CHEQUE == additionalInfo) {
            if(StringUtils.isBlank(request.getAdditionalItem().getBankName())) {
                throw new SystemException(Error.BANK_NAME_EMPTY);
            }
            if(StringUtils.isBlank(request.getAdditionalItem().getChequeNumber())) {
                throw new SystemException(Error.CHEQUE_NUMBER_EMPTY);
            }
        }else if (AdditionalInfo.COURIER == additionalInfo) {
            String courier = request.getAdditionalItem().getCourier();
            if(StringUtils.isBlank(courier)) {
                throw new SystemException(Error.COURIER_SERVICE_NOT_FOUND);
            }
            validateCourierService(courier);
        }
    }

    private void validateCourierService(String courier) {
        CourierService courierService = courierServiceService.findByName(courier);
        if(Boolean.FALSE == courierService.getPermitted()) {
            throw new SystemException(Error.COURIER_SERVICE_NOT_PERMITTED);
        }
    }
}
