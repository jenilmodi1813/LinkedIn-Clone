package com.services.dtos.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

    @NotEmpty
    private String address;

    @NotEmpty
    private String zipCode;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;

    @NotEmpty
    private String website;

    @NotEmpty
    private String professionalSummary;

    @NotEmpty
    private String headLine;

    @NonNull
    private Date dob;
}
