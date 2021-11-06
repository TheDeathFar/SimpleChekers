package ru.vsu.checkers.services;

import ru.vsu.checkers.model.Figure;
import ru.vsu.checkers.model.Game;
import ru.vsu.checkers.model.Player;

public abstract class FigureServiceAbs implements FigureService{
    protected boolean isEnemyFigure(Figure f, Player forWho, Game game){
        return !game.getPlayerFigures().get(forWho).contains(f);
    }
}
