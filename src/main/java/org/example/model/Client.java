package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    public Client(Long client_id) {
        this.client_id = client_id;
    }

    public Client(Client client) {
        this.first_name = client.getFirst_name();
        this.last_name = client.getLast_name();
    }

    @Id
    @Column(name = "client_id", unique = true)
    @GeneratedValue
    private Long client_id;

    @Column(name = "first_name")
    @NotBlank(message = "Should be not blank")
    private String first_name;

    @Column(name = "last_name")
    @NotBlank(message = "Should be not blank")
    private String last_name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Collection<Account> accounts;

}
