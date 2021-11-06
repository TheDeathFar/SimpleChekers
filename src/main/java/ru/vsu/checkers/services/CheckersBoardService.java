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
        List<Cell> upCells = new ArrayList<>();
        Cell leftUp = new Cell();
        game.setLeftUpCell(leftUp);
        upCells.add(leftUp);
        Cell prev = leftUp;
        for(int i = 1; i < 8; i++){
            Cell curr = new Cell();
            prev.getNeighbours().put(Direction.EAST, curr);
            curr.getNeighbours().put(Direction.WEST, prev);
            prev = curr;
            upCells.add(curr);
        }

        for(int i = 1; i < 8; i++){
            List<Cell> prevUp = new ArrayList<>();
            for(int j = 0; j < 8; j++){
                Cell c = new Cell();
                prevUp.add(c);
                upCells.get(j).getNeighbours().put(Direction.SOUTH, c);
                c.getNeighbours().put(Direction.NORTH, upCells.get(j));
                if(j > 0){
                    c.getNeighbours().put(Direction.NORTH_WEST, upCells.get(j - 1));
                    upCells.get(j - 1).getNeighbours().put(Direction.SOUTH_EAST, c);
                    prev.getNeighbours().put(Direction.EAST, c);
                    c.getNeighbours().put(Direction.WEST, prev);
                }
                if(j+1 < 8){
                    c.getNeighbours().put(Direction.NORTH_EAST, upCells.get(j + 1));
                    upCells.get(j + 1).getNeighbours().put(Direction.SOUTH_WEST, c);
                }
            }
            upCells = prevUp;
        }
    }

    @Override
    public void setFigures(Game game, Player up, Player down) {
        Cell curr = game.getLeftUpCell();
        List<Direction> directionsForDown = Arrays.asList(Direction.NORTH_WEST, Direction.NORTH_EAST);
        List<Direction> directionsForUp = Arrays.asList(Direction.SOUTH_WEST, Direction.SOUTH_EAST);
        Map<Figure, List<Direction>> figureListMap = new HashMap<>();
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
                if (i < 4 || i > 5) {
                    if (cellChecker.isGoodCell(i, j)) {
                        f = new Figure(FigureType.Checker);
                        if (i < 4) {
                            figureForPlayerOne.add(f);
                            figureListMap.put(f, directionsForUp);
                        } else {
                            figureForPlayerTwo.add(f);
                            figureListMap.put(f, directionsForDown);
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
