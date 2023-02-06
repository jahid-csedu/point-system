package com.jahid.grpcclient.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdditionalItemDto {
    private String last4;
    private String courier;
    private String bankName;
    private String chequeNumber;
    private String accountNumber;
}
