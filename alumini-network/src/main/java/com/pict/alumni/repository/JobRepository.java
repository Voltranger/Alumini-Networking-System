package com.pict.alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pict.alumni.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JobRepository extends JpaRepository<Job,Long> {

}
