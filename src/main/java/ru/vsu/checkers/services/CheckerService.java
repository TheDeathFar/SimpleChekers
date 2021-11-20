package ru.vsu.checkers.services;

import ru.vsu.checkers.model.*;

import java.util.ArrayList;
import java.util.List;

public class CheckerService extends FigureServiceAbs{
    private static final QueenService queenService = new QueenService();

    @Override
    public List<Move> getNecessaryMoves(Cell from, Game game, Player forWho) {
        if(isItEnd(forWho, from, game)) return queenService.getNecessaryMoves(from, game, forWho);
        List<Move> answer = new ArrayList<>();
        for(Direction d : enumHelper.getDiagonalDirections()){
            List<MicroStep> afterJump = tryToJump(from, d, game, forWho);
            if(!afterJump.isEmpty()){
                for(MicroStep ms : afterJump) {
                    List<Move> newOne = getNecessaryMoves(ms.to(), game, forWho);
                    if(newOne.isEmpty()){
                        List<MicroStep> steps = new ArrayList<>();
                        steps.add(ms);
                        answer.add(new Move(steps));
                    }else {
                        for (Move m : newOne) {
                            List<MicroStep> mc = new ArrayList<>();
                            mc.add(ms);
                            mc.addAll(m.path());
                            Move move = new Move(mc);
                            answer.add(move);
                        }
                    }
                    cancelJump(ms, game);
                }
            }
        }
        return answer;
    }

    @Override
    public List<Move> getSimpleMoves(Cell from, Game game, Player forWho) {
        List<Move> answer = new ArrayList<>();
        List<Direction> myDirections = game.getFigureDirection().get(forWho);
        for(Direction d : myDirections){
            List<MicroStep> steps = new ArrayList<>();
            Cell next = from.getNeighbours().get(d);
            if(next != null){
                Figure f = game.getCellFigure().get(next);
                if(f == null){
                    steps.add(new MicroStep(from, next, d, null));
                    answer.add(new Move(steps));
                }
            }
        }
        return answer;
    }

    @Override
    protected List<MicroStep> tryToJump(Cell from, Direction direction, Game game, Player who) {
        List<MicroStep> answer = new ArrayList<>();
        Cell next = from.getNeighbours().get(direction);
        if(next != null){
            Figure f = game.getCellFigure().get(next);
            if(f != null && isEnemyFigure(f, who, game)){
                Cell nextNext = next.getNeighbours().get(direction);
                if(nextNext != null){
                    Figure fig = game.getCellFigure().get(nextNext);
                    if(fig == null){
                        game.getCellFigure().put(next, null);
                        answer.add(new MicroStep(from, nextNext, direction, f));
                    }
                }
            }
        }
        return answer;
    }

    @Override
    protected void doStep(MicroStep microStep, Game game) {
        Figure f = game.getCellFigure().get(microStep.from());
        if(f != null){
            Player owner = getOwner(f, game);
            if(owner != null){
                if(isItEnd(owner, microStep.to(), game))
                f.setFigureType(FigureType.Queen);
            }
        }
        super.doStep(microStep, game);
    }

    private boolean isItEnd(Player player, Cell cell, Game game){
        List<Direction> directions = game.getFigureDirection().get(player);
        for(Direction d : directions){
            Cell next = cell.getNeighbours().get(d);
            if(next != null){
                return false;
            }
        }
        return true;
    }
}
