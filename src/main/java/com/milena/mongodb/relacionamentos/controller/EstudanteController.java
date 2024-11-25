package com.milena.mongodb.relacionamentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.milena.mongodb.relacionamentos.model.Estudante;
import com.milena.mongodb.relacionamentos.repository.EstudanteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    // POST /estudantes
    @PostMapping
    public ResponseEntity<Estudante> criarEstudante(@RequestBody Estudante estudante) {
        Estudante novoEstudante = estudanteRepository.save(estudante);
        return ResponseEntity.ok(novoEstudante);
    }

    // GET /estudantes
    @GetMapping
    public ResponseEntity<List<Estudante>> listarEstudantes() {
        List<Estudante> estudantes = estudanteRepository.findAll();
        return ResponseEntity.ok(estudantes);
    }

    // GET /estudantes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarEstudantePorId(@PathVariable String id) {
        Optional<Estudante> estudante = estudanteRepository.findById(id);
        return estudante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /estudantes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizarEstudante(@PathVariable String id, @RequestBody Estudante estudanteAtualizado) {
        return estudanteRepository.findById(id).map(estudante -> {
            estudante.setNome(estudanteAtualizado.getNome());
            estudante.setCursos(estudanteAtualizado.getCursos());
            Estudante atualizado = estudanteRepository.save(estudante);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /estudantes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstudante(@PathVariable String id) {
        if (estudanteRepository.existsById(id)) {
            estudanteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
