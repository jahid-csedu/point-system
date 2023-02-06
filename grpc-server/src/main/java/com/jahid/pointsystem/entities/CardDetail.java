package com.jahid.pointsystem.entities;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "card_details")
public class CardDetail {

    @Id
    @SequenceGenerator(name = "card_details_seq", sequenceName = "card_details_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_details_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDetail cardDetails = (CardDetail) o;
        return Objects.equals(id, cardDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
