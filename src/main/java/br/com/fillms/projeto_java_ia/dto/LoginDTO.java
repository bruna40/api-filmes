package br.com.fillms.projeto_java_ia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LoginDTO {
    @NotBlank(message = "Username or Email is required")
    private String login;
    @NotBlank(message = "Password is required")
    private String password;

}
