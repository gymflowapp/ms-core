package com.gymbro.core.dto.request;

import static com.gymbro.core.constants.AppConstants.Gym.ADDRESS_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.BRANDING_COLORS_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.EMAIL_INVALID;
import static com.gymbro.core.constants.AppConstants.Gym.EMAIL_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.LOGO_URL_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.NAME_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.NAME_REQUIRED;
import static com.gymbro.core.constants.AppConstants.Gym.PHONE_MAX_LENGTH;
import static com.gymbro.core.constants.AppConstants.Gym.PLAN_REQUIRED;

import com.gymbro.core.entity.Gym.Plan;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GymRequest(
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = 255, message = NAME_MAX_LENGTH)
        String name,

        @NotNull(message = PLAN_REQUIRED)
        Plan plan,

        @Size(max = 500, message = ADDRESS_MAX_LENGTH)
        String address,

        @Size(max = 20, message = PHONE_MAX_LENGTH)
        String phone,

        @Email(message = EMAIL_INVALID)
        @Size(max = 255, message = EMAIL_MAX_LENGTH)
        String email,

        @Size(max = 500, message = LOGO_URL_MAX_LENGTH)
        String logoUrl,

        @Size(max = 9, message = BRANDING_COLORS_MAX_LENGTH)
        String brandingColors,

        Boolean active
) {
}
