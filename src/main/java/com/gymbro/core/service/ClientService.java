package com.gymbro.core.service;

import com.gymbro.core.dto.request.ClientRequest;
import com.gymbro.core.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {

    ClientResponse create(String gymToken, ClientRequest request);

    List<ClientResponse> findAll(String gymToken);

    ClientResponse findById(String gymToken, Long clientId);

    ClientResponse update(String gymToken, Long clientId, ClientRequest request);

    void delete(String gymToken, Long clientId);
}
