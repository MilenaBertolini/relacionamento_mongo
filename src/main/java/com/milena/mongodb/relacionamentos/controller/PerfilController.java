package com.milena.mongodb.relacionamentos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milena.mongodb.relacionamentos.model.Perfil;
import com.milena.mongodb.relacionamentos.repository.PerfilRepository;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil perfil) {
        Perfil novoPerfil = perfilRepository.save(perfil);
        return ResponseEntity.ok(novoPerfil);
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarPerfis() {
        List<Perfil> perfis = perfilRepository.findAll();
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPerfilPorId(@PathVariable String id) {
        Optional<Perfil> perfil = perfilRepository.findById(id);
        return perfil.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable String id, @RequestBody Perfil perfilAtualizado) {
        return perfilRepository.findById(id).map(perfil -> {
            perfil.setBio(perfilAtualizado.getBio());
            perfil.setAvatarUrl(perfilAtualizado.getAvatarUrl());
            Perfil atualizado = perfilRepository.save(perfil);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPerfil(@PathVariable String id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
