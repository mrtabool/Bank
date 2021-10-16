package org.example.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    public Account(Long account_id) {
        this.account_id = account_id;
    }

    public Account(Account account) {
        this.account_num = account.getAccount_num();
        this.account_type = account.getAccount_type();
        this.balance = account.getBalance();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @Id
    @GeneratedValue
    @Column(name = "account_id", unique = true)
    private Long account_id;

    @Column(name = "account_num")
    @NotBlank(message = "Should be not blank.")
    private String account_num;

    @Column(name = "account_type")
    @NotBlank(message = "Should be not blank.")
    private String account_type;

    @Column(name = "balance")
    @Digits(integer = 8, fraction = 2, message = "Invalid digits structure. Should contain no more than two fractional digits.")
    private Float balance;


}
