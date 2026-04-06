package com.pict.alumni.service;

import com.pict.alumni.entity.Job;
import com.pict.alumni.repository.JobRepository;
import com.pict.alumni.repository.alumniRepository;
import com.pict.alumni.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private alumniRepository alumniRepository;

    public Job save(Job job) {
        if (job.getPostedBy() == null || job.getPostedBy().getId() == null) {
            throw new IllegalArgumentException("Posted By alumni ID is required");
        }

        Long alumniId = job.getPostedBy().getId();
        job.setPostedBy(
                alumniRepository.findById(alumniId)
                        .orElseThrow(() -> new ResourceNotFoundException("Alumni not found with id " + alumniId))
        );

        return jobRepository.save(job);
    }

    public List<Job> getAll() {
        return jobRepository.findAll();
    }
}
