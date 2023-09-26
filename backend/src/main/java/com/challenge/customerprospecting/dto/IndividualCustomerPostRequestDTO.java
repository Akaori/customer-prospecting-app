package com.challenge.customerprospecting.dto;


import lombok.Data;

@Data
public class IndividualCustomerPostRequestDTO {
    private String name;
    private String mcc;
    private String cpf;
    private String email;
}
