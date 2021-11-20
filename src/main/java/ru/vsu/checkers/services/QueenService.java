package ru.vsu.checkers.services;

import ru.vsu.checkers.model.*;

import java.util.ArrayList;
import java.util.List;

public class QueenService extends FigureServiceAbs{

    @Override
    protected List<MicroStep> tryToJump(Cell from, Direction direction, Game game, Player who) {
        List<MicroStep> microSteps = new ArrayList<>();
        Cell next = from.getNeighbours().get(direction);
        Figure f = game.getCellFigure().get(next);
        while(next != null && f == null){
            next = next.getNeighbours().get(direction);
            f = game.getCellFigure().get(next);
        }
        if(next != null && isEnemyFigure(f, who, game)) {
            game.getCellFigure().put(next, null);
            next = next.getNeighbours().get(direction);
            while(next != null){
                microSteps.add(new MicroStep(from, next, direction, f));
                next = next.getNeighbours().get(direction);
            }
        }
        return microSteps;
    }

    @Override
    public List<Move> getNecessaryMoves(Cell from, Game game, Player forWho) {
        List<Move> answer = new ArrayList<>();
        for(Direction d : enumHelper.getDiagonalDirections()){
            List<MicroStep> afterJump = tryToJump(from, d, game, forWho);
            if(!afterJump.isEmpty()){
                boolean was = false;
                for(MicroStep step : afterJump){
                    List<Move> moves = getNecessaryMoves(step.to(), game, forWho);
                    if(!moves.isEmpty()){
                        was = true;
                        for(Move m : moves){
                            List<MicroStep> microSteps = new ArrayList<>();
                            microSteps.add(step);
                            microSteps.addAll(m.path());
                            answer.add(new Move(microSteps));
                        }
                    }
                }
                if(!was){
                    for (MicroStep step : afterJump){
                        answer.add(new Move(List.of(step)));
                    }
                }
                cancelJump(afterJump.get(0), game);
            }
        }
        return answer;
    }


    @Override
    public List<Move> getSimpleMoves(Cell from, Game game, Player forWho) {
        List<Move> answer = new ArrayList<>();
        for(Direction d : enumHelper.getDiagonalDirections()){
            Cell next = from.getNeighbours().get(d);
            List<MicroStep> microSteps = new ArrayList<>();
            if(next != null) {
                Figure f = game.getCellFigure().get(next);
                while (next != null && f == null){
                    microSteps.add(new MicroStep(from, next, d, null));
                    next = next.getNeighbours().get(d);
                    f = game.getCellFigure().get(next);
                }
                answer.add(new Move(microSteps));
            }
        }
        return answer;
    }

}
