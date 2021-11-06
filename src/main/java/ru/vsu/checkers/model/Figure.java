package ru.vsu.checkers.model;

public class Figure {
    private FigureType figureType;

    public Figure(FigureType figureType) {
        this.figureType = figureType;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
    }
}
