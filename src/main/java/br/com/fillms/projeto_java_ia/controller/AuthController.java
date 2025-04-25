package br.com.fillms.projeto_java_ia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import br.com.fillms.projeto_java_ia.dto.LoginDTO;
import br.com.fillms.projeto_java_ia.dto.LoginResponseDTO;
import br.com.fillms.projeto_java_ia.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        var user = authService.authenticate(loginDTO);
        
        return ResponseEntity.ok(user);
    }

}
