package br.com.fillms.projeto_java_ia.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fillms.projeto_java_ia.enums.RoleEnum;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {
  
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    @NotNull
    private String username;
    private String name;
    private String lastName;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String password;
    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotNull
    private String email;
    private RoleEnum role;

    @NotNull
    private String phone;
    @NotNull
    @Column(unique = true)
    private String cpf;
    private Boolean active;


}
