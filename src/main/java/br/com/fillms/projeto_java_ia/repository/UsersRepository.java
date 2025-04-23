package br.com.fillms.projeto_java_ia.repository;

import org.springframework.stereotype.Repository;

import br.com.fillms.projeto_java_ia.model.UserEntity;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<UserEntity, UUID> {
    // Implementação de métodos específicos para manipulação de usuários, se necessário
    // Exemplo: findByUsername, findByEmail, etc.
    
}
