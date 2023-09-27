package com.challenge.customerprospecting.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerPutRequestDTO {

    @NotNull(message = "Id inválido: id nulo")
    @Schema(type = "number", example = "1")
    private Long id;

    @NotBlank(message = "Nome inválido: nome vazio")
    @NotNull(message = "Nome inválido: nome nulo")
    @Size(min = 1, max = 50, message = "Nome inválido: Mínimo 1 caracter e Máximo de 50 caracteres")
    @Schema(type = "string", example = "name")
    private String name;

    @NotBlank(message = "MCC inválido: MCC vazio")
    @NotNull(message = "MCC inválido: MCC nulo")
    @Size(min = 4, max = 4, message = "MCC inválido: Precisa ter 4 caracteres")
    @Schema(type = "string", example = "0000")
    private String mcc;

    @NotBlank(message = "CPF inválido: CPF vazio")
    @NotNull(message = "CPF inválido: CPF nulo")
    @Size(min = 11, max = 11, message = "CPF inválido: Precisa ter 11 caracteres")
    @Schema(type = "string", example = "21849889015")
    private String cpf;

    @NotBlank(message = "Email inválido: Email vazio")
    @NotNull(message = "Email inválido: Email nulo")
    @Email(message = "Email precisa ser válido")
    @Schema(type = "string", example = "example@example.com")
    private String email;
}
