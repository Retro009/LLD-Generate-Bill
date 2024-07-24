package com.scaler.repositories;

import com.scaler.models.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    List<CustomerSession> customerSessions = new ArrayList<>();
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        customerSessions.add(customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessions.stream().filter(session -> session.getId() == userId && session.isActive()).findFirst();
    }
}
