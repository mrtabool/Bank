package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long payment_id;

    private String timestamp;

    private String src_acc_num;

    private String dest_acc_num;

    private Float amount;

    private PersonalClientData payer;

    private PersonalClientData recipient;

    private String status;
}
