package com.pict.alumni.service;

import com.pict.alumni.dto.AlumniRequestDTO;
import com.pict.alumni.dto.AlumniResponseDTO;
import com.pict.alumni.entity.Alumni;
import com.pict.alumni.exception.ResourceNotFoundException;
import com.pict.alumni.repository.alumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class alumniService {
    @Autowired
    alumniRepository AlumniRepository;

    public AlumniResponseDTO register(AlumniRequestDTO dto) {
        Alumni alumni = new Alumni();
        alumni.setName(dto.getName());
        alumni.setEmail(dto.getEmail());
        alumni.setPassword(dto.getPassword());
        alumni.setCompany(dto.getCompany());
        alumni.setSkills(dto.getSkills());
        alumni.setGraduationYear(dto.getGraduationYear());

        Alumni saved = AlumniRepository.save(alumni);
        AlumniResponseDTO response = new AlumniResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setCompany(saved.getCompany());
        response.setSkills(saved.getSkills());
        response.setGraduationYear(saved.getGraduationYear());

        return response;
    }

    public List<Alumni> getAll() {
        return AlumniRepository.findAll();
    }

    public Alumni getById(long id) {
        return AlumniRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Alumni not found with id " + id));}

    public List<Alumni> getByCompany(String company) {
        return AlumniRepository.findByCompany(company);
    }

    public List<Alumni> getBySkills(String skill) {
        return AlumniRepository.findBySkillsContaining(skill);
    }

    public List<Alumni> getByGraduationYear(int graduationYear) {
        return AlumniRepository.findByGraduationYear(graduationYear);
    }
}
