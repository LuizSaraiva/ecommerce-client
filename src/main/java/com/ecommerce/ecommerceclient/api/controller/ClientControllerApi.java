package com.ecommerce.ecommerceclient.api.controller;

import com.ecommerce.ecommerceclient.api.mapper.dto.ClientDisableRequest;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientRequest;
import com.ecommerce.ecommerceclient.api.mapper.dto.ClientRequestUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface ClientControllerApi {

    @GetMapping
    ResponseEntity<?> getAllClients();

    @GetMapping("/{id}")
    ResponseEntity<?> getClientById(@PathVariable("id") UUID id);

    @PostMapping
    ResponseEntity<?> createClient(@RequestBody @Valid ClientRequest clientRequest);

    @PutMapping("/{id}")
    ResponseEntity<?> updateClient(@RequestBody @Valid ClientRequestUpdate clientRequestUpdate,
                                   @PathVariable("id") UUID id);

    @PatchMapping("/{id}")
    ResponseEntity<?> disableClient(@RequestBody @Valid ClientDisableRequest clientDisableRequest,
                                    @PathVariable("id") UUID id);
}
