package com.services.controller;

import com.services.dtos.experience.CreateExperienceDto;
import com.services.dtos.experience.ExperienceDto;
import com.services.dtos.experience.UpdateExperienceDto;
import com.services.service.ExperienceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User/Expi")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("getExpiById/{id}")
    public ResponseEntity<ExperienceDto> getExperienceById(@PathVariable Long id) {
        ExperienceDto experienceDto = experienceService.getExperienceById(id);

        return ResponseEntity.ok(experienceDto);
    }

    @PostMapping("addExpi/{id}")
    public void createExperience(@Valid @RequestBody CreateExperienceDto createExperienceDto, @PathVariable Long id) {
        experienceService.createExperience(createExperienceDto, id);
    }
    @PutMapping("updateExpi/{userId}/{expiId}")
    public void updateExperience(@Valid @RequestBody UpdateExperienceDto updateExperienceDto, @PathVariable Long userId,@PathVariable Long expiId) {

        experienceService.updateExperienceDto(updateExperienceDto, userId,expiId);
    }

    @GetMapping("getAllExpiByUserId/{id}")
    public ResponseEntity<List<ExperienceDto>> getAllExperiences(@PathVariable Long id) {
        List<ExperienceDto> experiences = experienceService.getAllExperiencesByUserId(id);
        return ResponseEntity.ok(experiences);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteExperienceById(@PathVariable Long id, HttpServletRequest request) {
        experienceService.deleteExperienceById(id);
    }

}
