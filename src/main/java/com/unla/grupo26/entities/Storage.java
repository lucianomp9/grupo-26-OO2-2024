package com.unla.grupo26.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
// Almacen
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storage_id;

    private String location;

    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY)
    private Set<Batch> batches = new HashSet<>();

    public Storage(Set<Batch> batches, String location) {
        this.batches = batches;
        this.location = location;
    }
}
