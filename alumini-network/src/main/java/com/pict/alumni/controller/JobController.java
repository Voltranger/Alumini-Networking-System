package com.pict.alumni.controller;

import com.pict.alumni.entity.Job;
import com.pict.alumni.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.save(job);
    }

    //  Get all jobs
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAll();
    }
}