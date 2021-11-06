package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Move;
import ru.vsu.checkers.model.Player;

public class BotMoveValidator implements MoveValidator{
    @Override
    public boolean isPossibleMove(Move move, Player who, Game game) {
        return true;
    }
}
