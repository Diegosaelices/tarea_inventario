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

    //Ponemos el JsonIgnore para que no se cree un bucle infinito.
    @JsonIgnore
    @ManyToOne
    //Nombre con el que se vera en la bbdd
    @JoinColumn(name = "aula_id")
    private Aula aula;
}

