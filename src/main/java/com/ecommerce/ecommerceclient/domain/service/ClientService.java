package com.ecommerce.ecommerceclient.domain.service;

import com.ecommerce.ecommerceclient.domain.model.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    List<Client> getAllClients();
    Client getClientById(UUID id);
    Client saveClient(Client client);
    void updateStatusClient(boolean status, UUID id);
    Client updateClient(Client client);

    Optional<Client> getClientByCpf(String cpf);

}
