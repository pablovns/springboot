package com.pablovns.springboot.controller;

import com.pablovns.springboot.model.Usuario;
import com.pablovns.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}
