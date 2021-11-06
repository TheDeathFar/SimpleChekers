package ru.vsu.checkers.component;

public class BasicCellChecker implements CellChecker {
    @Override
    public boolean isGoodCell(int i, int j) {
        return (i % 2 == 0 && j % 2 != 0) || (i % 2 != 0 && j % 2 == 0);
    }
}
