package ru.vsu.checkers.model;

public enum Color {
    Black("\u001B[30m"),
    White("\u001B[37m");
    private final String strColor;
    private final static String reset = "\u001B[0m";

    Color(String strColor){
        this.strColor = strColor;
    }

    public String getStrColor() {
        return strColor;
    }

    public static String getReset() {
        return reset;
    }
}
