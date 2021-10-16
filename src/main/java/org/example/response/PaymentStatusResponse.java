package org.example.response;

import lombok.Data;

@Data
public class PaymentStatusResponse {

    private Long payment_id;

    private String status;

    public PaymentStatusResponse(Long payment_id, final String status) {
        this.payment_id = payment_id;
        this.status = status;
    }

    public static PaymentStatusResponse of(Long payment_id, final String status) {
        return new PaymentStatusResponse(payment_id, status);
    }

}
