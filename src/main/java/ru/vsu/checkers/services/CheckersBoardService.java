package ru.vsu.checkers.services;

import ru.vsu.checkers.component.BasicCellChecker;
import ru.vsu.checkers.component.CellChecker;
import ru.vsu.checkers.model.*;

import java.util.*;

public class CheckersBoardService implements BoardService{
    private final CellChecker cellChecker;

    public CheckersBoardService() {
        this.cellChecker = new BasicCellChecker();
    }

    @Override
    public void setBoard(Game game) {
        Cell[][] board = new Cell[8][8];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                Cell c = new Cell();
                board[i][j] = c;
                createConnection(i, j, board);
            }
        }
        game.setLeftUpCell(board[0][0]);
    }

    private void createConnection(int i, int j, Cell[][] cells){
        Cell curr = cells[i][j];
        if(isInside(i, j-1, cells)){
            Cell need = cells[i][j-1];
            curr.getNeighbours().put(Direction.WEST, need);
            need.getNeighbours().put(Direction.EAST, curr);
        }
        if(isInside(i-1, j-1, cells)){
            Cell need = cells[i-1][j-1];
            need.getNeighbours().put(Direction.SOUTH_EAST, curr);
            curr.getNeighbours().put(Direction.NORTH_WEST, need);
        }
        if(isInside(i-1, j, cells)){
            Cell need = cells[i-1][j];
            need.getNeighbours().put(Direction.SOUTH, curr);
            curr.getNeighbours().put(Direction.NORTH, need);
        }
        if(isInside(i-1, j+1, cells)){
            Cell need = cells[i-1][j+1];
            need.getNeighbours().put(Direction.SOUTH_WEST, curr);
            curr.getNeighbours().put(Direction.NORTH_EAST, need);
        }
    }

    private boolean isInside(int i, int j, Cell[][] cells){
        return i >= 0 && i < cells.length && j >= 0 && j < cells[i].length;
    }

    @Override
    public void setFigures(Game game, Player up, Player down) {
        Cell curr = game.getLeftUpCell();
        List<Direction> directionsForDown = Arrays.asList(Direction.NORTH_WEST, Direction.NORTH_EAST);
        List<Direction> directionsForUp = Arrays.asList(Direction.SOUTH_WEST, Direction.SOUTH_EAST);
        Map<Player, List<Direction>> figureListMap = new HashMap<>();
        figureListMap.put(down, directionsForDown);
        figureListMap.put(up, directionsForUp);
        Set<Figure> figureForPlayerOne = new HashSet<>();
        Set<Figure> figureForPlayerTwo = new HashSet<>();
        Map<Cell, Figure> cellFigureMap = new HashMap<>();
        Map<Figure, Cell> figureCellMap = new HashMap<>();
        Map<Player, Set<Figure>> playerSetMap = new HashMap<>();
        playerSetMap.put(up, figureForPlayerOne);
        playerSetMap.put(down, figureForPlayerTwo);
        for(int i = 0; i < 8; i++){
            Cell c = curr;
            for(int j = 0; j < 8; j++){
                Figure f = null;
                if (i < 3 || i > 4) {
                    if (cellChecker.isGoodCell(i, j)) {
                        if (i < 4) {
                            f = new Figure(FigureType.Checker, Color.White);
                            figureForPlayerOne.add(f);
                        } else {
                            f = new Figure(FigureType.Checker, Color.Black);
                            figureForPlayerTwo.add(f);
                        }
                        figureCellMap.put(f, c);
                    }
                }
                cellFigureMap.put(c, f);
                c = c.getNeighbours().get(Direction.EAST);
            }
            curr = curr.getNeighbours().get(Direction.SOUTH);
        }
        game.setCellFigure(cellFigureMap);
        game.setFigureCell(figureCellMap);
        playerSetMap.put(up, figureForPlayerOne);
        playerSetMap.put(down, figureForPlayerTwo);
        game.setPlayerFigures(playerSetMap);
        game.setFigureDirection(figureListMap);
    }
}
