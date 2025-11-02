package com.pelo.insperscore.autenticacao;

import com.pelo.insperscore.autenticacao.dto.UsuarioLoginDTO;
import com.pelo.insperscore.autenticacao.dto.UsuarioRegistroDTO;
import com.pelo.insperscore.autenticacao.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrar(UsuarioRegistroDTO dto) {
        if (usuarioRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username já existe");
        }

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getSenha()));
        usuario.setEmail(dto.getEmail());
        usuario.setTimeCoracao(dto.getTimeCoracao());

        String token = UUID.randomUUID().toString();
        usuario.setToken(token);

        usuario = usuarioRepository.save(usuario);

        return mapearParaResponse(usuario);
    }


    public UsuarioResponseDTO login(UsuarioLoginDTO dto) {
        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        if (usuario.getToken() == null || usuario.getToken().isEmpty()) {
            String token = UUID.randomUUID().toString();
            usuario.setToken(token);
            usuario = usuarioRepository.save(usuario);
        }

        return mapearParaResponse(usuario);
    }

    public Usuario validarToken(String token) {
        return usuarioRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }


    private UsuarioResponseDTO mapearParaResponse(Usuario usuario) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setToken(usuario.getToken());
        return response;
    }
}