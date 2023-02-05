package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findByName(String name);
}
