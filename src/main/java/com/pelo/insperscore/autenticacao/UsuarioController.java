package com.pelo.insperscore.autenticacao;

import com.pelo.insperscore.autenticacao.dto.UsuarioLoginDTO;
import com.pelo.insperscore.autenticacao.dto.UsuarioRegistroDTO;
import com.pelo.insperscore.autenticacao.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrar(@RequestBody UsuarioRegistroDTO dto) {
        UsuarioResponseDTO response = usuarioService.registrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioLoginDTO dto) {
        UsuarioResponseDTO response = usuarioService.login(dto);
        return ResponseEntity.ok(response);
    }
}
