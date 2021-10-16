package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    public Payment(Client payer, Account source, Client recipient, Account destination, Float amount, String reason, String status) {
        this.payer = payer;
        this.source = source;
        this.recipient = recipient;
        this.destination = destination;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
    }

    @Id
    @Column(name = "payment_id", unique = true)
    @GeneratedValue
    private Long payment_id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "payer")
    private Client payer;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "source")
    private Account source;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient")
    private Client recipient;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "destination")
    private Account destination;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created = new Date(System.currentTimeMillis());

    @Column(name = "amount")
    @Digits(integer = 8, fraction = 2, message = "Invalid digits structure. Should contain no more than two fractional digits.")
    private Float amount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status;

}
