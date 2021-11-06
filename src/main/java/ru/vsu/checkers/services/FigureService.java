package ru.vsu.checkers.services;

import ru.vsu.checkers.model.*;

import java.util.List;
import java.util.Set;

public interface FigureService {
    List<Move> getPossibleMoves(Cell from, Game game, Player forWho);
}
