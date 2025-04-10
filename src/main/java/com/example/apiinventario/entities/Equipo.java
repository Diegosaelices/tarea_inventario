package com.example.apiinventario.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroDeSerie;
    private LocalDate fechaAlta;
    private String caracteristicas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;
}

