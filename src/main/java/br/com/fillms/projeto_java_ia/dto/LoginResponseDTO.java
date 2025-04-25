package br.com.fillms.projeto_java_ia.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private String id;
    private String name;
    private String email;
    private String role;
    
}
