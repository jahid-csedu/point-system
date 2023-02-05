package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.entities.PaymentMethod;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.repositories.PaymentMethodRepository;
import com.jahid.pointsystem.services.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod findByName(String name) {
        log.info("Getting payment methods by name: {}", name);
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findByName(name);
        log.debug("payment methods: {}", paymentMethods.toString());
        if(paymentMethods.size() == 0) {
            log.error("No payment method found");
            throw new SystemException(Error.PAYMENT_METHOD_NOT_FOUND);
        }
        if(paymentMethods.size() > 1) {
            log.error("Multiple payment found with same name");
            throw new SystemException(Error.MULTIPLE_PAYMENT_METHOD_FOUND);
        }

        return paymentMethods.get(0);
    }
}
