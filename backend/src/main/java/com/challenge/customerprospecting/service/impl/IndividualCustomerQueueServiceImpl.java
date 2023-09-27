package com.challenge.customerprospecting.service.impl;

import com.challenge.customerprospecting.entity.IndividualCustomer;
import com.challenge.customerprospecting.service.IndividualCustomerQueueService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;


@Service
public class IndividualCustomerQueueServiceImpl implements IndividualCustomerQueueService {

    // Use an ArrayDeque to store the customers in FIFO order
    private ArrayDeque<IndividualCustomer> queue;


    // Initialize the queue in a @PostConstruct method
    @PostConstruct
    public void init() {
        queue = new ArrayDeque<>();
    }

    @Override
    public void enqueueCustomer(IndividualCustomer customer) {
        // Add the customer at the end of the queue
        queue.addLast(customer);
    }

    @Override
    public IndividualCustomer dequeueCustomer() {
        // Remove and return the customer from the front of the queue, or return null if the queue is empty
        return queue.pollFirst();
    }

    @Override
    public IndividualCustomer peekCustomer() {
        // Return but not remove the customer from the front of the queue, or return null if the queue is empty
        return queue.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        // Return true if the queue is empty, false otherwise
        return queue.isEmpty();
    }
}
