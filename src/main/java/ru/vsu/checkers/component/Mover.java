package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Move;
import ru.vsu.checkers.model.Player;

@FunctionalInterface
public interface Mover {
    Move getMove(Player forWho, Game game);
}
