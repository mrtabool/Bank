package org.example.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentJournalRequest {

    @NotNull
    private Long payer_id;

    @NotNull
    private Long source_acc_id;

    @NotNull
    private Long recipient_id;

    @NotNull
    private Long dest_acc_id;
}
