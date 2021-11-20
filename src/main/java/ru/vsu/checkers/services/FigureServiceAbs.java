package ru.vsu.checkers.services;

import ru.vsu.checkers.component.EnumHelper;
import ru.vsu.checkers.model.*;
import java.util.List;

public abstract class FigureServiceAbs implements FigureService{
    protected static final GameService gameService = new CheckersGameService();
    protected static final EnumHelper enumHelper = new EnumHelper();
    protected boolean isEnemyFigure(Figure f, Player forWho, Game game){
        return !game.getPlayerFigures().get(forWho).contains(f);
    }

    @Override
    public List<Move> getPossibleMoves(Cell from, Game game, Player forWho) {
        List<Move> necessary = getNecessaryMoves(from, game, forWho);
        if(necessary.isEmpty() && !gameService.hasAnyNecessaryMove(forWho, game)){
            return getSimpleMoves(from, game, forWho);
        }
        return necessary;
    }

    protected abstract List<MicroStep> tryToJump(Cell from, Direction direction, Game game, Player who);
    protected void cancelJump(MicroStep step, Game game){
        Figure bitten = step.bittenFigure();
        Cell itsCell = game.getFigureCell().get(bitten);
        game.getCellFigure().put(itsCell, bitten);
    }

    @Override
    public void doMove(Move move, Game game) {
        move.path().forEach(ms -> doStep(ms, game));
    }

    protected void doStep(MicroStep microStep, Game game){
        Figure active = game.getCellFigure().get(microStep.from());
        if(active == null) return;
        game.getCellFigure().put(microStep.from(), null);
        game.getCellFigure().put(microStep.to(), active);
        game.getFigureCell().put(active, microStep.to());
        if(microStep.bittenFigure() != null){
            Cell cellOfBitten = game.getFigureCell().get(microStep.bittenFigure());
            game.getCellFigure().put(cellOfBitten, null);
            game.getFigureCell().put(microStep.bittenFigure(),null);
            Player pl = getOwner(microStep.bittenFigure(), game);
            if(pl != null){
                game.getPlayerFigures().get(pl).remove(microStep.bittenFigure());
            }
        }
    }

    protected Player getOwner(Figure f, Game game){
        for(var l : game.getPlayerFigures().entrySet()){
            if(l.getValue().contains(f))return l.getKey();
        }
        return null;
    }
}
