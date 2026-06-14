package com.gymbro.core.mapper;

import com.gymbro.core.dto.request.ClientRequest;
import com.gymbro.core.dto.response.ClientResponse;
import com.gymbro.core.entity.Clients;
import com.gymbro.core.entity.DocumentType;
import com.gymbro.core.entity.Genders;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Clients toEntity(ClientRequest request, DocumentType documentType, Genders gender) {
        Clients client = new Clients();
        updateEntity(client, request, documentType, gender);
        return client;
    }

    public void updateEntity(
            Clients client,
            ClientRequest request,
            DocumentType documentType,
            Genders gender
    ) {
        client.setFirstName(request.firstName().trim());
        client.setLastName(request.lastName().trim());
        client.setDocumentType(documentType);
        client.setDocumentNumber(request.documentNumber().trim());
        client.setBirthDate(request.birthDate());
        client.setGender(gender);
        client.setEmail(trimToNull(request.email()));
        client.setPhone(trimToNull(request.phone()));
        client.setAddress(trimToNull(request.address()));
        client.setPhotoUrl(trimToNull(request.photoUrl()));
        if (request.active() != null) {
            client.setActive(request.active());
        }
    }

    public ClientResponse toResponse(Clients client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getDocumentType().getId(),
                client.getDocumentType().getName(),
                client.getDocumentNumber(),
                client.getBirthDate(),
                client.getGender().getId(),
                client.getGender().getName(),
                client.getEmail(),
                client.getPhone(),
                client.getAddress(),
                client.getPhotoUrl(),
                client.getActive(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }

    private String trimToNull(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
