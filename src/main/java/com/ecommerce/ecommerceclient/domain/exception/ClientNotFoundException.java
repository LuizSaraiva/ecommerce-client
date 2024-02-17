package com.ecommerce.ecommerceclient.domain.exception;

import java.util.UUID;

public class ClientNotFoundException extends BusinessException{

    private static final String CLIENT_NOT_FOUND = "Client id %s not found!";

    public ClientNotFoundException(String message){
        super(message);
    }

    public ClientNotFoundException(UUID id){
        this(String.format(CLIENT_NOT_FOUND, id.toString()));
    }
}
