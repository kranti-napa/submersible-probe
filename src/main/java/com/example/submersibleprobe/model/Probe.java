package com.example.submersibleprobe.model;

import java.util.HashSet;
import java.util.Set;

public class Probe {
    private int x, y;
    private Direction direction;
    private final Set<String> visitedCoordinates = new HashSet<>();

    public Probe(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        logVisited();
    }

    public void moveForward() {
        switch (direction) {
            case NORTH -> y++;
            case SOUTH -> y--;
            case EAST -> x++;
            case WEST -> x--;
        }
        logVisited();
    }

    public void moveBackward() {
        switch (direction) {
            case NORTH -> y--;
            case SOUTH -> y++;
            case EAST -> x--;
            case WEST -> x++;
        }
        logVisited();
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    private void logVisited() {
        visitedCoordinates.add(x + "," + y);
    }

    public String getPosition() {
        return "(" + x + "," + y + ") facing " + direction;
    }

    public Set<String> getVisitedCoordinates() {
        return visitedCoordinates;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }
}
