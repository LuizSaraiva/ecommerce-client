package com.ecommerce.ecommerceclient.domain.repository;

import com.ecommerce.ecommerceclient.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findClientByCpf(String cpf);

}
