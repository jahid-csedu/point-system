package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.entities.ChequeDetail;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.repositories.ChequeDetailRepository;
import com.jahid.pointsystem.services.ChequeDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChequeDetailServiceImpl implements ChequeDetailService {
    private final ChequeDetailRepository chequeDetailRepository;

    @Override
    public ChequeDetail add(ChequeDetail chequeDetail) {
        log.info("Creating new check details");
        validate(chequeDetail);

        return chequeDetailRepository.save(chequeDetail);
    }

    private void validate(ChequeDetail chequeDetail) {
        if(StringUtils.isBlank(chequeDetail.getBankName())) {
            log.error("Bank name is blank");
            throw new SystemException(Error.BANK_NAME_EMPTY);
        }
        if(StringUtils.isBlank(chequeDetail.getChequeNumber())) {
            log.error("Cheque number is blank");
            throw new SystemException(Error.CHEQUE_NUMBER_EMPTY);
        }
        if(chequeDetail.getPaymentId() == null) {
            log.error("payment id is null");
            throw new SystemException(Error.PAYMENT_ID_NULL);
        }
    }
}
