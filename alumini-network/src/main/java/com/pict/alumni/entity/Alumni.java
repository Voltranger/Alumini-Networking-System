package com.pict.alumni.entity;

import jakarta.persistence.*;

@Entity
@Table(name="alumni")
public class Alumni {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    Long id;

    private String name;
    private String email;
    private String password;
    private String skills;
    private int graduationYear;
    private String company;



    public Alumni(){}

    public Alumni(String name,String email,String password,String skills,int graduationYear,String company)
    {
        this.company=company;
        this.email=email;
        this.name=name;
        this.skills=skills;
        this.password=password;
        this.graduationYear=graduationYear;
    }

    //getters
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

    public String getCompany()
    {
        return company;
    }

    public String getName()
    {
        return name;
    }

    public String getSkills()
    {
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