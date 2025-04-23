package br.com.fillms.projeto_java_ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import br.com.fillms.projeto_java_ia.repository.UsersRepository;

public class UserService {
    
    private UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        
    }
}
