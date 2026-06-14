package com.gymbro.core.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        Long documentTypeId,
        String documentType,
        String documentNumber,
        LocalDate birthDate,
        Long genderId,
        String gender,
        String email,
        String phone,
        String address,
        String photoUrl,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
