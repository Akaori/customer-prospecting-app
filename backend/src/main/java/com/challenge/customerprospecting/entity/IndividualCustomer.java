package com.challenge.customerprospecting.entity;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "IndividualCustomer")
public class IndividualCustomer extends Customer {

    @NotBlank(message = "O campo 'Nome' é obrigatório")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "O campo 'CPF' é obrigatório")
    @Size(min = 11, max = 11)
    private String cpf;

    public IndividualCustomer(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO) {
        this.setName(individualCustomerPostRequestDTO.getName());
        this.setMcc(individualCustomerPostRequestDTO.getMcc());
        this.setCpf(individualCustomerPostRequestDTO.getCpf());
        this.setEmail(individualCustomerPostRequestDTO.getEmail());
    }
}
