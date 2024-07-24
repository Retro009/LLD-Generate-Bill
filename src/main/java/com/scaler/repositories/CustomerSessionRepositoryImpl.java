package com.scaler.repositories;

import com.scaler.models.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return customerSessions.stream().filter(session -> session.getUser().getId() == userId && session.isActive()).findFirst();
    }
}
