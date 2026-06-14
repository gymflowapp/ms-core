package com.gymbro.core.repository;

import com.gymbro.core.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Long> {

    List<Clients> findAllByGymIdAndActiveTrueOrderByFirstNameAscLastNameAsc(Long gymId);

    Optional<Clients> findByIdAndGymId(Long id, Long gymId);

    boolean existsByGymIdAndDocumentNumberIgnoreCase(Long gymId, String documentNumber);

    boolean existsByGymIdAndDocumentNumberIgnoreCaseAndIdNot(Long gymId, String documentNumber, Long id);
}
