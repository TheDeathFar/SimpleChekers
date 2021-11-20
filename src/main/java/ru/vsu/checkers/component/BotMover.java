package ru.vsu.checkers.component;

import ru.vsu.checkers.model.*;
import ru.vsu.checkers.services.CheckerService;
import ru.vsu.checkers.services.FigureService;
import ru.vsu.checkers.services.QueenService;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BotMover implements Mover{
    private final Map<FigureType, FigureService> figureServiceMap;
    private final static Random RND = new Random();
    public BotMover(){
        figureServiceMap = Map.of(
                FigureType.Checker, new CheckerService(),
                FigureType.Queen, new QueenService()
        );
    }

    @Override
    public Move getMove(Player forWho, Game game) {
        Set<Figure> figures = game.getPlayerFigures().get(forWho);
        for(Figure f : figures){
            Cell from = game.getFigureCell().get(f);
            List<Move> moves = figureServiceMap.get(f.getFigureType()).getNecessaryMoves(from, game, forWho);
            if(!moves.isEmpty()){
                return moves.get(RND.nextInt(moves.size()));
            }
        }
        for(Figure f : figures){
            Cell from = game.getFigureCell().get(f);
            List<Move> moves = figureServiceMap.get(f.getFigureType()).getSimpleMoves(from, game, forWho);
            if(!moves.isEmpty()){
                return moves.get(RND.nextInt(moves.size()));
            }
        }
        return null;
    }
}
