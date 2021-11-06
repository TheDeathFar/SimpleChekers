package ru.vsu.checkers.model;

public record MicroStep(Cell from, Cell to, Direction direction) {
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(getClass() != obj.getClass()) return false;
        MicroStep ms = (MicroStep) obj;
        return from == ms.from() && to == ms.to;
    }
}
