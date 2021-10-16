package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.dto.PaymentDTO;
import org.example.exception.EntityNotFoundException;
import org.example.exception.InsufficientFundsException;
import org.example.model.Account;
import org.example.model.Client;
import org.example.model.Payment;
import org.example.repository.AccountRepository;
import org.example.repository.ClientRepository;
import org.example.repository.PaymentRepository;
import org.example.request.PaymentJournalRequest;
import org.example.request.PaymentRequest;
import org.example.response.ClientResponse;
import org.example.response.PaymentResponse;
import org.example.response.PaymentStatusResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClientService {

    final private AccountRepository accountRepository;
    final private ConverterService converterService;
    final private ClientRepository clientRepository;
    final private PaymentRepository paymentRepository;
    final private ObjectMapper objectMapper;

    public ResponseEntity<ClientResponse> create(Client client) {
        Client newClient = new Client(client);
        List<Account> accountList = new ArrayList<>();
        for (Account a: client.getAccounts()) {
            Account newAccount = new Account(a);
            newAccount.setClient(newClient);
            accountList.add(newAccount);
        }
        newClient.setAccounts(accountList);
        System.out.println(newClient);
        Client save = clientRepository.save(newClient);
        System.out.println("client_id is: " + save.getClient_id());
        return new ResponseEntity<>(new ClientResponse(save), HttpStatus.CREATED);
    }

    public List<AccountDto> findAccounts(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException(Client.class, clientId));
        Collection<Account> accounts = client.getAccounts();

        return accounts
                .stream()
                .map(converterService::convertAccountToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PaymentResponse> createPayment(PaymentRequest paymentRequest) {
        Payment payment = create(paymentRequest);
        if (payment.getStatus().equals("error")) throw new InsufficientFundsException();
        return new ResponseEntity<PaymentResponse>(new PaymentResponse(payment.getPayment_id()), HttpStatus.CREATED);
    }

    public ResponseEntity<List<PaymentStatusResponse>> createPaymentPack(List<PaymentRequest> paymentRequests) {
        List<PaymentStatusResponse> paymentStatusResponseList = new ArrayList<>();
        for (PaymentRequest paymentRequest : paymentRequests) {
            Payment payment = create(paymentRequest);
            paymentStatusResponseList.add(new PaymentStatusResponse(payment.getPayment_id(),
                    payment.getStatus().equals("ok") ? "ok" : "error"));
        }
        return new ResponseEntity<>(paymentStatusResponseList, HttpStatus.OK);
    }

    private Payment create(PaymentRequest paymentRequest) {
        Long source_acc_id = paymentRequest.getSource_acc_id();
        Long dest_acc_id = paymentRequest.getDest_acc_id();
        Float amount = paymentRequest.getAmount();

        Account source = accountRepository.findById(source_acc_id)
                .orElseThrow(() -> new EntityNotFoundException(Account.class, source_acc_id));
        Account destination = accountRepository.findById(dest_acc_id)
                .orElseThrow(() -> new EntityNotFoundException(Account.class, dest_acc_id));

        Float sourceBalance = source.getBalance();
        Float destinationBalance = destination.getBalance();

        if (sourceBalance < amount) {
            Payment error = new Payment(
                    source.getClient(),
                    source,
                    destination.getClient(),
                    destination,
                    amount,
                    paymentRequest.getReason(),
                    "error");
            return paymentRepository.save(error);
        }

        source.setBalance(sourceBalance - amount);
        destination.setBalance(destinationBalance + amount);

        Payment ok = new Payment(
                source.getClient(),
                source,
                destination.getClient(),
                destination,
                amount,
                paymentRequest.getReason(),
                "ok");

        return paymentRepository.save(ok);
    }

    public ResponseEntity<List<PaymentDTO>> findPayments(PaymentJournalRequest paymentJournalRequest) {


        List<Payment> paymentsList = paymentRepository
                .findByPayerAndSourceAndRecipientAndDestination
                        (
                                new Client(paymentJournalRequest.getPayer_id()),
                                new Account(paymentJournalRequest.getSource_acc_id()),
                                new Client(paymentJournalRequest.getRecipient_id()),
                                new Account(paymentJournalRequest.getDest_acc_id())
                        );

        List<PaymentDTO> paymentDTOList = paymentsList
                .stream()
                .map((payment) -> converterService.convertPaymentToDto(payment))
                .collect(Collectors.toList());

        return new ResponseEntity<>(paymentDTOList, HttpStatus.OK);
    }
}
