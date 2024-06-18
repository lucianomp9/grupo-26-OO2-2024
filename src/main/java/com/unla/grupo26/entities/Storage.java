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

    @OneToMany(mappedBy = "storage", fetch = FetchType.LAZY)
    private Set<Batch> batches = new HashSet<>();

    public Storage(Set<Batch> batches) {
        this.batches = batches;
    }
}
