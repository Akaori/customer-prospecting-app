package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.dto.LegalEntityCustomerPostRequestDTO;
import com.challenge.customerprospecting.dto.LegalEntityCustomerPutRequestDTO;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.repository.LegalEntityCustomerRepository;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.exceptions.LegalEntityCustomerNotFoundException;
import com.challenge.customerprospecting.service.impl.LegalEntityCustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
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
        assertThrows(LegalEntityCustomerAlreadyExistsException.class, () -> legalEntityCustomerService.checkIfCustomerAlreadyExists(cnpj), "Customer with cnpj " + cnpj + " already exists");

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).findByCnpj(cnpj);
    }

    @Test
    public void testFindById() {
        // Create an id value to test with
        Long id = 1L;

        // Create an entity object with some sample data and the id value
        LegalEntityCustomer entity = new LegalEntityCustomer();
        entity.setId(id);
        entity.setCorporateName("ABC Inc.");
        entity.setCnpj("12345678901234");
        entity.setMcc("0000");
        entity.setContactName("Teste");
        entity.setContactCpf("11111111111");
        entity.setEmail("abc@example.com");

        // Stub the repository method to return an optional with the entity when finding by id
        when(legalEntityCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Call the service method with the id and assert that it returns the entity
        LegalEntityCustomer result = legalEntityCustomerService.findById(id);
        assertEquals(entity, result);

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).findById(id);
    }


    @Test
    public void testFindByIdThrowsException() {
        // Create an id value to test with
        Long id = 2L;

        // Stub the repository method to return an empty optional when finding by id
        when(legalEntityCustomerRepository.findById(id)).thenReturn(Optional.empty());

        // Call the service method with the id and assert that it throws an exception of the expected type and message
        assertThrows(LegalEntityCustomerNotFoundException.class, () -> legalEntityCustomerService.findById(id), "Customer with id " + id  + " not found");

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).findById(id);
    }


    @Test
    public void testUpdate() {
        // Create an id value to test with
        Long id = 3L;

        // Create a DTO object with some sample data
        LegalEntityCustomerPutRequestDTO dto = new LegalEntityCustomerPutRequestDTO();
        dto.setId(id);
        dto.setCorporateName("ABC Inc.");
        dto.setCnpj("12345678901234");
        dto.setMcc("0000");
        dto.setContactName("Teste");
        dto.setContactCpf("11111111111");
        dto.setEmail("abc@example.com");

        // Create an entity object with the same data and the id value
        LegalEntityCustomer entity = new LegalEntityCustomer(dto);
        entity.setId(id);

        // Stub the repository method to return the entity when saving
        when(legalEntityCustomerRepository.save(entity)).thenReturn(entity);

        // Stub the service method to return the entity when finding by id
        when(legalEntityCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Call the service method with the DTO and the id and assert that it returns the entity
        LegalEntityCustomer result = legalEntityCustomerService.update(dto, id);
        assertEquals(entity, result);

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).save(entity);
    }

    @Test
    public void testDelete() {
        // Create an id value to test with
        Long id = 1L;

        // Create an entity object with some sample data and the id value
        LegalEntityCustomer entity = new LegalEntityCustomer();
        entity.setId(id);
        entity.setCorporateName("ABC Inc.");
        entity.setCnpj("12345678901234");
        entity.setMcc("0000");
        entity.setContactName("Teste");
        entity.setContactCpf("11111111111");
        entity.setEmail("abc@example.com");

        when(legalEntityCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Stub the repository method to do nothing when deleting by id
        doNothing().when(legalEntityCustomerRepository).deleteById(id);

        // Call the service method with the id and assert that it does not throw an exception
        assertDoesNotThrow(() -> legalEntityCustomerService.delete(id));

        // Verify that the repository method was called once
        verify(legalEntityCustomerRepository, times(1)).deleteById(id);
    }
}
