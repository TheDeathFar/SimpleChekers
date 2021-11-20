package ru.vsu.checkers.model;

import ru.vsu.checkers.component.MoveValidator;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Game {
    private Cell leftUpCell;
    //all players settings
    private Queue<Player> players;

    private Map<Player, Set<Figure>> playerFigures;
    private Map<Player, MoveValidator> playerMoveValidatorMap;
    private Map<Player, List<Direction>> figureDirection;
    private GameStatus gameStatus;

    //actually for figures
    private Map<Cell, Figure> cellFigure;
    private Map<Figure, Cell> figureCell;

    private List<MoveInfo> moveInfoList;


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Map<Player, MoveValidator> getPlayerMoveValidatorMap() {
        return playerMoveValidatorMap;
    }

    public void setPlayerMoveValidatorMap(Map<Player, MoveValidator> playerMoveValidatorMap) {
        this.playerMoveValidatorMap = playerMoveValidatorMap;
    }

    public Map<Player, List<Direction>> getFigureDirection() {
        return figureDirection;
    }

    public void setFigureDirection(Map<Player, List<Direction>> figureDirection) {
        this.figureDirection = figureDirection;
    }

    public Cell getLeftUpCell() {
        return leftUpCell;
    }

    public void setLeftUpCell(Cell leftUpCell) {
        this.leftUpCell = leftUpCell;
    }

    public Queue<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }

    public Map<Player, Set<Figure>> getPlayerFigures() {
        return playerFigures;
    }

    public void setPlayerFigures(Map<Player, Set<Figure>> playerFigures) {
        this.playerFigures = playerFigures;
    }

    public Map<Cell, Figure> getCellFigure() {
        return cellFigure;
    }

    public void setCellFigure(Map<Cell, Figure> cellFigure) {
        this.cellFigure = cellFigure;
    }

    public Map<Figure, Cell> getFigureCell() {
        return figureCell;
    }

    public void setFigureCell(Map<Figure, Cell> figureCell) {
        this.figureCell = figureCell;
    }

    public List<MoveInfo> getMoveInfoList() {
        return moveInfoList;
    }

    public void setMoveInfoList(List<MoveInfo> moveInfoList) {
        this.moveInfoList = moveInfoList;
    }
}
