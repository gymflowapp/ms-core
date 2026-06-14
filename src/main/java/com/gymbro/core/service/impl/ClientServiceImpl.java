package com.gymbro.core.service.impl;

import com.gymbro.common.security.GymTokenService;
import com.gymbro.core.dto.request.ClientRequest;
import com.gymbro.core.dto.response.ClientResponse;
import com.gymbro.core.entity.Clients;
import com.gymbro.core.entity.DocumentType;
import com.gymbro.core.entity.Genders;
import com.gymbro.core.entity.Gym;
import com.gymbro.core.exception.ConflictException;
import com.gymbro.core.exception.ResourceNotFoundException;
import com.gymbro.core.mapper.ClientMapper;
import com.gymbro.core.repository.ClientRepository;
import com.gymbro.core.repository.DocumentTypeRepository;
import com.gymbro.core.repository.GenderRepository;
import com.gymbro.core.repository.GymRepository;
import com.gymbro.core.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final GymRepository gymRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final GenderRepository genderRepository;
    private final GymTokenService gymTokenService;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(
            ClientRepository clientRepository,
            GymRepository gymRepository,
            DocumentTypeRepository documentTypeRepository,
            GenderRepository genderRepository,
            GymTokenService gymTokenService,
            ClientMapper clientMapper
    ) {
        this.clientRepository = clientRepository;
        this.gymRepository = gymRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.genderRepository = genderRepository;
        this.gymTokenService = gymTokenService;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientResponse create(String gymToken, ClientRequest request) {
        Long gymId = decryptGymId(gymToken);
        Gym gym = findGym(gymId);
        validateUniqueDocument(gymId, request.documentNumber(), null);
        Clients client = clientMapper.toEntity(
                request,
                findDocumentType(request.documentTypeId()),
                findGender(request.genderId())
        );
        client.setGym(gym);
        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    public List<ClientResponse> findAll(String gymToken) {
        Long gymId = decryptGymId(gymToken);
        findGym(gymId);
        return clientRepository.findAllByGymIdAndActiveTrueOrderByFirstNameAscLastNameAsc(gymId)
                .stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    public ClientResponse findById(String gymToken, Long clientId) {
        return clientMapper.toResponse(findClient(decryptGymId(gymToken), clientId));
    }

    @Override
    @Transactional
    public ClientResponse update(String gymToken, Long clientId, ClientRequest request) {
        Long gymId = decryptGymId(gymToken);
        Clients client = findClient(gymId, clientId);
        validateUniqueDocument(gymId, request.documentNumber(), clientId);
        clientMapper.updateEntity(
                client,
                request,
                findDocumentType(request.documentTypeId()),
                findGender(request.genderId())
        );
        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    @Transactional
    public void delete(String gymToken, Long clientId) {
        Clients client = findClient(decryptGymId(gymToken), clientId);
        client.setActive(false);
        clientRepository.save(client);
    }

    private Long decryptGymId(String gymToken) {
        try {
            return gymTokenService.decryptGymId(gymToken);
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("El token del gimnasio no es válido");
        }
    }

    private Gym findGym(Long gymId) {
        return gymRepository.findById(gymId)
                .filter(gym -> Boolean.TRUE.equals(gym.getActive()))
                .orElseThrow(() -> new ResourceNotFoundException("Gimnasio no encontrado"));
    }

    private Clients findClient(Long gymId, Long clientId) {
        return clientRepository.findByIdAndGymId(clientId, gymId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    private DocumentType findDocumentType(Long id) {
        return documentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de documento no encontrado"));
    }

    private Genders findGender(Long id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));
    }

    private void validateUniqueDocument(Long gymId, String documentNumber, Long clientId) {
        boolean exists = clientId == null
                ? clientRepository.existsByGymIdAndDocumentNumberIgnoreCase(gymId, documentNumber.trim())
                : clientRepository.existsByGymIdAndDocumentNumberIgnoreCaseAndIdNot(
                        gymId,
                        documentNumber.trim(),
                        clientId
                );
        if (exists) {
            throw new ConflictException("Ya existe un cliente con ese documento en el gimnasio");
        }
    }
}
