package br.com.fillms.projeto_java_ia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String lastName;
    private String birthDate;
    private String phone;

}
