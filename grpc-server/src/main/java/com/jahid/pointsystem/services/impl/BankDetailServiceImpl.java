package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.services.BankDetailService;
import com.jahid.pointsystem.entities.BankDetail;
import com.jahid.pointsystem.repositories.BankDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankDetailServiceImpl implements BankDetailService {
    private final BankDetailRepository bankDetailRepository;

    @Override
    public BankDetail add(BankDetail bankDetail) {
        log.info("Creating new bank details");
        validate(bankDetail);

        return bankDetailRepository.save(bankDetail);
    }

    private void validate(BankDetail bankDetail) {
        if(StringUtils.isBlank(bankDetail.getBankName())) {
            log.error("Bank name is blank");
            throw new SystemException(Error.BANK_NAME_EMPTY);
        }
        if(StringUtils.isBlank(bankDetail.getAccountNumber())) {
            log.error("Bank name is blank");
            throw new SystemException(Error.ACCOUNT_NUMBER_EMPTY);
        }
        if(bankDetail.getPaymentId() == null) {
            log.error("Payment id is null");
            throw new SystemException(Error.PAYMENT_ID_NULL);
        }
    }
}
