package com.challenge.customerprospecting.service;

import com.challenge.customerprospecting.entity.LegalEntityCustomer;

public interface LegalEntityCustomerQueueService {

    void enqueueCustomer(LegalEntityCustomer customer);

    LegalEntityCustomer dequeueCustomer();

    LegalEntityCustomer peekCustomer();

    boolean isEmpty();
}
