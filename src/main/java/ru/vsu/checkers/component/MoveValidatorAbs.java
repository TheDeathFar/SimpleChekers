package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Move;

import java.util.List;

public abstract class MoveValidatorAbs implements MoveValidator{

    protected boolean hasEquals(List<Move> moveList, Move move){
        for(Move m : moveList){
            if(m.equals(move)) return true;
        }
        return false;
    }
}
