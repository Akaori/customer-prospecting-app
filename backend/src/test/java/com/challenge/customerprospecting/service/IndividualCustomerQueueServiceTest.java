package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.service.impl.IndividualCustomerQueueServiceImpl;
import com.challenge.customerprospecting.entity.IndividualCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class IndividualCustomerQueueServiceTest {
    @InjectMocks
    private static IndividualCustomerQueueServiceImpl individualCustomerQueueService;


    // Initialize the mocks and the queue before all tests
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        individualCustomerQueueService.init();
    }


    @Test
    public void testEnqueueCustomer() {
        // Create a customer object with some sample data
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setMcc("1234");
        customer.setCpf("11111111111");
        customer.setEmail("john.doe@example.com");

        // Call the service method with the customer and assert that it does not throw an exception
        assertDoesNotThrow(() -> individualCustomerQueueService.enqueueCustomer(customer));

        // Assert that the queue is not empty after enqueuing the customer
        assertFalse(individualCustomerQueueService.isEmpty());

        // Assert that the customer is at the front of the queue after enqueuing it
        assertEquals(customer, individualCustomerQueueService.peekCustomer());
    }

    @Test
    public void testDequeueCustomer() {
        // Create two customer objects with some sample data
        IndividualCustomer customer1 = new IndividualCustomer();
        customer1.setId(1L);
        customer1.setName("John Doe");
        customer1.setMcc("1234");
        customer1.setCpf("11111111111");
        customer1.setEmail("john.doe@example.com");

        IndividualCustomer customer2 = new IndividualCustomer();
        customer2.setId(2L);
        customer2.setName("Jane Doe");
        customer2.setMcc("4321");
        customer2.setCpf("22222222222");
        customer2.setEmail("jane.doe@example.com");

        // Enqueue the two customers to the service queue
        individualCustomerQueueService.enqueueCustomer(customer1);
        individualCustomerQueueService.enqueueCustomer(customer2);

        // Call the service method and assert that it returns the first customer
        IndividualCustomer result = individualCustomerQueueService.dequeueCustomer();
        assertEquals(customer1, result);

        // Assert that the queue is not empty after dequeuing one customer
        assertFalse(individualCustomerQueueService.isEmpty());

        // Assert that the second customer is now at the front of the queue after dequeuing the first one
        assertEquals(customer2, individualCustomerQueueService.peekCustomer());
    }

    @Test
    public void testDequeueCustomerWhenEmpty() {
        // Assert that the queue is initially empty
        assertTrue(individualCustomerQueueService.isEmpty());

        // Call the service method and assert that it returns null when the queue is empty
        IndividualCustomer result = individualCustomerQueueService.dequeueCustomer();
        assertNull(result);
    }

    @Test
    public void testPeekCustomer() {
        // Create a customer object with some sample data
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setMcc("1234");
        customer.setCpf("11111111111");
        customer.setEmail("john.doe@example.com");

        // Enqueue the customer to the service queue
        individualCustomerQueueService.enqueueCustomer(customer);

        // Call the service method and assert that it returns but does not remove the customer from the queue
        IndividualCustomer result = individualCustomerQueueService.peekCustomer();
        assertEquals(customer, result);

        // Assert that the queue is not empty after peeking at the customer
        assertFalse(individualCustomerQueueService.isEmpty());
    }

    @Test
    public void testPeekCustomerWhenEmpty() {
        // Assert that the queue is initially empty
        assertTrue(individualCustomerQueueService.isEmpty());

        // Call the service method and assert that it returns null when the queue is empty
        IndividualCustomer result = individualCustomerQueueService.peekCustomer();
        assertNull(result);
    }

    @Test
    public void testIsEmpty() {
        // Assert that the queue is initially empty
        assertTrue(individualCustomerQueueService.isEmpty());

        // Create a customer object with some sample data and enqueue it to the service queue
        IndividualCustomer customer = new IndividualCustomer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setMcc("1234");
        customer.setCpf("11111111111");
        customer.setEmail("john.doe@example.com");
        individualCustomerQueueService.enqueueCustomer(customer);

        // Assert that the queue is not empty after enqueuing a customer
        assertFalse(individualCustomerQueueService.isEmpty());

        // Dequeue the customer from the service queue
        individualCustomerQueueService.dequeueCustomer();

        // Assert that the queue is empty again after dequeuing the customer
        assertTrue(individualCustomerQueueService.isEmpty());
    }
}
