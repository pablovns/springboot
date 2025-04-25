package com.pablovns.springboot.controller;

import com.pablovns.springboot.exceptions.BusinessException;
import com.pablovns.springboot.model.Usuario;
import com.pablovns.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
                throw new BusinessException("O nome do usuário não pode estar vazio");
            }
        
        return usuarioRepository.save(usuario);
    } catch (DataIntegrityViolationException e) {
        throw new BusinessException("Erro ao salvar usuário: violação de integridade de dados");
    } catch (Exception e) {
        throw new BusinessException("Erro ao salvar usuário: %s", e.getMessage());
    }
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
}