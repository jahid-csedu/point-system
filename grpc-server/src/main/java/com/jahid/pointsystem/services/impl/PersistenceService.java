package com.jahid.pointsystem.services.impl;

import com.example.pointsystem.services.PaymentRequest;
import com.jahid.pointsystem.entities.*;
import com.jahid.pointsystem.enums.AdditionalInfo;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.repositories.PaymentRepository;
import com.jahid.pointsystem.services.BankDetailService;
import com.jahid.pointsystem.services.CardDetailService;
import com.jahid.pointsystem.services.ChequeDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersistenceService {

    private final PaymentRepository paymentRepository;
    private final CardDetailService cardDetailService;
    private final BankDetailService bankDetailService;
    private final ChequeDetailService chequeDetailService;

    @Transactional
    public Payment prepareAndSaveData(PaymentRequest request, PaymentMethod paymentMethod) {
        double price = Double.parseDouble(request.getPrice());
        double finalPrice = price * request.getPriceModifier();

        LocalDateTime dateTime = Util.getLocalDateTime(request.getDatetime());

        Payment payment = Payment.builder()
                .finalPrice(finalPrice)
                .customerId(request.getCustomerId())
                .paymentMethodId(paymentMethod.getId())
                .price(price)
                .purchaseDate(dateTime)
                .priceModifier(request.getPriceModifier())
                .points(paymentMethod.getPointRate() * price)
                .build();

        Payment paidPayment;
        try {
            paidPayment = paymentRepository.save(payment);
        } catch (Exception e) {
            log.error("Failed to save payment: {}", payment);
            throw new SystemException(Error.PAYMENT_SAVING_FAILED);
        }

        AdditionalInfo additionalInfo = paymentMethod.getAdditionalInfo();
        if(AdditionalInfo.CARD == additionalInfo) {
            prepareAndSaveCardDetail(request, paidPayment);
        }else if (AdditionalInfo.BANK == additionalInfo) {
            prepareAndSaveBankDetail(request, paidPayment);
        }else if (AdditionalInfo.CHEQUE == additionalInfo) {
            prepareAndSaveChequeDetail(request, paidPayment);
        }
        return payment;
    }

    private void prepareAndSaveChequeDetail(PaymentRequest request, Payment paidPayment) {
        ChequeDetail chequeDetail = ChequeDetail.builder()
                .paymentId(paidPayment.getId())
                .bankName(request.getAdditionalItem().getBankName())
                .chequeNumber(request.getAdditionalItem().getChequeNumber())
                .build();
        chequeDetailService.add(chequeDetail);
    }

    private void prepareAndSaveBankDetail(PaymentRequest request, Payment paidPayment) {
        BankDetail bankDetail = BankDetail.builder()
                .paymentId(paidPayment.getId())
                .bankName(request.getAdditionalItem().getBankName())
                .accountNumber(request.getAdditionalItem().getAccountNumber())
                .build();
        bankDetailService.add(bankDetail);
    }

    private void prepareAndSaveCardDetail(PaymentRequest request, Payment paidPayment) {
        CardDetail cardDetail = CardDetail.builder()
                .paymentId(paidPayment.getId())
                .cardNumber(request.getAdditionalItem().getLast4())
                .build();
        cardDetailService.add(cardDetail);
    }
}
