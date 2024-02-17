package com.ecommerce.ecommerceclient.api.mapper.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ClientResponse {

    private UUID id;
    private String name;
    private String cpf;
    private Timestamp created_at;
    private boolean status;
}
