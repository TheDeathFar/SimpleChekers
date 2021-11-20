package ru.vsu.checkers.model;
public enum FigureType {
    Checker(" ◉ "),
    Queen(" ♟ ");

    private final String string;

    FigureType(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }
}
