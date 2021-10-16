package org.example.response;

import lombok.Data;
import org.example.model.Client;

@Data
public class ClientResponse {

    private Long client_id;

    public ClientResponse(Client client) {
        this.client_id = client.getClient_id();
    }

}
