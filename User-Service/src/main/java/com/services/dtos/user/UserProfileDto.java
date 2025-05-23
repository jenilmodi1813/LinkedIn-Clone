package com.services.dtos.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto  {
    private String address;
    private String zipCode;
    private String city;
    private String country;
    private String website;
    private String professionalSummary;
    private String headLine;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;

}
