package ru.vsu.checkers.services;

import ru.vsu.checkers.component.BotMoveValidator;
import ru.vsu.checkers.component.HumanMoveValidator;
import ru.vsu.checkers.component.MoveValidator;
import ru.vsu.checkers.model.*;

import java.util.*;

public class CheckersGameService implements GameService{
    private final BoardService boardService;

    public CheckersGameService() {
        this.boardService = new CheckersBoardService();
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
        return null;
    }

    @Override
    public boolean canDoMove(Player player, Move move, Game game) {
        return game.getPlayerMoveValidatorMap().get(player).isPossibleMove(move, player, game);
    }

    @Override
    public void doMove(Move move, Player player, Game game) {
        List<MicroStep> path = move.path();
        Figure active = game.getCellFigure().get(path.get(0).from());
        for(MicroStep ms : path){
            List<Cell> between = getBetween(ms);
            Optional<Cell> need = between.stream().filter(c -> game.getCellFigure().get(c) != null).findAny();
            if(need.isPresent()){
                Cell c = need.get();
                Figure f = game.getCellFigure().get(c);
                game.getCellFigure().put(c, null);
                game.getFigureCell().put(f, null);
                Player p = getOwner(f, game);
                game.getPlayerFigures().get(p).remove(f);
            }
            game.getCellFigure().put(ms.from(), null);
            game.getCellFigure().put(ms.to(), active);
            game.getFigureCell().put(active, ms.to());
        }
    }

    @Override
    public boolean isEnd(Game game) {
        Set<Figure> figures = game.getPlayerFigures().get(getCurrentPlayer(game));
        return figures.isEmpty();
    }

    @Override
    public Player getCurrentPlayer(Game game) {
        return game.getPlayers().peek();
    }


    private List<Cell> getBetween(MicroStep ms){
       List<Cell> answer = new ArrayList<>();
       Cell c = ms.from();
       while(c.getNeighbours().get(ms.direction()) != ms.to()){
           c = c.getNeighbours().get(ms.direction());
           answer.add(c);
       }
        return answer;
    }

    private Player getOwner(Figure f, Game game){
        for(var l : game.getPlayerFigures().entrySet()){
            if(l.getValue().contains(f)) return l.getKey();
        }
        return null;
    }
}
