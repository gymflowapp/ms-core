package com.gymbro.core.service;

import com.gymbro.core.dto.request.GymRequest;
import com.gymbro.core.dto.response.GymResponse;

import java.util.List;

public interface GymService {

    GymResponse create(GymRequest request);

    List<GymResponse> findAll();

    GymResponse findById(Long gymId);

    GymResponse update(Long gymId, GymRequest request);

    void delete(Long gymId);
}
