package com.challenge.customerprospecting.entity;

import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;
import lombok.Data;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "IndividualCustomer")
public class IndividualCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 4)
    private String mcc;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private String email;

    public IndividualCustomer(IndividualCustomerPostRequestDTO individualCustomerPostRequestDTO) {
        this.setName(individualCustomerPostRequestDTO.getName());
        this.setMcc(individualCustomerPostRequestDTO.getMcc());
        this.setCpf(individualCustomerPostRequestDTO.getCpf());
        this.setEmail(individualCustomerPostRequestDTO.getEmail());
    }

    public IndividualCustomer(IndividualCustomerPutRequestDTO individualCustomerPutRequestDTO) {
        this.setId(individualCustomerPutRequestDTO.getId());
        this.setName(individualCustomerPutRequestDTO.getName());
        this.setMcc(individualCustomerPutRequestDTO.getMcc());
        this.setCpf(individualCustomerPutRequestDTO.getCpf());
        this.setEmail(individualCustomerPutRequestDTO.getEmail());
    }
}
