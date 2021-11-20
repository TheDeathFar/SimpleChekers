package ru.vsu.checkers.model;

public class Figure {
    private FigureType figureType;
    private Color color;

    public Figure(FigureType figureType, Color color) {
        this.figureType = figureType;
        this.color = color;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.getStrColor() + figureType + Color.getReset();
    }
}
