package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.Payment;
import com.jahid.pointsystem.models.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = """
            SELECT
              date_trunc('hour', purchase_date) AS datetime,
              SUM(final_price) AS saleAmount,
              SUM(points) AS points
            FROM payments
            WHERE purchase_date >= :startDateTime
              AND purchase_date <= :endDateTime
            GROUP BY date_trunc('hour', purchase_date)
            """,
    nativeQuery = true)
    List<SalesData> findHourlySalesData(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
