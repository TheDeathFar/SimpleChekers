package ru.vsu.checkers.services;

import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Move;
import ru.vsu.checkers.model.Player;

public interface GameService {
    Game createGame(Player one, Player two);
    boolean canDoMove(Player player, Move move, Game game);
    void doMove(Move move, Player player, Game game);
    boolean isEnd(Game game);
    Player getCurrentPlayer(Game game);
}
