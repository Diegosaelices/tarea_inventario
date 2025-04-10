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

    // IMPORTANTE: Usamos PERSIST y MERGE para mantener la relación,
    // pero evitamos REMOVE para que los equipos NO se eliminen cuando se borra un aula
    @OneToMany(mappedBy = "aula", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Equipo> equipos;
}

