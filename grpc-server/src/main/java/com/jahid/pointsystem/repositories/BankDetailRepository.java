package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.BankDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailRepository extends JpaRepository<BankDetail, Long> {
}
