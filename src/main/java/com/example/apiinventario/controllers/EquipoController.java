package com.example.apiinventario.controllers;

import com.example.apiinventario.entities.Equipo;
import com.example.apiinventario.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipoController {

    @Autowired
    private EquipoRepository equipoRepo;

    // Listar todos los equipos
    @GetMapping("/api/equipos")
    public List<Equipo> getAll() {
        return equipoRepo.findAll();
    }

    // Obtener equipo por ID
    @GetMapping("/api/equipos/{id}")
    public Equipo getById(@PathVariable Long id) {
        return equipoRepo.findById(id).orElse(null);
    }

    // Añadir equipo
    @PostMapping("/api/equipos")
    public Equipo create(@RequestBody Equipo equipo) {
        return equipoRepo.save(equipo);
    }

    // Modificar equipo
    @PutMapping("/api/equipos/{id}")
    public Equipo update(@PathVariable Long id, @RequestBody Equipo equipo) {
        equipo.setId(id);
        return equipoRepo.save(equipo);
    }

    // Eliminar equipo
    @DeleteMapping("/api/equipos/{id}")
    public void delete(@PathVariable Long id) {
        equipoRepo.deleteById(id);
    }
}
