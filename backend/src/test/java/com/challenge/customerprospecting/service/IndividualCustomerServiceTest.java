package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.service.exceptions.IndividualCustomerAlreadyExistsException;
import com.challenge.customerprospecting.service.impl.IndividualCustomerQueueServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;
import static org.mockito.Mockito.*;

import com.challenge.customerprospecting.repository.IndividualCustomerRepository;
import com.challenge.customerprospecting.service.impl.IndividualCustomerServiceImpl;
import com.challenge.customerprospecting.dto.IndividualCustomerPostRequestDTO;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.service.exceptions.IndividualCustomerNotFoundException;
import com.challenge.customerprospecting.dto.IndividualCustomerPutRequestDTO;

@ExtendWith(MockitoExtension.class)
public class IndividualCustomerServiceTest {
    @Mock
    private IndividualCustomerRepository individualCustomerRepository;

    @InjectMocks
    private IndividualCustomerServiceImpl individualCustomerService;

    @Mock
    private IndividualCustomerQueueServiceImpl individualCustomerQueueService;

    @Test
    public void testFindAll() {
        // Create a list of entity objects with some sample data
        List<IndividualCustomer> entities = new ArrayList<>();

        IndividualCustomer entity1 = new IndividualCustomer();
        entity1.setId(1L);
        entity1.setName("John Doe");
        entity1.setMcc("1234");
        entity1.setCpf("11111111111");
        entity1.setEmail("john.doe@example.com");

        IndividualCustomer entity2 = new IndividualCustomer();
        entity2.setId(2L);
        entity2.setName("John Doe");
        entity2.setMcc("1234");
        entity2.setCpf("11111111111");
        entity2.setEmail("john.doe@example.com");

        entities.add(entity1);
        entities.add(entity2);

        // Stub the repository method to return the list when finding all
        when(individualCustomerRepository.findAll()).thenReturn(entities);

        // Call the service method and assert that it returns the same list
        List<IndividualCustomer> result = individualCustomerService.findAll();
        assertEquals(entities, result);

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).findAll();
    }

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

        // Verify the interactions with the queue mock
        verify(individualCustomerQueueService, times(1)).enqueueCustomer(result);

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

    @Test
    public void testFindById() {
        // Create an id value to test with
        Long id = 1L;

        // Create an entity object with some sample data and the id value
        IndividualCustomer entity = new IndividualCustomer();
        entity.setId(id);
        entity.setName("John Doe");
        entity.setMcc("1234");
        entity.setCpf("11111111111");
        entity.setEmail("john.doe@example.com");

        // Stub the repository method to return an optional with the entity when finding by id
        when(individualCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Call the service method with the id and assert that it returns the entity
        IndividualCustomer result = individualCustomerService.findById(id);
        assertEquals(entity, result);

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).findById(id);
    }

    // Write a test for the findById method when it throws an exception
    @Test
    public void testFindByIdThrowsException() {
        // Create an id value to test with
        Long id = 2L;

        // Stub the repository method to return an empty optional when finding by id
        when(individualCustomerRepository.findById(id)).thenReturn(Optional.empty());

        // Call the service method with the id and assert that it throws an exception of the expected type and message
        assertThrows(IndividualCustomerNotFoundException.class, () -> individualCustomerService.findById(id), "Customer with id " + id  + " not found");

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).findById(id);
    }

    @Test
    public void testUpdate() {
        // Create an id value to test with
        Long id = 3L;

        // Create a DTO object with some sample data
        IndividualCustomerPutRequestDTO dto = new IndividualCustomerPutRequestDTO().builder()
                .id(id)
                .name("Jane Doe")
                .mcc("0000")
                .cpf("22222222222")
                .email("jane.doe@example.com")
                .build();

        // Create an entity object with the same data and the id value
        IndividualCustomer entity = new IndividualCustomer(dto);
        entity.setId(id);

        // Stub the repository method to return the entity when saving
        when(individualCustomerRepository.save(entity)).thenReturn(entity);

        // Stub the service method to return the entity when finding by id
        when(individualCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Call the service method with the DTO and the id and assert that it returns the entity
        IndividualCustomer result = individualCustomerService.update(dto, id);
        assertEquals(entity, result);

        // Verify the interactions with the queue mock
        verify(individualCustomerQueueService, times(1)).enqueueCustomer(result);

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).save(entity);
    }

    @Test
    public void testDelete() {
        // Create an id value to test with
        Long id = 3L;

        // Create an entity object with some sample data and the id value
        IndividualCustomer entity = new IndividualCustomer();
        entity.setId(id);
        entity.setName("John Doe");
        entity.setMcc("1234");
        entity.setCpf("11111111111");
        entity.setEmail("john.doe@example.com");

        when(individualCustomerRepository.findById(id)).thenReturn(Optional.of(entity));

        // Stub the repository method to do nothing when deleting by id
        doNothing().when(individualCustomerRepository).deleteById(id);

        // Call the service method with the id and assert that it does not throw an exception
        assertDoesNotThrow(() -> individualCustomerService.delete(id));

        // Verify that the repository method was called once
        verify(individualCustomerRepository, times(1)).deleteById(id);
    }
}
