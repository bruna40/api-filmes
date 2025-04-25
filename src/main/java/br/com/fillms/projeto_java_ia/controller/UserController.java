package br.com.fillms.projeto_java_ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fillms.projeto_java_ia.dto.ResponseUser;
import br.com.fillms.projeto_java_ia.dto.UpdateUserDTO;
import br.com.fillms.projeto_java_ia.model.UserEntity;
import br.com.fillms.projeto_java_ia.providers.JWTUserProvider;
import br.com.fillms.projeto_java_ia.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    private final JWTUserProvider jwtUserProvider;

    @Autowired
    public UserController(UserService userService, JWTUserProvider jwtUserProvider) {
        this.jwtUserProvider = jwtUserProvider;
        this.userService = userService;
        
    }

    @PostMapping("/create")
    public ResponseUser createUser(@Valid @RequestBody  UserEntity user) {
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public ResponseUser updateUser(
        @Valid @RequestBody  UpdateUserDTO user,
        @RequestHeader("Authorization") String token
    ) {

        String userId = jwtUserProvider.extractIdFromToken(token);
        return userService.editUser(user, userId);
    }

    @GetMapping("/profile")
    public ResponseUser getUserProfile(@RequestHeader("Authorization") String token) {
        String userId = jwtUserProvider.extractIdFromToken(token);
        return userService.profileUser(userId);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestHeader("Authorization") String token) {
        String userId = jwtUserProvider.extractIdFromToken(token);
        userService.deleteUser(userId);
    }
}
