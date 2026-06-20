package com.gymbro.core.service.impl;

import static com.gymbro.core.constants.AppConstants.Gym.NOT_FOUND;
import static com.gymbro.core.constants.AppConstants.Gym.NAME_ALREADY_EXISTS;

import com.gymbro.core.dto.request.GymRequest;
import com.gymbro.core.dto.response.GymResponse;
import com.gymbro.core.entity.Gym;
import com.gymbro.core.exception.ConflictException;
import com.gymbro.core.exception.ResourceNotFoundException;
import com.gymbro.core.mapper.GymMapper;
import com.gymbro.core.repository.GymRepository;
import com.gymbro.core.service.GymService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;
    private final GymMapper gymMapper;

    public GymServiceImpl(GymRepository gymRepository, GymMapper gymMapper) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    @Override
    @Transactional
    public GymResponse create(GymRequest request) {
        validateUniqueName(request.name(), null);
        Gym gym = gymMapper.toEntity(request);
        return gymMapper.toResponse(gymRepository.save(gym));
    }

    @Override
    public List<GymResponse> findAll() {
        return gymRepository.findAll()
                .stream()
                .map(gymMapper::toResponse)
                .toList();
    }

    @Override
    public GymResponse findById(Long gymId) {
        return gymRepository.findById(gymId)
                .map(gymMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
    }

    @Override
    @Transactional
    public GymResponse update(Long gymId, GymRequest request) {
        Gym gym = findGymById(gymId);
        validateUniqueName(request.name(), gymId);
        gymMapper.updateEntity(gym, request);
        return gymMapper.toResponse(gymRepository.save(gym));
    }

    @Override
    @Transactional
    public void delete(Long gymId) {
        Gym gym = findGymById(gymId);
        gym.setActive(false);
        gymRepository.save(gym);
    }

    private Gym findGymById(Long gymId) {
        return gymRepository.findById(gymId)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND));
    }

    private void validateUniqueName(String name, Long gymId) {
        boolean exists = gymId == null
                ? gymRepository.existsByNameIgnoreCase(name)
                : gymRepository.existsByNameIgnoreCaseAndIdNot(name, gymId);
        if (exists) {
            throw new ConflictException(NAME_ALREADY_EXISTS);
        }
    }
}
