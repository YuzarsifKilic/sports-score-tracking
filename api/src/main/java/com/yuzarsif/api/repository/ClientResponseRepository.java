package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.ClientResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClientResponseRepository extends MongoRepository<ClientResponse, String> {

    Optional<ClientResponse> findByRequest(String request);

    List<ClientResponse> findClientResponsesByCreatedDateBefore(LocalDateTime createdDate);
}
