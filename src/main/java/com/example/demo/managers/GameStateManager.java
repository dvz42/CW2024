package com.example.demo.managers;

import com.example.demo.utils.GameState;

import java.util.Observable;

public class GameStateManager extends Observable {

    private GameState currentState;

    public GameStateManager(GameState initialState) {
        this.currentState = initialState;
    }

    public void goToNextLevel(GameState nextState) {
        if (currentState == nextState) {
            return;
        }
        setChanged();
        notifyObservers(nextState);
        currentState = nextState;
    }

    public GameState getCurrentState() {
        return currentState;
    }
}