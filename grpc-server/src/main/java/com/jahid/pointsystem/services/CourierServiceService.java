package com.jahid.pointsystem.services;

import com.jahid.pointsystem.entities.CourierService;

public interface CourierServiceService {
    CourierService findByName(String name);
}
