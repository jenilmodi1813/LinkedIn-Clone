package com.services.service.impl;

import com.services.dtos.experience.CreateExperienceDto;
import com.services.dtos.experience.ExperienceDto;
import com.services.dtos.experience.UpdateExperienceDto;
import com.services.entity.Experience;
import com.services.entity.User;
import com.services.exception.ApiException;
import com.services.repositories.ExperienceRepository;
import com.services.repositories.users.UserRepository;
import com.services.service.ExperienceService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final String USER_DOES_NOT_EXIST = "user does not exist";
    private final String INVALID_INPUT = "INVALID INPUT";
    private final String EXPERIENCE_DOES_NOT_EXIST = "experience not exist";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public void createExperience(CreateExperienceDto createExperienceDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ApiException(USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND));

        Experience experience = new Experience();
        experience.setUser(user);
        experience.setDescription(createExperienceDto.getDescription());
        experience.setExperienceYear(createExperienceDto.getExperienceInYears());
        experience.setCompany(createExperienceDto.getCompany());
        experience.setPosition(createExperienceDto.getPosition());
        experience.setStartDate(createExperienceDto.getStartDate());
        experience.setEndDate(createExperienceDto.getEndDate());

        experienceRepository.save(experience);
    }

    @Override
    public void updateExperienceDto(UpdateExperienceDto updateExperienceDto, Long userId,Long expiId) {

        Experience experience = experienceRepository.findByUserIdAndId(userId, expiId).orElseThrow(()->new ApiException(INVALID_INPUT,HttpStatus.NOT_FOUND));

        if(updateExperienceDto.getExperienceInYears() != null) experience.setExperienceYear(updateExperienceDto.getExperienceInYears());
        if(updateExperienceDto.getCompany() != null) experience.setCompany(updateExperienceDto.getCompany());
        if(updateExperienceDto.getDescription() != null) experience.setDescription(updateExperienceDto.getDescription());
        if(updateExperienceDto.getPosition() != null) experience.setPosition(updateExperienceDto.getPosition());
        if(updateExperienceDto.getStartDate() != null) experience.setStartDate(updateExperienceDto.getStartDate());
        if(updateExperienceDto.getEndDate() != null) experience.setEndDate(updateExperienceDto.getEndDate());

        experienceRepository.save(experience);
    }

    @Override
    public List<ExperienceDto> getAllExperiencesByUserId(Long id) {
        List<Experience> experiences = experienceRepository.findAllByUserId(id);

        return experiences.stream().map(this::mapToExperienceDto).collect(Collectors.toList());
    }

    @Override
    public void deleteExperienceById(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(()->new ApiException(EXPERIENCE_DOES_NOT_EXIST,HttpStatus.NOT_FOUND));
        experienceRepository.deleteById(id);
    }

    @Override
    public ExperienceDto getExperienceById(Long id) {

        Experience experience = experienceRepository.findById(id).orElseThrow(()->new ApiException(EXPERIENCE_DOES_NOT_EXIST,HttpStatus.NOT_FOUND));

        return mapToExperienceDto(experience);
    }

    private ExperienceDto mapToExperienceDto(Experience experience) {
        ExperienceDto dto = new ExperienceDto();
        dto.setId(experience.getId());
        dto.setExperienceInYears(experience.getExperienceYear());
        dto.setStartDate(experience.getStartDate());
        dto.setEndDate(experience.getEndDate());
        dto.setDescription(experience.getDescription());
        dto.setCompany(experience.getCompany());
        dto.setPosition(experience.getPosition());
        dto.setUserId(experience.getUser().getId());

        return dto;
    }

}
