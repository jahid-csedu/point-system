package com.jahid.pointsystem.entities;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cheque_details")
public class ChequeDetail {

    @Id
    @SequenceGenerator(name = "cheque_details_seq", sequenceName = "cheque_details_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_details_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "cheque_number", nullable = false)
    private String chequeNumber;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChequeDetail chequeDetails = (ChequeDetail) o;
        return Objects.equals(id, chequeDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
