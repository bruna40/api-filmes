package br.com.fillms.projeto_java_ia.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

import br.com.fillms.projeto_java_ia.enums.RoleEnum;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String password;
    private String email;
    private RoleEnum role;
    // @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Filme> filmes;

    private String phone;
    private String cpf;
    private Boolean active;

}
