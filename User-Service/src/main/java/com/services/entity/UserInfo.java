package com.services.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column
    private String address;

    @Column
    private String zipcode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column
    private String website;

    @Column(columnDefinition = "TEXT")
    private String professionalSummary;

    @Column
    private String headline;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date dob;


}
