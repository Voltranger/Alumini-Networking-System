package com.pict.alumni.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlumniRequestDTO {

    @NotBlank(message="Name is required")
    private String name;

    @Email(message="Invalid Email")
    private String email;

    @NotBlank(message="Password is required")
    private String password;

    private Long id;
    private String company;
    private String skills;

    @NotNull(message = "Graduation year required")
    private Integer graduationYear;

    public int getGraduationYear() {
        return graduationYear;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public String getSkills() {
        return skills;
    }


    //setters


    public void setCompany(String company) {
        this.company = company;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
