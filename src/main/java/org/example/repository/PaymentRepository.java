package org.example.repository;

import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    List<Payment> findByPayerAndSourceAndRecipientAndDestination(
            Client payer, Account source, Client recipient, Account destination
    );
}
