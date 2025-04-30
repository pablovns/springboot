package com.pablovns.springboot.controller;

import com.pablovns.springboot.dto.usuario.UsuarioRequest;
import com.pablovns.springboot.dto.usuario.UsuarioResponse;
import com.pablovns.springboot.service.UsuarioService;
import com.pablovns.springboot.validation.groups.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioResponse encontrarUsuario(@PathVariable Integer id) {
        return usuarioService.encontrarUsuarioPorId(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Validated(OnCreate.class) @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = usuarioService.criarUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.atualizarUsuario(id, usuarioRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        usuarioService.deletarUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
