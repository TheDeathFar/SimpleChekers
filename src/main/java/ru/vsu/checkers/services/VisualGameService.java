package ru.vsu.checkers.services;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.VisualBoard;

public interface VisualGameService {
    VisualBoard createVisualBoard(Game game);
    void draw(VisualBoard visualBoard);
}
