package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.repositories.CourierServiceRepository;
import com.jahid.pointsystem.entities.CourierService;
import com.jahid.pointsystem.services.CourierServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "courier_services")
public class CourierServiceServiceImpl implements CourierServiceService {
    private final CourierServiceRepository courierServiceRepository;

    @Override
    @Cacheable
    public CourierService findByName(String name) {
        log.info("Getting courier service by name: {}", name);
        List<CourierService> courierServices = courierServiceRepository.findByName(name);
        log.debug("Courier Services: {}", courierServices.toString());
        if(courierServices.size() == 0) {
            log.error("No courier service found");
            throw new SystemException(Error.COURIER_SERVICE_NOT_FOUND);
        }
        if(courierServices.size() > 1) {
            log.error("Multiple courier service found with same name");
            throw new SystemException(Error.MULTIPLE_COURIER_SERVICE_FOUND);
        }

        return courierServices.get(0);
    }
}
