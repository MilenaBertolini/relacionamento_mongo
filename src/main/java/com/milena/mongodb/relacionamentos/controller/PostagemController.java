package com.milena.mongodb.relacionamentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.milena.mongodb.relacionamentos.model.Postagem;
import com.milena.mongodb.relacionamentos.repository.PostagemRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @PostMapping
    public ResponseEntity<Postagem> criarPostagem(@RequestBody Postagem postagem) {
        Postagem novaPostagem = postagemRepository.save(postagem);
        return ResponseEntity.ok(novaPostagem);
    }

    @GetMapping
    public ResponseEntity<List<Postagem>> listarPostagens() {
        List<Postagem> postagens = postagemRepository.findAll();
        return ResponseEntity.ok(postagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> buscarPostagemPorId(@PathVariable String id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);
        return postagem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Postagem> atualizarPostagem(@PathVariable String id, @RequestBody Postagem postagemAtualizada) {
        return postagemRepository.findById(id).map(postagem -> {
            postagem.setTitulo(postagemAtualizada.getTitulo());
            postagem.setConteudo(postagemAtualizada.getConteudo());
            Postagem atualizada = postagemRepository.save(postagem);
            return ResponseEntity.ok(atualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPostagem(@PathVariable String id) {
        if (postagemRepository.existsById(id)) {
            postagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
