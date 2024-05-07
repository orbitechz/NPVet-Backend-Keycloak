package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.UsuarioDTO;
import com.orbitech.npvet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO>geById(@PathVariable("id") final String id){
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO>create(@Validated @RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.create(usuarioDTO));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO>update( @PathVariable("id") final long id, @RequestBody @Validated UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(service.update(id,usuarioDTO));
    }

    @GetMapping("/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getUsuarioByName(@PathVariable("nome") String nome){
        return ResponseEntity.ok(service.getUsuarioByName(nome));

    }

    @GetMapping("/secretaria")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getSecretaria(){
        List<UsuarioDTO>response = service.getTipoSecretaria();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/adm")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getAdm(){
        List<UsuarioDTO>response = service.getTipoAdm();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/veterinarios")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getVeterinarios(){
        List<UsuarioDTO>response = service.getTipoMedico();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<UsuarioDTO>>getUsername(@PathVariable("username")String username){
        return ResponseEntity.ok(service.getUsername(username));
    }

    @GetMapping("/cpf/{cpf}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO>getUsuarioCpf(@PathVariable("cpf")String cpf){
        return ResponseEntity.ok(service.getUsuarioByCpf(cpf));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(service.delete(id));
    }
    @PostMapping("/activate/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    public ResponseEntity<UsuarioDTO> activate(@PathVariable("id") String id){
        return ResponseEntity.ok(service.activate(id));
    }

}
