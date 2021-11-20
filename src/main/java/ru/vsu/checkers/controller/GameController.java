package ru.vsu.checkers.controller;

import ru.vsu.checkers.model.PlayerInfo;

public interface GameController {
    void startGame(PlayerInfo one, PlayerInfo two, int numOfMoves);

}
