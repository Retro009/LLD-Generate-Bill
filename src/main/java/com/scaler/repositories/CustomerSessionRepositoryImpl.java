package com.scaler.repositories;

import com.scaler.models.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    List<CustomerSession> customerSessions = new ArrayList<>();
    private static long idCounter = 0;
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if(customerSession.getId()==0)
            customerSession.setId(idCounter++);
        customerSessions.add(customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessions.stream().filter(session -> session.getId() == userId && session.isActive()).findFirst();
    }
}
