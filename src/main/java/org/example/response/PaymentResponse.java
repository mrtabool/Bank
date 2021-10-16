package org.example.response;

import lombok.Data;

@Data
public class PaymentResponse {

    private Long payment_id;

    public PaymentResponse(Long paymentId) {
        this.payment_id = paymentId;
    }
}
