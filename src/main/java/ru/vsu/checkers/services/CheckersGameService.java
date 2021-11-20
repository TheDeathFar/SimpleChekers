package ru.vsu.checkers.services;

import ru.vsu.checkers.component.BotMoveValidator;
import ru.vsu.checkers.component.HumanMoveValidator;
import ru.vsu.checkers.component.MoveValidator;
import ru.vsu.checkers.model.*;

import java.util.*;

public class CheckersGameService implements GameService{
    private final BoardService boardService;
    private final Map<FigureType, FigureService> figureServiceMap;

    public CheckersGameService() {
        this.boardService = new CheckersBoardService();
        this.figureServiceMap = Map.of(FigureType.Checker, new CheckerService(),
                FigureType.Queen, new QueenService());
    }

    @Override
    public Game createGame(Player one, Player two) {
        Game game = new Game();
        boardService.setBoard(game);
        boardService.setFigures(game, one, two);
        Queue<Player> playerQueue = new LinkedList<>();
        playerQueue.add(one);
        playerQueue.add(two);
        Map<Player, MoveValidator> playerMoveValidatorMap = new HashMap<>();
        playerMoveValidatorMap.put(one, one instanceof Human ? new HumanMoveValidator() : new BotMoveValidator());
        playerMoveValidatorMap.put(two, two instanceof Human ? new HumanMoveValidator() : new BotMoveValidator());
        game.setPlayerMoveValidatorMap(playerMoveValidatorMap);
        game.setPlayers(playerQueue);
        game.setMoveInfoList(new ArrayList<>());
        return game;
    }

    @Override
    public boolean canDoMove(Player player, Move move, Game game) {
        return game.getPlayerMoveValidatorMap().get(player).isPossibleMove(move, player, game);
    }

    @Override
    public void doMove(Move move, Player player, Game game) {
        MicroStep ms = move.path().get(0);
        Cell from = ms.from();
        Figure f = game.getCellFigure().get(from);
        if(f!= null){
            FigureService fs = figureServiceMap.get(f.getFigureType());
            fs.doMove(move, game);
            game.getMoveInfoList().add(new MoveInfo(move, player));
            game.getPlayers().add(game.getPlayers().poll());
        }
    }

    @Override
    public boolean isEnd(Game game) {
        Set<Figure> figures = game.getPlayerFigures().get(getCurrentPlayer(game));
        System.out.println("----");
        System.out.println(figures.size());
        System.out.println("----");
        return figures.isEmpty();
    }

    @Override
    public Player getCurrentPlayer(Game game) {
        return game.getPlayers().peek();
    }


    public boolean hasAnyNecessaryMove(Player player, Game game){
        for(Figure f : game.getPlayerFigures().get(player)){
            Cell from = game.getFigureCell().get(f);
            List<Move> moves = figureServiceMap.get(f.getFigureType()).getNecessaryMoves(from, game, player);
            if(!moves.isEmpty()) return true;
        }
        return false;
    }
}
