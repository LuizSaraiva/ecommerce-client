package com.ecommerce.ecommerceclient.domain.exception;

import java.util.UUID;

public class ClientAlreadyExistsException extends BusinessException{

    private static final String CLIENT_CPF_ALREADY_EXISTS = "Client already exists with id %s.";

    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException(UUID id) {
        this(String.format(CLIENT_CPF_ALREADY_EXISTS, id.toString()));
    }
}
