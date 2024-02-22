package com.ecommerce.ecommerceclient.api.mapper.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ClientEventDto {

    private UUID id;
    private String name;
    private String cpf;
    private Timestamp created_at;
    private boolean status;
    private String actionType;
}
