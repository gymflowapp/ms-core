package com.gymbro.core.service.impl;

import com.gymbro.common.security.GymTokenService;
import com.gymbro.core.dto.request.ClientRequest;
import com.gymbro.core.entity.Clients;
import com.gymbro.core.entity.Gym;
import com.gymbro.core.exception.ConflictException;
import com.gymbro.core.exception.ResourceNotFoundException;
import com.gymbro.core.mapper.ClientMapper;
import com.gymbro.core.repository.ClientRepository;
import com.gymbro.core.repository.DocumentTypeRepository;
import com.gymbro.core.repository.GenderRepository;
import com.gymbro.core.repository.GymRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTests {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private GymRepository gymRepository;
    @Mock
    private DocumentTypeRepository documentTypeRepository;
    @Mock
    private GenderRepository genderRepository;
    @Mock
    private GymTokenService gymTokenService;

    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientServiceImpl(
                clientRepository,
                gymRepository,
                documentTypeRepository,
                genderRepository,
                gymTokenService,
                new ClientMapper()
        );
    }

    @Test
    void createRejectsDuplicateDocumentWithinGym() {
        ClientRequest request = request();
        Gym gym = Gym.builder().id(10L).active(true).build();
        when(gymTokenService.decryptGymId("token")).thenReturn(10L);
        when(gymRepository.findById(10L)).thenReturn(Optional.of(gym));
        when(clientRepository.existsByGymIdAndDocumentNumberIgnoreCase(10L, "123"))
                .thenReturn(true);

        assertThrows(ConflictException.class, () -> clientService.create("token", request));
    }

    @Test
    void findByIdDoesNotReturnClientFromAnotherGym() {
        when(gymTokenService.decryptGymId("token")).thenReturn(10L);
        when(clientRepository.findByIdAndGymId(20L, 10L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.findById("token", 20L));
    }

    @Test
    void deletePerformsSoftDelete() {
        Clients client = Clients.builder().id(20L).active(true).build();
        when(gymTokenService.decryptGymId("token")).thenReturn(10L);
        when(clientRepository.findByIdAndGymId(20L, 10L)).thenReturn(Optional.of(client));

        clientService.delete("token", 20L);

        assertFalse(client.getActive());
        verify(clientRepository).save(client);
    }

    private ClientRequest request() {
        return new ClientRequest(
                "Ana",
                "Díaz",
                1L,
                "123",
                LocalDate.of(1990, 1, 1),
                1L,
                "ana@example.com",
                null,
                null,
                null,
                true
        );
    }
}
