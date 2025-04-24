package br.com.fillms.projeto_java_ia.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fillms.projeto_java_ia.dto.ResponseUser;
import br.com.fillms.projeto_java_ia.enums.RoleEnum;
import br.com.fillms.projeto_java_ia.exceptions.UserAlreadyExists;
import br.com.fillms.projeto_java_ia.model.UserEntity;
import br.com.fillms.projeto_java_ia.repository.UsersRepository;
import br.com.fillms.projeto_java_ia.utils.CPFValidator;


@Service
public class UserService {
    
    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        
    }


    public ResponseUser createUser(UserEntity user) {
        if (usersRepository.findByUsername(user.getUsername()).isPresent()
            || usersRepository.findByEmail(user.getEmail()).isPresent()
            || usersRepository.findByCpf(user.getCpf()).isPresent()) {
                throw new UserAlreadyExists();
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        if (user.getActive() == null) {
            user.setActive(true);
        }
        if (user.getRole() == null) {
            user.setRole(RoleEnum.USER);
        } 
        
        
        if (Period.between(user.getBirthDate(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
        if(!CPFValidator.isCPF(user.getCpf())) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        UserEntity userEntity = UserEntity.builder()
            .username(user.getUsername())
            .name(user.getName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phone(user.getPhone())
            .cpf(user.getCpf())
            .password(encodedPassword)
            .birthDate(user.getBirthDate())
            .active(user.getActive())
            .role(user.getRole())
            .build();
        UserEntity savedUser = usersRepository.save(userEntity);

        
        return new ResponseUser(
            savedUser.getUsername(),
            savedUser.getName(),
            savedUser.getLastName(),
            savedUser.getEmail(),
            savedUser.getPhone(),
            savedUser.getCpf(),
            savedUser.getActive()
        );
    }

}
