package edu.bbte.replate.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Collection;

@Getter
@Entity
public class Country extends BaseEntity {
    @Column(nullable = false, unique = true, length = 63)
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<County> counties;
}

