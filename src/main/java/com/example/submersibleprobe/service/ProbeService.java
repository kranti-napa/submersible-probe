package com.example.submersibleprobe.service;

import com.example.submersibleprobe.exception.InvalidCommandException;
import com.example.submersibleprobe.exception.ObstacleCollisionException;
import com.example.submersibleprobe.exception.OutOfBoundsException;
import com.example.submersibleprobe.model.Direction;
import com.example.submersibleprobe.model.Grid;
import com.example.submersibleprobe.model.Probe;
import org.springframework.stereotype.Service;

@Service
public class ProbeService {
    private final Grid grid = new Grid(10, 10);
    private final Probe probe = new Probe(0, 0, Direction.NORTH);

    public ProbeService() {
        grid.addObstacle(3, 3); // Example obstacle
    }

    public String executeCommands(String commands) {
        if (commands == null || commands.isBlank()) {
            throw new InvalidCommandException("Commands cannot be null or empty.");
        }

        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'F' -> moveProbe(true);
                case 'B' -> moveProbe(false);
                case 'L' -> probe.turnLeft();
                case 'R' -> probe.turnRight();
                default -> throw new InvalidCommandException("Invalid command: " + command);
            }
        }
        return probe.getPosition();
    }

    private void moveProbe(boolean forward) {
        int prevX = probe.getX();
        int prevY = probe.getY();

        if (forward) probe.moveForward();
        else probe.moveBackward();

        if (!grid.isWithinBounds(probe.getX(), probe.getY())) {
            probe.moveBackward();
            throw new OutOfBoundsException("Movement beyond grid limits is not allowed.");
        }

        if (grid.isObstacle(probe.getX(), probe.getY())) {
            probe.moveBackward();
            throw new ObstacleCollisionException("Collision detected at (" + probe.getX() + "," + probe.getY() + "). Movement stopped.");
        }
    }

    public String getCurrentPosition() {
        return probe.getPosition();
    }

    public Object getVisitedCoordinates() {
        return probe.getVisitedCoordinates();
    }
}
