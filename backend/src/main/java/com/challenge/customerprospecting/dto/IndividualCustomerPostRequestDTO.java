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
    @Max(value = 50, message = "Nome inválido: Excede 50 caracteres")
    private String name;

    @NotBlank(message = "MCC inválido: MCC vazio")
    @NotNull(message = "MCC inválido: MCC nulo")
    @Max(value = 4, message = "MCC inválido: Máximo de 4 caracteres")
    @Min(value = 4, message = "MCC inválido: Mínimo de 4 caracteres")
    private String mcc;

    @NotBlank(message = "CPF inválido: CPF vazio")
    @NotNull(message = "CPF inválido: CPF nulo")
    @Max(value = 11, message = "CPF inválido: Máximo de 11 caracteres")
    @Min(value = 11, message = "CPF inválido: Mínimo de 11 caracteres")
    private String cpf;

    @NotBlank(message = "Email inválido: Email vazio")
    @NotNull(message = "Email inválido: Email nulo")
    @Email(message = "Email precisa ser válido")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email precisa ser válido")
    private String email;
}
