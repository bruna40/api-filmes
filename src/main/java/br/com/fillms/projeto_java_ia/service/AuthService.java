package br.com.fillms.projeto_java_ia.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fillms.projeto_java_ia.dto.LoginDTO;
import br.com.fillms.projeto_java_ia.dto.LoginResponseDTO;
import br.com.fillms.projeto_java_ia.model.UserEntity;
import br.com.fillms.projeto_java_ia.providers.JWTUserProvider;
import br.com.fillms.projeto_java_ia.repository.UsersRepository;

@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUserProvider jwtService;

    public AuthService(UsersRepository usersRepository, PasswordEncoder passwordEncoder,  JWTUserProvider jwtService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO authenticate(LoginDTO loginRequestDTO) {
        String login = loginRequestDTO.getLogin();
        UserEntity user = usersRepository.findByUsernameOrEmail(login, login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtService.generateToken(user.getId(), user.getEmail(),  user.getUsername());
        return LoginResponseDTO.builder()
                .token(token)
                .id(user.getId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
    
}
