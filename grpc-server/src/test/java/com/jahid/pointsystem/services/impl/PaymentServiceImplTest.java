package com.jahid.pointsystem.services.impl;

import com.example.pointsystem.services.AdditionalItem;
import com.example.pointsystem.services.PaymentRequest;
import com.example.pointsystem.services.PaymentResponse;
import com.jahid.pointsystem.entities.CourierService;
import com.jahid.pointsystem.entities.Payment;
import com.jahid.pointsystem.entities.PaymentMethod;
import com.jahid.pointsystem.enums.AdditionalInfo;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.services.CourierServiceService;
import com.jahid.pointsystem.services.PaymentMethodService;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentMethodService paymentMethodService;

    @Mock
    private CourierServiceService courierServiceService;

    @Mock
    private PersistenceService persistenceService;

    @Mock
    private StreamObserver<PaymentResponse> responseObserver;

    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setup() {
        paymentService = new PaymentServiceImpl(paymentMethodService, courierServiceService, persistenceService);
    }

    @Test
    public void makePayment_validCardPayment_shouldReturnValidPaymentResponse() {
        // given
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .id(1L)
                .name("VISA")
                .additionalInfo(AdditionalInfo.CARD)
                .build();
        Payment payment = Payment.builder()
                .id(1L)
                .paymentMethodId(paymentMethod.getId())
                .price(100D)
                .priceModifier(0.95D)
                .finalPrice(95D)
                .points(3D)
                .purchaseDate(LocalDateTime.now())
                .build();
        PaymentRequest request = PaymentRequest.newBuilder()
                .setPrice("100.00")
                .setPaymentMethod("VISA")
                .setAdditionalItem(AdditionalItem.newBuilder().setLast4("1234").build())
                .build();

        when(paymentMethodService.findByName(anyString())).thenReturn(paymentMethod);
        when(persistenceService.prepareAndSaveData(request, paymentMethod)).thenReturn(payment);

        // when
        paymentService.makePayment(request, responseObserver);

        // then
        ArgumentCaptor<PaymentResponse> captor = ArgumentCaptor.forClass(PaymentResponse.class);
        verify(responseObserver).onNext(captor.capture());
        verify(responseObserver).onCompleted();
        PaymentResponse response = captor.getValue();
        assertEquals("95.0", response.getFinalPrice());
        assertEquals(3, response.getPoints());
    }

    @Test
    void testMakePayment_withPaymentSavingFails_throwsException() {
        PaymentRequest request = PaymentRequest.newBuilder()
                .setPaymentMethod("CASH_ON_DELIVERY")
                .setAdditionalItem(AdditionalItem.newBuilder().setCourier("YAMATO").build())
                .build();

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .name("CASH_ON_DELIVERY")
                .additionalInfo(AdditionalInfo.COURIER)
                .build();

        CourierService courierService = CourierService.builder()
                .id(1L)
                .name("YAMATO")
                .permitted(true)
                .build();

        when(paymentMethodService.findByName(any())).thenReturn(paymentMethod);

        when(courierServiceService.findByName(any())).thenReturn(courierService);

        when(persistenceService.prepareAndSaveData(request, paymentMethod)).thenThrow(SystemException.class);

        assertThrows(SystemException.class, () ->
            paymentService.makePayment(request, new StreamObserver<>() {
                @Override
                public void onNext(PaymentResponse value) {

                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {

                }
            })
        );

    }
}
