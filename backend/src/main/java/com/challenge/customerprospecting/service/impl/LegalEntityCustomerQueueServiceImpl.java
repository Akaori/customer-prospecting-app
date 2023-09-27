package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.entity.LegalEntityCustomer;
import com.challenge.customerprospecting.service.LegalEntityCustomerQueueService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;


@Service
public class LegalEntityCustomerQueueServiceImpl implements LegalEntityCustomerQueueService {
    // Use an ArrayDeque to store the customers in FIFO order
    private ArrayDeque<LegalEntityCustomer> queue;

    // Initialize the queue in a @PostConstruct method
    @PostConstruct
    public void init() {
        queue = new ArrayDeque<>();
    }

    @Override
    public void enqueueCustomer(LegalEntityCustomer customer) {
        // Add the customer at the end of the queue
        queue.addLast(customer);
    }

    @Override
    public LegalEntityCustomer dequeueCustomer() {
        // Remove and return the customer from the front of the queue, or return null if the queue is empty
        return queue.pollFirst();
    }

    @Override
    public LegalEntityCustomer peekCustomer() {
        // Return but not remove the customer from the front of the queue, or return null if the queue is empty
        return queue.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        // Return true if the queue is empty, false otherwise
        return queue.isEmpty();
    }
}
