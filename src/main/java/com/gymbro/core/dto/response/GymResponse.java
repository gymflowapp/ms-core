package com.gymbro.core.dto.response;

import com.gymbro.core.entity.Gym.Plan;
import java.time.LocalDateTime;

public record GymResponse(
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String logoUrl,
        String brandingColors,
        Plan plan,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
