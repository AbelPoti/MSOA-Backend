package edu.bbte.replate.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
public class Listing extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    @PositiveOrZero
    private Double price;

    @Column(nullable = false, updatable = false)
    private Timestamp datePosted;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(length = 100)
    private String locationDetails;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Do not have a direct reference to User - would violate Database per Service pattern
    @Column(nullable = false)
    private Long ownerId;

    @PrePersist
    private void onCreate() {
        datePosted = new Timestamp(System.currentTimeMillis());
    }
}
