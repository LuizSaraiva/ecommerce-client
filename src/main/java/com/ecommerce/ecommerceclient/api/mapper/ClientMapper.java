package com.ecommerce.ecommerceclient.api.mapper;

import com.ecommerce.ecommerceclient.api.mapper.dto.ClientRequest;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientResponse;
import com.ecommerce.ecommerceclient.domain.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientRequest clientDomainToDto(Client client);
    Client clientDtoToDomain(ClientRequest clientRequest);
    ClientResponse clientDomainToResponseDto(Client client);
    List<ClientRequest> listClientDomainToDto(List<Client> clientList);
    List<Client> listClientDtoToDomain(List<ClientRequest> clientRequests);
    List<ClientResponse> listClientDomainToResponseDto(List<Client> clientList);

}
