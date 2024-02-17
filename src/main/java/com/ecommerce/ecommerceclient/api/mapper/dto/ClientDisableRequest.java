package com.ecommerce.ecommerceclient.api.mapper.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientDisableRequest {
    @NotBlank
    @NotNull
    private boolean status;
}
