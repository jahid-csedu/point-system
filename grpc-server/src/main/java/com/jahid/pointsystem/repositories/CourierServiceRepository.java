package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.CourierService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierServiceRepository extends JpaRepository<CourierService, Long> {
    List<CourierService> findByName(String name);
}
