package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.repository.LegalEntityCustomerRepository;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.impl.LegalEntityCustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Collections;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LegalEntityCustomerServiceTest {

    @Mock
    private LegalEntityCustomerRepository legalEntityCustomerRepository;

    @InjectMocks
    private LegalEntityCustomerServiceImpl legalEntityCustomerService;


    @Test
    public void testSave() {
        // Create a DTO object with some sample data
        LegalEntityCustomerPostRequestDTO dto = new LegalEntityCustomerPostRequestDTO().builder()
                .corporateName("ABC Inc.")
                .cnpj("12345678901234")
                .mcc("0000")
                .contactName("Teste")
                .contactCpf("11111111111")
                .email("abc@example.com")
                .build();

        // Create an entity object with the same data
        LegalEntityCustomer entity = new LegalEntityCustomer(dto);

        // Stub the repository method to return the entity when saving
        when(legalEntityCustomerRepository.save(entity)).thenReturn(entity);

        // Call the service method with the DTO and assert that it returns the entity
        LegalEntityCustomer result = legalEntityCustomerService.save(dto);
        assertEquals(entity, result);

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).save(entity);
    }

    @Test
    public void testCheckIfCustomerAlreadyExists() {
        // Create a CNPJ value to test with
        String cnpj = "98765432109876";

        // Stub the repository method to return an empty list when finding by CNPJ
        when(legalEntityCustomerRepository.findByCnpj(cnpj)).thenReturn(Collections.emptyList());

        // Call the service method with the CNPJ and assert that it does not throw an exception
        assertDoesNotThrow(() -> legalEntityCustomerService.checkIfCustomerAlreadyExists(cnpj));

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    public void testCheckIfCustomerAlreadyExistsThrowsException() {
        // Create a CNPJ value to test with
        String cnpj = "55555555555555";

        // Create a DTO object with some sample data
        LegalEntityCustomerPostRequestDTO dto = new LegalEntityCustomerPostRequestDTO().builder()
                .corporateName("ABC Inc.")
                .cnpj(cnpj)
                .mcc("0000")
                .contactName("Teste")
                .contactCpf("11111111111")
                .email("abc@example.com")
                .build();

        // Create an entity object with some sample data and the CNPJ value
        LegalEntityCustomer entity = new LegalEntityCustomer(dto);

        // Stub the repository method to return a list with one element when finding by CNPJ
        when(legalEntityCustomerRepository.findByCnpj(cnpj)).thenReturn(Collections.singletonList(entity));

        // Call the service method with the CNPJ and assert that it throws an exception of the expected type and message
        assertThrows(LegalEntityCustomerAlreadyExistsException.class, () -> legalEntityCustomerService.checkIfCustomerAlreadyExists(cnpj), "Legal entity customer already exists: " + cnpj);

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).findByCnpj(cnpj);
    }
}
