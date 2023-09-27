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
public class LegalEntityCustomerPutRequestDTO {

    @NotNull(message = "Id inválido: id nulo")
    @Schema(type = "number", example = "1")
    private Long id;

    @NotBlank(message = "'Razão Social' inválida: 'Razão Social' vazio")
    @NotNull(message = "'Razão Social' inválida: 'Razão Social' nulo")
    @Size(min = 1, max = 50, message = "'Razão Social' inválida: Mínimo 1 caracter e Máximo de 50 caracteres")
    @Schema(type = "string", example = "name")
    private String corporateName;

    @NotBlank(message = "'CNPJ' inválido: 'CNPJ' vazio")
    @NotNull(message = "'CNPJ' inválido: 'CNPJ' nulo")
    @Size(min = 14, max = 14, message = "CNPJ inválido: Precisa ter 14 caracteres")
    @Schema(type = "string", example = "00000000000000")
    private String cnpj;

    @NotBlank(message = "MCC inválido: MCC vazio")
    @NotNull(message = "MCC inválido: MCC nulo")
    @Size(min = 4, max = 4, message = "MCC inválido: Precisa ter 4 caracteres")
    @Schema(type = "string", example = "0000")
    private String mcc;

    @NotBlank(message = "'Nome do contato' inválido: 'Nome do contato' vazio")
    @NotNull(message = "'Nome do contato' inválido: 'Nome do contato' nulo")
    @Size(min = 1, max = 50, message = "'Nome do contato' inválido: Mínimo 1 caracter e Máximo de 50 caracteres")
    @Schema(type = "string", example = "name")
    private String contactName;

    @NotBlank(message = "'CPF de contato do estabelecimento' inválido: 'CPF de contato do estabelecimento' vazio")
    @NotNull(message = "'CPF de contato do estabelecimento' inválido: 'CPF de contato do estabelecimento' nulo")
    @Size(min = 11, max = 11, message = "'CPF de contato do estabelecimento' inválido: Precisa ter 11 caracteres")
    @Schema(type = "string", example = "21849889015")
    private String contactCpf;

    @NotBlank(message = "Email inválido: Email vazio")
    @NotNull(message = "Email inválido: Email nulo")
    @Email(message = "Email precisa ser válido")
    @Schema(type = "string", example = "example@example.com")
    private String email;
}
