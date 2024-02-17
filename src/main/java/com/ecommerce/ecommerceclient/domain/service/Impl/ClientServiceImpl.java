package com.ecommerce.ecommerceclient.domain.service.Impl;

import com.ecommerce.ecommerceclient.api.mapper.ClientMapper;
import com.ecommerce.ecommerceclient.domain.exception.ClientAlreadyExistsException;
import com.ecommerce.ecommerceclient.domain.exception.ClientNotFoundException;
import com.ecommerce.ecommerceclient.domain.model.Client;
import com.ecommerce.ecommerceclient.domain.repository.ClientRepository;
import com.ecommerce.ecommerceclient.domain.service.ClientService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ClientNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public Client saveClient(Client client) {

        Optional<Client> clientFound = clientRepository.findClientByCpf(client.getCpf());

        if(clientFound.isPresent()){
            throw (new ClientAlreadyExistsException(clientFound.get().getId()));
        }

        log.info("Saving client.");
        Client clientSaved = clientRepository.save(client);
        return clientSaved;
    }

    @Override
    @Transactional
    public void updateStatusClient(boolean status, UUID id) {
        Client client = getClientById(id);
        client.setStatus(status);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        log.info("Saving Client.");
        Client clientSaved = clientRepository.save(client);
        return clientSaved;
    }

    @Override
    public Optional<Client> getClientByCpf(String cpf) {
        return clientRepository.findClientByCpf(cpf);
    }
}
