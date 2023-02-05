package com.jahid.pointsystem.entities;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "payments")
public class Payment {

    @Id
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "price_modifier", nullable = false)
    private Double priceModifier;

    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @Column(name = "points", nullable = false)
    private Double points;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "payment_method_id", nullable = false)
    private Long paymentMethodId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
