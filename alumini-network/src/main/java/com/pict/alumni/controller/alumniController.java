package com.pict.alumni.controller;
import com.pict.alumni.service.alumniService;
import com.pict.alumni.entity.Alumni;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.pict.alumni.dto.*;
import java.util.List;



@RestController
@RequestMapping("/api/alumni")
public class alumniController {

    @Autowired
    alumniService AlumniService;

    @PostMapping("/register")
    public AlumniResponseDTO register(@Valid @RequestBody AlumniRequestDTO dto) {
        return AlumniService.register(dto);
    }

    @GetMapping("/all")
    public List<Alumni> getAll(){
        return AlumniService.getAll();
    }

    @GetMapping("/{id}")
    public Alumni getById(@PathVariable Long id)
    {return AlumniService.getById(id);
    }

    @GetMapping("/company/{company}")
    public List<Alumni> getByCompany(@PathVariable String company) {
        return AlumniService.getByCompany(company);
    }

    @GetMapping("/skills/{skill}")
    public List<Alumni> getBySkills(@PathVariable String skill) {
        return AlumniService.getBySkills(skill);
    }

    @GetMapping("/graduationYear/{graduationYear}")
    public List<Alumni> getByGraduationYear(@PathVariable int graduationYear) {
        return AlumniService.getByGraduationYear(graduationYear);
    }





}
