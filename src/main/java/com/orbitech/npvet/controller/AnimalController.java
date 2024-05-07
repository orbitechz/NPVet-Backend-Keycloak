package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnimalDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import com.orbitech.npvet.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class AnimalController {

    @Autowired
    private AnimalService service;
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }

    @GetMapping("/tutor/{tutorId}/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> getAnimalByTutorAndName(
            @PathVariable("tutorId") Long tutorId,
            @PathVariable("nome") String nome
    ) {
        AnimalDTO animals = service.getByTutorAndName(tutorId, nome);
        return ResponseEntity.ok(animals);
    }


    @GetMapping("/tutor/{tutorId}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByTutor(@PathVariable("tutorId") Long tutorId) {
        List<AnimalDTO> animals = service.getAllByTutor(tutorId);
        return ResponseEntity.ok(animals);
    }


    @GetMapping("/raca/{raca}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getByRaca(@PathVariable("raca") String raca) {
        return ResponseEntity.ok(service.getAllByRaca(raca));
    }

    @GetMapping("/especie/{especie}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getByEspecie(@PathVariable("especie") String especie) {
        return ResponseEntity.ok(service.getAllByEspecie(especie));
    }
    @GetMapping("/tutor/get/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAllByTutorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAllByTutorId(id));
    }
    @GetMapping("/all/desativados")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAllDesativados(){
        return ResponseEntity.ok(service.getAllDesativado());
    }

    @GetMapping("/all/ativos")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnimalDTO>> getAllAtivos(){
        return ResponseEntity.ok(service.getAllAtivo());
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> create(@RequestBody @Validated AnimalDTO animalDTO){
        return ResponseEntity.ok(service.create(animalDTO));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> update(@PathVariable("id") Long id, @RequestBody @Validated AnimalDTO animalDTO){
        return ResponseEntity.ok(service.update(id, animalDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/activate/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<AnimalDTO> activate(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.activate(id));
    }

}
