package com.challenge.customerprospecting.entity;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import lombok.Data;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "LegalEntityCustomer")
public class LegalEntityCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String corporateName;

    @Column(nullable = false, length = 14)
    private String cnpj;

    @Column(nullable = false, length = 4)
    private String mcc;

    @Column(nullable = false, length = 50)
    private String contactName;

    @Column(nullable = false, length = 11)
    private String contactCpf;

    @Column(nullable = false)
    private String email;

    public LegalEntityCustomer(LegalEntityCustomerPostRequestDTO legalEntityCustomerPostRequestDTO) {
        this.setCorporateName(legalEntityCustomerPostRequestDTO.getCorporateName());
        this.setCnpj(legalEntityCustomerPostRequestDTO.getCnpj());
        this.setMcc(legalEntityCustomerPostRequestDTO.getMcc());
        this.setContactName(legalEntityCustomerPostRequestDTO.getContactName());
        this.setContactCpf(legalEntityCustomerPostRequestDTO.getContactCpf());
        this.setEmail(legalEntityCustomerPostRequestDTO.getEmail());
    }
}
