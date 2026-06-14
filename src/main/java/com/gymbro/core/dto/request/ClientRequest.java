package com.gymbro.core.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientRequest(
        @NotBlank @Size(max = 100) String firstName,
        @NotBlank @Size(max = 100) String lastName,
        @NotNull Long documentTypeId,
        @NotBlank @Size(max = 100) String documentNumber,
        @NotNull @Past LocalDate birthDate,
        @NotNull Long genderId,
        @Email @Size(max = 100) String email,
        @Size(max = 100) String phone,
        @Size(max = 100) String address,
        @Size(max = 100) String photoUrl,
        Boolean active
) {
}
