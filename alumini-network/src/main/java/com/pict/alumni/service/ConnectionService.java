package com.pict.alumni.service;

import com.pict.alumni.entity.Alumni;
import com.pict.alumni.entity.Connection;
import com.pict.alumni.repository.ConnectionRepository;
import com.pict.alumni.repository.alumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private alumniRepository alumniRepository;

    public Connection connect(Long alumni1Id, Long alumni2Id) {
        Alumni a1 = alumniRepository.findById(alumni1Id).orElseThrow();
        Alumni a2 = alumniRepository.findById(alumni2Id).orElseThrow();

        //  Check if already connected (both directions)
        Optional<Connection> existing =
                connectionRepository.findByAlumni1AndAlumni2(a1, a2);

        Optional<Connection> reverse =
                connectionRepository.findByAlumni1AndAlumni2(a2, a1);

        if (existing.isPresent() || reverse.isPresent()) {
            throw new RuntimeException("Already connected");
        }

        Connection connection = new Connection();
        connection.setAlumni1(a1);
        connection.setAlumni2(a2);

        return connectionRepository.save(connection);
    }

    public List<Connection> getConnectionsByUser(Long id) {
        Alumni alumni = alumniRepository.findById(id).orElseThrow();
        return connectionRepository.findByAlumni1OrAlumni2(alumni, alumni);
    }

    public List<Connection> getAll() {
        return connectionRepository.findAll();
    }
}
