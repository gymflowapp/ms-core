package com.gymbro.core.controller;

import static com.gymbro.core.constants.AppConstants.Router.API_V1;
import static com.gymbro.core.constants.AppConstants.Router.GYMS;
import static com.gymbro.core.constants.AppConstants.Router.GYM_BY_ID;

import com.gymbro.core.dto.response.GymResponse;
import com.gymbro.core.dto.request.GymRequest;
import com.gymbro.core.service.GymService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(API_V1 + GYMS)
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService){
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity<GymResponse> create(
            @Valid
            @RequestBody
            GymRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(gymService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<GymResponse>> findAll(){

        return ResponseEntity.ok(gymService.findAll());
    }

    @GetMapping(GYM_BY_ID)
    public ResponseEntity<GymResponse> findById(
            @PathVariable Long gymId
    ){

        return ResponseEntity.ok(gymService.findById(gymId));
    }

    @PutMapping(GYM_BY_ID)
    public ResponseEntity<GymResponse> update(
            @PathVariable Long gymId,
            @Valid @RequestBody GymRequest request
    ){
        return ResponseEntity.ok(gymService.update(gymId, request));
    }

    @DeleteMapping(GYM_BY_ID)
    public ResponseEntity<Void> delete(
            @PathVariable Long gymId
    ){
        gymService.delete(gymId);
        return ResponseEntity.noContent().build();

    }
}
