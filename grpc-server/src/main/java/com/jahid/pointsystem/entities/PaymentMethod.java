package com.jahid.pointsystem.entities;

import com.jahid.pointsystem.enums.AdditionalInfo;
import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "payment_methods")
public class PaymentMethod {
    @Id
    @SequenceGenerator(name = "payment_methods_seq", sequenceName = "payment_methods_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_methods_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "point_rate", nullable = false)
    private Double pointRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "additional_info")
    private AdditionalInfo additionalInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod paymentMethod = (PaymentMethod) o;
        return Objects.equals(id, paymentMethod.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
