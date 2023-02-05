package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
