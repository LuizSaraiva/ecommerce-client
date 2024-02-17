package com.ecommerce.ecommerceclient.api.controller;

import com.ecommerce.ecommerceclient.api.mapper.ClientMapper;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientDisableRequest;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientRequest;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientResponse;
import com.ecommerce.ecommerceclient.domain.exception.ClientAlreadyExistsException;
import com.ecommerce.ecommerceclient.domain.model.Client;
import com.ecommerce.ecommerceclient.domain.service.ClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@Log4j2
public class ClientController implements ClientControllerApi {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;


    @Override
    public ResponseEntity<?> getAllClients() {

        List<Client> clients = clientService.getAllClients();
        log.info("Received request to get all Clients.");
        List<ClientResponse> clientResponseDto = clientMapper.listClientDomainToResponseDto(clients);
        log.info("Response request to get all Clients.");
        return ResponseEntity.ok(clientResponseDto);
    }

    @Override
    public ResponseEntity<?> getClientById(UUID id) {
        log.info("Received request to get client id {}.", id.toString());
        try{
            Client client = clientService.getClientById(id);
            ClientResponse clientResponse = clientMapper.clientDomainToResponseDto(client);
            log.info("Response request to get client: {}", clientResponse);
            return ResponseEntity.ok(clientResponse);
        }catch (Exception e){
            log.error(e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
    }

    @Override
    public ResponseEntity<?> createClient(ClientRequest clientRequest) {

        log.info("Received request to create client: {}", clientRequest);
        Client client = clientMapper.clientDtoToDomain(clientRequest);
        try{
            Client clientSaved = clientService.saveClient(client);
            ClientResponse clientResponse = clientMapper.clientDomainToResponseDto(clientSaved);
            log.info("Response created client successfully: {}", clientResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
        }catch (ClientAlreadyExistsException e){
            log.error(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateClient(ClientRequest clientRequest, UUID id) {
        log.info("Received client {} to update.", id.toString());
        Client client = clientService.getClientById(id);

        BeanUtils.copyProperties(clientRequest, client);

        Client clientSaved = clientService.updateClient(client);
        ClientResponse clientResponse = clientMapper.clientDomainToResponseDto(clientSaved);
        log.info("Response client updated: {}", clientResponse);
        return ResponseEntity.ok(clientResponse);
    }

    @Override
    public ResponseEntity<?> disableClient(ClientDisableRequest clientDisableRequest, UUID id) {
        clientService.updateStatusClient(clientDisableRequest.isStatus(), id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
