package br.com.fillms.projeto_java_ia.repository;

import br.com.fillms.projeto_java_ia.model.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<UserEntity,Long> {
    
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCpf(String cpf);

}
