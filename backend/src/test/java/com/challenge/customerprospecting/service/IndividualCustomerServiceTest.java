package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.service.exceptions.IndividualCustomerAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Collections;
import static org.mockito.Mockito.*;

import com.challenge.customerprospecting.repository.IndividualCustomerRepository;
import com.challenge.customerprospecting.service.impl.IndividualCustomerServiceImpl;
import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;

@ExtendWith(MockitoExtension.class)
public class IndividualCustomerServiceTest {
    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @InjectMocks
    private IndividualCustomerServiceImpl individualCustomerService;


    @Test
    public void testSave() {
        // Create a DTO object with some sample data
        IndividualCustomerPostRequestDTO dto = new IndividualCustomerPostRequestDTO().builder()
                .name("Teste")
                .mcc("1234")
                .cpf("11111111111")
                .email("teste@teste.com")
                .build();

        // Create an entity object with the same data
        IndividualCustomer entity = new IndividualCustomer(dto);

        // Stub the repository method to return the entity when saving
        when(individualCustomerRepository.save(entity)).thenReturn(entity);

        // Call the service method with the DTO and assert that it returns the entity
        IndividualCustomer result = individualCustomerService.save(dto);
        assertEquals(entity, result);

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).save(entity);
    }

    @Test
    public void testCheckIfCustomerAlreadyExists() {
        // Create a CPF value to test with
        String cpf = "11111111111";

        // Stub the repository method to return an empty list when finding by CPF
        when(individualCustomerRepository.findByCpf(cpf)).thenReturn(Collections.emptyList());

        // Call the service method with the CPF and assert that it does not throw an exception
        assertDoesNotThrow(() -> individualCustomerService.checkIfCustomerAlreadyExists(cpf));

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testCheckIfCustomerAlreadyExistsThrowsException() {
        // Create a CPF value to test with
        String cpf = "11111111111";

        // Create an entity object with some sample data and the CPF value
        IndividualCustomerPostRequestDTO dto = new IndividualCustomerPostRequestDTO().builder()
                .name("Teste")
                .mcc("1234")
                .cpf(cpf)
                .email("teste@teste.com")
                .build();


        // Create an entity object with the same data
        IndividualCustomer entity = new IndividualCustomer(dto);

        // Stub the repository method to return a list with one element when finding by CPF
        when(individualCustomerRepository.findByCpf(cpf)).thenReturn(Collections.singletonList(entity));

        // Call the service method with the CPF and assert that it throws an exception of the expected type and message
        assertThrows(IndividualCustomerAlreadyExistsException.class, () -> individualCustomerService.checkIfCustomerAlreadyExists(cpf), "Customer with cpf " + cpf + " already exists");

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).findByCpf(cpf);
    }
}
