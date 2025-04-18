package com.example.apiinventario.repositories;

import com.example.apiinventario.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository <Equipo, Long> {
}
