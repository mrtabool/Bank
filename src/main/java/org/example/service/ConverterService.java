package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.dto.PaymentDTO;
import org.example.dto.PersonalClientData;
import org.example.model.Account;
import org.example.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterService {

    final private ModelMapper modelMapper;

    public PaymentDTO convertPaymentToDto(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPayment_id(payment.getPayment_id());
        paymentDTO.setTimestamp(payment.getCreated().toString());
        paymentDTO.setSrc_acc_num(payment.getSource().getAccount_num());
        paymentDTO.setDest_acc_num(payment.getDestination().getAccount_num());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPayer(
                new PersonalClientData(payment.getPayer().getFirst_name(), payment.getRecipient().getLast_name()));
        paymentDTO.setRecipient(
                new PersonalClientData(payment.getRecipient().getFirst_name(), payment.getRecipient().getLast_name()));
        paymentDTO.setStatus(payment.getStatus());

        return paymentDTO;
    }

    public AccountDto convertAccountToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }
}
