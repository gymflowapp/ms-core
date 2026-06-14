package com.gymbro.core.repository;

import com.gymbro.core.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Genders, Long> {
}
