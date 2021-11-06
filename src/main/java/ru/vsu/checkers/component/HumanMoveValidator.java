package ru.vsu.checkers.component;


import ru.vsu.checkers.model.*;
import ru.vsu.checkers.services.CheckerService;
import ru.vsu.checkers.services.FigureService;
import ru.vsu.checkers.services.QueenService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HumanMoveValidator extends MoveValidatorAbs {
    private final Map<FigureType, FigureService> figureServiceMap;

    public HumanMoveValidator() {
        figureServiceMap = new HashMap<>();
        figureServiceMap.put(FigureType.Checker, new CheckerService());
        figureServiceMap.put(FigureType.Queen, new QueenService());
    }

    @Override
    public boolean isPossibleMove(Move move, Player who, Game game) {
        List<MicroStep> path = move.path();
        if(path.isEmpty())return false;
        Cell from = path.get(0).from();
        if(from == null) return false;
        Figure main = game.getCellFigure().get(from);
        if(main == null) return false;
        FigureService figureService = figureServiceMap.get(main.getFigureType());
        List<Move> possibleMoves = figureService.getPossibleMoves(from, game, who);
        return hasEquals(possibleMoves, move);
    }
}
