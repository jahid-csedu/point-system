package com.jahid.pointsystem.services;

import com.jahid.pointsystem.entities.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod findByName(String name);
}
