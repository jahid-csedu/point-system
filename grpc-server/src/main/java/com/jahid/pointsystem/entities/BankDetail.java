package com.jahid.pointsystem.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "bank_details")
public class BankDetail {

    @Id
    @SequenceGenerator(name = "bank_details_seq", sequenceName = "bank_details_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_details_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDetail bankDetails = (BankDetail) o;
        return Objects.equals(id, bankDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
