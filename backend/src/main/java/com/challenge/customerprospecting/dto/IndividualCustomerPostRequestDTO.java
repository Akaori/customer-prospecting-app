package com.challenge.customerprospecting.dto;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class IndividualCustomerPostRequestDTO {

    @NotBlank(message = "Nome inválido: nome vazio")
    @NotNull(message = "Nome inválido: nome nulo")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "MCC inválido: MCC vazio")
    @NotNull(message = "MCC inválido: MCC nulo")
    @Size(max = 4)
    private String mcc;

    @NotBlank(message = "CPF inválido: CPF vazio")
    @NotNull(message = "CPF inválido: CPF nulo")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank(message = "Email inválido: Email vazio")
    @NotNull(message = "Email inválido: Email nulo")
    @Email(message = "Email precisa ser válido")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email precisa ser válido")
    private String email;
}
