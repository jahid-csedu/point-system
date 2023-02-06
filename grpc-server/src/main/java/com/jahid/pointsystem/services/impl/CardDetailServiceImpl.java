package com.jahid.pointsystem.services.impl;

import com.jahid.pointsystem.entities.CardDetail;
import com.jahid.pointsystem.exception.Error;
import com.jahid.pointsystem.exception.SystemException;
import com.jahid.pointsystem.repositories.CardDetailRepository;
import com.jahid.pointsystem.services.CardDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardDetailServiceImpl implements CardDetailService {

    private final CardDetailRepository cardDetailRepository;

    @Override
    public CardDetail add(CardDetail cardDetail) {
        log.info("Adding new card details");
        validate(cardDetail);

        return cardDetailRepository.save(cardDetail);
    }

    private void validate(CardDetail cardDetail) {
        if(StringUtils.isBlank(cardDetail.getCardNumber())) {
            log.error("Card number is blank");
            throw new SystemException(Error.CARD_NO_EMPTY);
        }

        if(cardDetail.getPaymentId() == null) {
            log.error("payment id is null");
            throw new SystemException(Error.PAYMENT_ID_NULL);
        }
    }
}
