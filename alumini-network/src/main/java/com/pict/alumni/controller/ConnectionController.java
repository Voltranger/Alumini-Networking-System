package com.pict.alumni.controller;

import com.pict.alumni.entity.Alumni;
import com.pict.alumni.entity.Connection;
import com.pict.alumni.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/connections")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    //  Create connection (direct connect)
    @PostMapping
    public Connection connect(@RequestBody Connection connection) {
        return connectionService.connect(
                connection.getAlumni1().getId(),
                connection.getAlumni2().getId()
        );
    }

    // Get all connections
    @GetMapping
    public List<Connection> getAllConnections() {
        return connectionService.getAll();
    }

    // 🔍 Get connections of a specific alumni (VERY USEFUL 🔥)
    @GetMapping("/user/{id}")
    public List<Connection> getConnectionsByUser(@PathVariable Long id) {
        return connectionService.getConnectionsByUser(id);
    }
}
