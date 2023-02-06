package com.jahid.pointsystem.entities;

import javax.persistence.*;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "courier_services")
public class CourierService {

    @Id
    @SequenceGenerator(name = "courier_services_seq", sequenceName = "courier_services_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courier_services_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "permitted", nullable = false)
    private Boolean permitted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourierService courierService = (CourierService) o;
        return Objects.equals(id, courierService.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
