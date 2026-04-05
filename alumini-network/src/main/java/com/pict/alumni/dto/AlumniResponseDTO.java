package com.pict.alumni.dto;

public class AlumniResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String company;
    private String skills;
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


    public void setSkills(String skills) {
        this.skills = skills;

    }
}
