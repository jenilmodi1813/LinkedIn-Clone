package com.services.service;

import com.services.dtos.experience.CreateExperienceDto;
import com.services.dtos.experience.ExperienceDto;
import com.services.dtos.experience.UpdateExperienceDto;
import jakarta.validation.Valid;

import java.util.List;

public interface ExperienceService {
    void createExperience(@Valid CreateExperienceDto createExperienceDto, Long id);

    void updateExperienceDto(@Valid UpdateExperienceDto updateExperienceDto, Long userId,Long expiId);

    List<ExperienceDto> getAllExperiencesByUserId(Long id);

    void deleteExperienceById(Long id);

    ExperienceDto getExperienceById(Long id);
}
