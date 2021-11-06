package ru.vsu.checkers.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cell {
    private final Map<Direction, Cell> neighbours;

    public Cell() {
        this.neighbours = new LinkedHashMap<>();
    }

    public Map<Direction, Cell> getNeighbours() {
        return neighbours;
    }
}
