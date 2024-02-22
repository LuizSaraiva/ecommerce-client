package com.ecommerce.ecommerceclient.domain.service.Impl;

import com.ecommerce.ecommerceclient.api.mapper.ClientMapper;
import com.ecommerce.ecommerceclient.domain.exception.ClientAlreadyExistsException;
import com.ecommerce.ecommerceclient.domain.exception.ClientNotFoundException;
import com.ecommerce.ecommerceclient.domain.model.Client;
import com.ecommerce.ecommerceclient.domain.repository.ClientRepository;
import com.ecommerce.ecommerceclient.domain.service.ClientService;
import com.ecommerce.ecommerceclient.enums.ActionType;
import com.ecommerce.ecommerceclient.publishers.ClientEventPublisher;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientEventPublisher clientEventPublisher;

    @Autowired
    private ClientMapper clientMapper;

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
        log.info("Saving client.");
        try{
            Client clientSaved = clientRepository.save(client);
            clientRepository.flush();
            clientEventPublisher.publisherClientEvent(clientMapper.clientDomainToEventDto(clientSaved), ActionType.CREATE);
            return clientSaved;
        }catch (DataIntegrityViolationException e){
            throw new ClientAlreadyExistsException(
                    String.format("Client already exists with CPF %s",client.getCpf().toString()));
        }
    }

    @Override
    @Transactional
    public void updateStatusClient(boolean status, UUID id) {
        Client client = getClientById(id);
        client.setStatus(status);
        Client clientSaved = clientRepository.save(client);
        clientEventPublisher.publisherClientEvent(clientMapper.clientDomainToEventDto(clientSaved), ActionType.UPDATE);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        log.info("Saving client update.");
        try{
            Client clientSaved = clientRepository.save(client);
            clientRepository.flush();
            clientEventPublisher.publisherClientEvent(clientMapper.clientDomainToEventDto(clientSaved), ActionType.UPDATE);
            return clientSaved;
        }catch (DataIntegrityViolationException e){
            throw new ClientAlreadyExistsException(
                    String.format("Client already exists with CPF %s",client.getCpf().toString()));
        }
    }

}
