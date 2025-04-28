package com.example.apiinventario.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    // IMPORTANTE: Pongo PERSIST y MERGE para mantener la relación,
    // si pusieramos REMOVE los equipos se borrarian al borrar un aula.
    @OneToMany(mappedBy = "aula", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Equipo> equipos;
}

