package ru.vsu.checkers.controller;

import ru.vsu.checkers.component.Mover;
import ru.vsu.checkers.model.*;
import ru.vsu.checkers.services.CheckersGameService;
import ru.vsu.checkers.services.GameService;
import ru.vsu.checkers.services.VisualCheckerGameService;
import ru.vsu.checkers.services.VisualGameService;

import java.util.HashMap;
import java.util.Map;

public class CheckerController implements GameController{
    private final GameService gameService;
    private final VisualGameService visualGameService;

    public CheckerController() {
        this.gameService = new CheckersGameService();
        this.visualGameService = new VisualCheckerGameService();
    }

    public void startGame(PlayerInfo one, PlayerInfo two, int numOfMoves){
        Map<Player, Mover> moverMap = new HashMap<>();
        moverMap.put(one.player(), one.mover());
        moverMap.put(two.player(), two.mover());
        Game game = gameService.createGame(one.player(), two.player());
        VisualBoard vb = visualGameService.createVisualBoard(game);
        int step = 0;
        while(!gameService.isEnd(game) || step < numOfMoves){
            Player player = gameService.getCurrentPlayer(game);
            Mover m = moverMap.get(player);
            Move move = m.getMove();
            while(!gameService.canDoMove(player, move, game)){
                move = m.getMove();
            }
            gameService.doMove(move, player, game);
            visualGameService.draw(vb);
        }
    }
}
