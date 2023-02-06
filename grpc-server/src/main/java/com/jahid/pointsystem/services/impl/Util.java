package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class Util {
    public static LocalDateTime getLocalDateTime(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(datetime, formatter);
        } catch (Exception e) {
            log.error("Invalid Date Format");
            throw new SystemException(Error.INVALID_DATE_FORMAT);
        }
        return date;
    }

    public static String getStringDate(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String date;
        try {
            date = datetime.format(formatter);
        } catch (Exception e) {
            log.error("Invalid Date Format");
            throw new SystemException(Error.INVALID_DATE_FORMAT);
        }
        return date;
    }
}
