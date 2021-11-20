package ru.vsu.checkers;


import ru.vsu.checkers.component.BotMover;
import ru.vsu.checkers.controller.CheckerController;
import ru.vsu.checkers.controller.GameController;
import ru.vsu.checkers.model.Bot;
import ru.vsu.checkers.model.Direction;
import ru.vsu.checkers.model.PlayerInfo;

public class Application {
    public static void main(String[] args){
        GameController gameController = new CheckerController();
        gameController.startGame(new PlayerInfo(new Bot(), new BotMover(), Direction.SOUTH), new PlayerInfo(new Bot(), new BotMover(), Direction.NORTH), 50);
    }
}
