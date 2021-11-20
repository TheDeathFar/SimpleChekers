package ru.vsu.checkers.model;

import java.util.Objects;

public record MicroStep(Cell from, Cell to, Direction direction, Figure bittenFigure) {
    @Override
    public int hashCode() {
        return Objects.hash(from, to, direction, bittenFigure);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        MicroStep ms = (MicroStep) obj;
        return from == ms.from() && to == ms.to && direction==ms.direction && bittenFigure == ms.bittenFigure;
    }
}
