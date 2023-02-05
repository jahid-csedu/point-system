package com.jahid.pointsystem.repositories;

import com.jahid.pointsystem.entities.CardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailRepository extends JpaRepository<CardDetail, Long> {
}
