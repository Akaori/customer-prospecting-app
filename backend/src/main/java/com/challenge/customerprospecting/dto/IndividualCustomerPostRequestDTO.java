package com.challenge.customerprospecting.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerPostRequestDTO {

    @NotBlank(message = "Nome inválido: nome vazio")
    @NotNull(message = "Nome inválido: nome nulo")
    @Size(min = 1, max = 50, message = "Nome inválido: Mínimo 1 caracter e Máximo de 50 caracteres")
    private String name;

    @NotBlank(message = "MCC inválido: MCC vazio")
    @NotNull(message = "MCC inválido: MCC nulo")
    @Size(min = 4, max = 4, message = "MCC inválido: Precisa ter 4 caracteres")
    private String mcc;

    @NotBlank(message = "CPF inválido: CPF vazio")
    @NotNull(message = "CPF inválido: CPF nulo")
    @Size(min = 11, max = 11, message = "CPF inválido: Precisa ter 11 caracteres")
    private String cpf;

    @NotBlank(message = "Email inválido: Email vazio")
    @NotNull(message = "Email inválido: Email nulo")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email precisa ser válido")
    private String email;
}
