package com.ensa.client_ms.dao;

import com.ensa.client_ms.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;

@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findByFirstname(String firstname);
}
