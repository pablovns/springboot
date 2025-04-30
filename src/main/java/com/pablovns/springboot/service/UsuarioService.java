package com.pablovns.springboot.service;

import com.pablovns.springboot.dto.usuario.UsuarioRequest;
import com.pablovns.springboot.dto.usuario.UsuarioResponse;
import com.pablovns.springboot.exceptions.BusinessException;
import com.pablovns.springboot.mapper.UsuarioMapper;
import com.pablovns.springboot.model.usuario.Usuario;
import com.pablovns.springboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest usuarioRequest) {
        try {
            Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            return usuarioMapper.toResponse(usuarioSalvo);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violação de integridade de dados", e);
        } catch (Exception e) {
            throw new BusinessException("Erro ao criar usuário", e);
        }
    }

    @Transactional
    public UsuarioResponse atualizarUsuario(Integer id, UsuarioRequest usuarioRequest) {
        // Recupera o usuário existente no banco
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        // Atualiza os dados da entidade existente com os dados do DTO
        usuarioMapper.updateEntityFromRequest(usuarioRequest, usuarioExistente);

        // Persiste a entidade existente com os dados atualizados
        usuarioRepository.save(usuarioExistente);

        // Retorna a resposta com os dados atualizados
        return usuarioMapper.toResponse(usuarioExistente);
    }

    public void deletarUsuarioPorId(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new BusinessException("Usuário não encontrado para deleção");
        }
        usuarioRepository.deleteById(id);
    }

    public List<UsuarioResponse> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toResponseList(usuarios);
    }

    public UsuarioResponse encontrarUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        return usuarioMapper.toResponse(usuario);
    }
}
