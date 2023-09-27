package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.service.impl.LegalEntityCustomerQueueServiceImpl;
import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LegalEntityCustomerQueueServiceTest {

    @InjectMocks
    private static LegalEntityCustomerQueueServiceImpl legalEntityCustomerQueueService;

    // Initialize the mocks and the queue before all tests
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        legalEntityCustomerQueueService.init();
    }

    @Test
    public void testEnqueueCustomer() {
        // Create a customer object with some sample data
        LegalEntityCustomer customer = new LegalEntityCustomer();
        customer.setId(1L);
        customer.setCorporateName("ABC Inc.");
        customer.setCnpj("12345678901239");
        customer.setMcc("0000");
        customer.setContactName("Teste");
        customer.setContactCpf("11111111111");
        customer.setEmail("abc@example.com");

        // Call the service method with the customer and assert that it does not throw an exception
        assertDoesNotThrow(() -> legalEntityCustomerQueueService.enqueueCustomer(customer));

        // Assert that the queue is not empty after enqueuing the customer
        assertFalse(legalEntityCustomerQueueService.isEmpty());

        // Assert that the customer is at the front of the queue after enqueuing it
        assertEquals(customer, legalEntityCustomerQueueService.peekCustomer());
    }

    @Test
    public void testDequeueCustomer() {
        // Create two customer objects with some sample data
        LegalEntityCustomer customer1 = new LegalEntityCustomer();
        customer1.setId(1L);
        customer1.setCorporateName("ABC Inc.");
        customer1.setCnpj("12345678901239");
        customer1.setMcc("0000");
        customer1.setContactName("Teste");
        customer1.setContactCpf("11111111111");
        customer1.setEmail("abc@example.com");

        LegalEntityCustomer customer2 = new LegalEntityCustomer();
        customer2.setId(2L);
        customer2.setCorporateName("DEF Inc.");
        customer2.setCnpj("12345678901234");
        customer2.setMcc("0001");
        customer2.setContactName("Teste");
        customer2.setContactCpf("22222222222");
        customer2.setEmail("abcd@example.com");

        // Enqueue the two customers to the service queue
        legalEntityCustomerQueueService.enqueueCustomer(customer1);
        legalEntityCustomerQueueService.enqueueCustomer(customer2);

        // Call the service method and assert that it returns the first customer
        LegalEntityCustomer result = legalEntityCustomerQueueService.dequeueCustomer();
        assertEquals(customer1, result);

        // Assert that the queue is not empty after dequeuing one customer
        assertFalse(legalEntityCustomerQueueService.isEmpty());

        // Assert that the second customer is now at the front of the queue after dequeuing the first one
        assertEquals(customer2, legalEntityCustomerQueueService.peekCustomer());
    }

    @Test
    public void testDequeueCustomerWhenEmpty() {
        // Assert that the queue is initially empty
        assertTrue(legalEntityCustomerQueueService.isEmpty());

        // Call the service method and assert that it returns null when the queue is empty
        LegalEntityCustomer result = legalEntityCustomerQueueService.dequeueCustomer();
        assertNull(result);
    }

    @Test
    public void testPeekCustomer() {
        // Create a customer object with some sample data
        LegalEntityCustomer customer = new LegalEntityCustomer();
        customer.setId(1L);
        customer.setCorporateName("ABC Inc.");
        customer.setCnpj("12345678901239");
        customer.setMcc("0000");
        customer.setContactName("Teste");
        customer.setContactCpf("11111111111");
        customer.setEmail("abc@example.com");

        // Enqueue the customer to the service queue
        legalEntityCustomerQueueService.enqueueCustomer(customer);

        // Call the service method and assert that it returns but does not remove the customer from the queue
        LegalEntityCustomer result = legalEntityCustomerQueueService.peekCustomer();
        assertEquals(customer, result);

        // Assert that the queue is not empty after peeking at the customer
        assertFalse(legalEntityCustomerQueueService.isEmpty());
    }

    @Test
    public void testPeekCustomerWhenEmpty() {
        // Assert that the queue is initially empty
        assertTrue(legalEntityCustomerQueueService.isEmpty());

        // Call the service method and assert that it returns null when the queue is empty
        LegalEntityCustomer result = legalEntityCustomerQueueService.peekCustomer();
        assertNull(result);
    }

    @Test
    public void testIsEmpty() {
        // Assert that the queue is initially empty
        assertTrue(legalEntityCustomerQueueService.isEmpty());

        // Create a customer object with some sample data and enqueue it to the service queue
        LegalEntityCustomer customer = new LegalEntityCustomer();
        customer.setId(1L);
        customer.setCorporateName("ABC Inc.");
        customer.setCnpj("12345678901239");
        customer.setMcc("0000");
        customer.setContactName("Teste");
        customer.setContactCpf("11111111111");
        customer.setEmail("abc@example.com");
        legalEntityCustomerQueueService.enqueueCustomer(customer);

        // Assert that the queue is not empty after enqueuing a customer
        assertFalse(legalEntityCustomerQueueService.isEmpty());

        // Dequeue the customer from the service queue
        legalEntityCustomerQueueService.dequeueCustomer();

        // Assert that the queue is empty again after dequeuing the customer
        assertTrue(legalEntityCustomerQueueService.isEmpty());
    }

}
