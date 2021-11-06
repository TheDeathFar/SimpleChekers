package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Move;
import ru.vsu.checkers.model.Player;

public interface MoveValidator {
    boolean isPossibleMove(Move move, Player who, Game game);
}
