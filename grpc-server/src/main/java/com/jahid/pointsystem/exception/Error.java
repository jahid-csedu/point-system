package com.jahid.pointsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {

    SYSTEM_EXCEPTION("SYS999", "An unwanted error occurred"),
    COURIER_SERVICE_NOT_FOUND("POS101", "No Courier Service Found"),
    MULTIPLE_COURIER_SERVICE_FOUND("POS102", "Multiple Courier Service Found"),
    PAYMENT_METHOD_NOT_FOUND("POS103", "No Payment Found"),
    MULTIPLE_PAYMENT_METHOD_FOUND("POS104", "Multiple Payment Method Found"),
    BANK_NAME_EMPTY("POS105", "Bank name can't be empty"),
    ACCOUNT_NUMBER_EMPTY("POS106", "Account number can't be empty"),
    CHEQUE_NUMBER_EMPTY("POS107", "Cheque number can't be empty"),
    CARD_NO_EMPTY("POS108", "Must contain last 4 digits of the card"),
    PAYMENT_ID_NULL("POS109", "Payment id can't be null"),

    COURIER_SERVICE_NOT_PERMITTED("POS110", "This courier service is not permitted"),
    PAYMENT_SAVING_FAILED("POS111", "Failed to save the payment information"),
    INVALID_DATE_FORMAT("POS112", "Date format is not valid");





    private String code;
    private String message;
}
