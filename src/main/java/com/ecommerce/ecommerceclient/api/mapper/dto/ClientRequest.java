package com.ecommerce.ecommerceclient.api.mapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String cpf;

    private boolean status = true;

}
