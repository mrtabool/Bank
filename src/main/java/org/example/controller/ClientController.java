package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.dto.PaymentDTO;
import org.example.model.Client;
import org.example.request.PaymentJournalRequest;
import org.example.request.PaymentRequest;
import org.example.response.ClientResponse;
import org.example.response.PaymentResponse;
import org.example.response.PaymentStatusResponse;
import org.example.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/client")
@RestController
public class ClientController {

    final private ClientService clientService;

    @PostMapping(path = "/createClient",
            produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody Client client) {
        return clientService.create(client);
    }

    @GetMapping(path = "/findAccounts",
            produces = {"application/xml", "application/json"})
    public List<AccountDto> findAccounts(@NotNull(message = "Should be not null.") @RequestParam Long clientId) {
        return clientService.findAccounts(clientId);
    }

    @PostMapping(path = "/createPayment",
            produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return clientService.createPayment(paymentRequest);
    }

    @PostMapping(path = "createPaymentBatch",
            produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<PaymentStatusResponse>> createPaymentBatch(@Valid @RequestBody List<PaymentRequest> paymentRequests) {
        return clientService.createPaymentPack(paymentRequests);
    }

    @PostMapping(path = "/findPayments",
            produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public ResponseEntity<List<PaymentDTO>> findPayments(@RequestBody PaymentJournalRequest paymentJournalRequest) {
        return clientService.findPayments(paymentJournalRequest);
    }

}
