package com.gymbro.core.controller;

import com.gymbro.core.dto.request.ClientRequest;
import com.gymbro.core.dto.response.ClientResponse;
import com.gymbro.core.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private static final String GYM_TOKEN_HEADER = "X-Gym-Token";

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(
            @RequestHeader(GYM_TOKEN_HEADER) String gymToken,
            @Valid @RequestBody ClientRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(gymToken, request));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(
            @RequestHeader(GYM_TOKEN_HEADER) String gymToken
    ) {
        return ResponseEntity.ok(clientService.findAll(gymToken));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponse> findById(
            @RequestHeader(GYM_TOKEN_HEADER) String gymToken,
            @PathVariable Long clientId
    ) {
        return ResponseEntity.ok(clientService.findById(gymToken, clientId));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponse> update(
            @RequestHeader(GYM_TOKEN_HEADER) String gymToken,
            @PathVariable Long clientId,
            @Valid @RequestBody ClientRequest request
    ) {
        return ResponseEntity.ok(clientService.update(gymToken, clientId, request));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> delete(
            @RequestHeader(GYM_TOKEN_HEADER) String gymToken,
            @PathVariable Long clientId
    ) {
        clientService.delete(gymToken, clientId);
        return ResponseEntity.noContent().build();
    }
}
