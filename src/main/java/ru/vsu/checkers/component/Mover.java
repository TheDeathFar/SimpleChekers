package ru.vsu.checkers.component;

import ru.vsu.checkers.model.Move;

@FunctionalInterface
public interface Mover {
    Move getMove();
}
