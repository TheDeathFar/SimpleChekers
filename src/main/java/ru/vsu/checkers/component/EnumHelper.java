package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Direction;

import java.util.List;

public class EnumHelper {
    public List<Direction> getDiagonalDirections(){
        return List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }
}
