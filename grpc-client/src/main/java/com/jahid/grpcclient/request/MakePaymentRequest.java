package com.jahid.grpcclient.request;

import com.example.pointsystem.services.AdditionalItem;
import com.example.pointsystem.services.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MakePaymentRequest {
    private String customerId;
    private String price;
    private Double priceModifier;
    private String paymentMethod;
    private String datetime;
    private AdditionalItemDto additionalItem;

    public PaymentRequest toGrpc() {
        AdditionalItem.Builder additionalItemBuilder = AdditionalItem.newBuilder()
                .setLast4(additionalItem.getLast4());
        return PaymentRequest.newBuilder()
                .setCustomerId(customerId)
                .setPrice(price)
                .setPriceModifier(priceModifier)
                .setPaymentMethod(paymentMethod)
                .setDatetime(datetime)
                .setAdditionalItem(additionalItemBuilder)
                .build();
    }
}
