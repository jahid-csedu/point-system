package com.jahid.pointsystem.models;

import java.time.LocalDateTime;

public interface SalesData {
    LocalDateTime getDatetime();
    Double getSaleAmount();
    Double getPoints();
}
