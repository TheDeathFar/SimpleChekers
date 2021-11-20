package ru.vsu.checkers.services;

import ru.vsu.checkers.model.Cell;
import ru.vsu.checkers.model.Direction;
import ru.vsu.checkers.model.Figure;
import ru.vsu.checkers.model.Game;

public class VisualCheckerGameService implements VisualGameService{

    @Override
    public void draw(Game game) {
        Cell l = game.getLeftUpCell();
        for(int i = 0; i < 8; i++){
            System.out.print("    " + i);
        }
        System.out.println();
        int pl = 0;
        while(l != null){
            Cell k = l;
            System.out.print("  ");
            for(int i = 0; i < 8; i++){
                String upU =  "═══";
                String upL =  "╒";
                String upR = "╕";
                System.out.print(upL + upU + upR);
            }
            System.out.println();
            System.out.print(pl + " ");
            pl++;
            while(k != null){
                String t = "   ";
                Figure f = game.getCellFigure().get(k);
                if(f != null){
                    t = f.toString();
                }
                String before = "|";
                String after = "|";
                System.out.print(before + t + after);
                k = k.getNeighbours().get(Direction.EAST);
            }
            System.out.println();
            System.out.print("  ");
            for(int p = 0; p < 8; p++){
                String downD = "═══";
                String downL =  "╘";
                String downR =  "╛";
                System.out.print(downL + downD + downR);
            }
            l = l.getNeighbours().get(Direction.SOUTH);
            System.out.println();
        }

    }
}
