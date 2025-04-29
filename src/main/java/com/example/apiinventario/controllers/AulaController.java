package com.example.apiinventario.controllers;

import com.example.apiinventario.entities.Aula;
import com.example.apiinventario.entities.Equipo;
import com.example.apiinventario.repositories.AulaRepository;
import com.example.apiinventario.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AulaController {

    @Autowired
    private AulaRepository aulaRepo;

    @Autowired
    private EquipoRepository equipoRepo;

    // Listar todas las aulas
    @GetMapping("/api/aulas")
    public List<Aula> getAll() {
        return aulaRepo.findAll();
    }

    // Obtener un aula por ID
    @GetMapping("/api/aulas/{id}")
    public Aula getById(@PathVariable Long id) {
        return aulaRepo.findById(id).orElse(null);
    }

    // Añadir aula
    @PostMapping("/api/aulas")
    public Aula create(@RequestBody Aula aula) {
        return aulaRepo.save(aula);
    }

    // Modificar aula
    @PutMapping("/api/aulas/{id}")
    public Aula update(@PathVariable Long id, @RequestBody Aula aula) {
        aula.setId(id);
        return aulaRepo.save(aula);
    }

    // Eliminar aula (sin borrar sus equipos, solo desvincular)
    @DeleteMapping("/api/aulas/{id}")
    public void delete(@PathVariable Long id) {
        Aula aula = aulaRepo.findById(id).orElse(null);
        if (aula != null) {
            for (Equipo e : aula.getEquipos()) {
                e.setAula(null);   // Quitamos la relación con el aula (aula=null)
                equipoRepo.save(e); //guardamos el equipo
            }
            aulaRepo.delete(aula); //borramos el aula
        }
    }

    // Agregar un equipo a un aula
    @PostMapping("/api/aulas/{aulaId}/equipos/{equipoId}")
    public ResponseEntity<Aula> addEquipoToAula(@PathVariable Long aulaId, @PathVariable Long equipoId) {
        Optional<Aula> aulaOpt = aulaRepo.findById(aulaId);
        Optional<Equipo> equipoOpt = equipoRepo.findById(equipoId);

        if (aulaOpt.isPresent() && equipoOpt.isPresent()) { //buscamos que existan
            Aula aula = aulaOpt.get();
            Equipo equipo = equipoOpt.get();

            equipo.setAula(aula);           // Asignamos el aula al equipo
            aula.getEquipos().add(equipo);  // Añadimos el equipo al aula

            aulaRepo.save(aula);
            equipoRepo.save(equipo);

            return ResponseEntity.ok(aula);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar un equipo de un aula
    @DeleteMapping("/api/aulas/{aulaId}/equipos/{equipoId}")
    public ResponseEntity<Aula> removeEquipoFromAula(@PathVariable Long aulaId, @PathVariable Long equipoId) {
        Optional<Aula> aulaOpt = aulaRepo.findById(aulaId);
        Optional<Equipo> equipoOpt = equipoRepo.findById(equipoId);

        if (aulaOpt.isPresent() && equipoOpt.isPresent()) { //buscamos que existen
            Aula aula = aulaOpt.get();
            Equipo equipo = equipoOpt.get();

            if (equipo.getAula() != null && equipo.getAula().getId().equals(aulaId)) { //Comprobamos si el equipo está en esa aula.
                equipo.setAula(null);               // Quitamos la referencia al aula
                aula.getEquipos().remove(equipo);   // Lo sacamos de la lista del aula

                equipoRepo.save(equipo);
                aulaRepo.save(aula);

                return ResponseEntity.ok(aula);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
