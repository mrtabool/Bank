package org.example.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Payment;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class PaymentRequest {

    public PaymentRequest(PaymentRequest payment) {
        this.source_acc_id = payment.getSource_acc_id();
        this.dest_acc_id = payment.getDest_acc_id();
        this.amount = payment.getAmount();
        this.reason = payment.getReason();
    }

    @NotNull(message = "Should be not null.")
    private Long source_acc_id;

    @NotNull(message = "Should be not null.")
    private Long dest_acc_id;

    @Digits(integer = 8, fraction = 2, message = "Invalid digits structure.")
    private Float amount;

    private String reason;
}
