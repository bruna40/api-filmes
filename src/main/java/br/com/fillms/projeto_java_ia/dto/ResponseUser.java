package br.com.fillms.projeto_java_ia.dto;

public record ResponseUser(
        String username,
        String name,
        String lastName,
        String email,
        String phone,
        String cpf,
        Boolean active
) {
 
}
