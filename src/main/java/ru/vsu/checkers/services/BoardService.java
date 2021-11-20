package ru.vsu.checkers.services;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Move;
import ru.vsu.checkers.model.Player;

public interface BoardService {
    void setBoard(Game game);
    void setFigures(Game game, Player one, Player two);
}
