package com.pict.alumni.repository;

import com.pict.alumni.entity.Alumni;
import com.pict.alumni.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository extends JpaRepository<Connection,Long> {

    Optional<Connection> findByAlumni1AndAlumni2(Alumni a1, Alumni a2);

    List<Connection> findByAlumni1OrAlumni2(Alumni alumni, Alumni alumni1);
}
