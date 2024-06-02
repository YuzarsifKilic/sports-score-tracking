package com.yuzarsif.api.service;

import com.yuzarsif.api.model.ClientResponse;
import com.yuzarsif.api.repository.ClientResponseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientResponseService {

    private final ClientResponseRepository clientResponseRepository;

    public ClientResponseService(ClientResponseRepository clientResponseRepository) {
        this.clientResponseRepository = clientResponseRepository;
    }

    public ClientResponse save(String request, String response) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setRequest(request);
        clientResponse.setResponse(response);
        return clientResponseRepository.save(clientResponse);
    }

    public Optional<ClientResponse> findByRequest(String request) {
        return clientResponseRepository.findByRequest(request);
    }


    public void deleteAll() {
        clientResponseRepository.deleteAll();
    }
}
