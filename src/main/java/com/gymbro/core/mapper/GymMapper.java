package com.gymbro.core.mapper;

import com.gymbro.core.dto.request.GymRequest;
import com.gymbro.core.dto.response.GymResponse;
import com.gymbro.core.entity.Gym;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {

    public Gym toEntity(GymRequest request){
        Gym gym = new Gym();
        updateEntity(gym, request);

        return gym;
    }

    public void updateEntity(Gym gym, GymRequest request) {
        gym.setName(request.name().trim());
        gym.setPlan(request.plan());
        gym.setAddress(trimToNull(request.address()));
        gym.setPhone(trimToNull(request.phone()));
        gym.setEmail(trimToNull(request.email()));
        gym.setLogoUrl(trimToNull(request.logoUrl()));
        gym.setBrandingColors(trimToNull(request.brandingColors()));
        if (request.active() != null) {
            gym.setActive(request.active());
        }
    }

    public GymResponse toResponse(Gym gym) {
        return new GymResponse(
                gym.getId(),
                gym.getName(),
                gym.getAddress(),
                gym.getPhone(),
                gym.getEmail(),
                gym.getLogoUrl(),
                gym.getBrandingColors(),
                gym.getPlan(),
                gym.getActive(),
                gym.getCreatedAt(),
                gym.getUpdatedAt()
        );
    }

    private String trimToNull(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
