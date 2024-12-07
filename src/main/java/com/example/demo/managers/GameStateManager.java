package com.example.demo.managers;

import com.example.demo.utils.GameState;

import java.util.Observable;

/**
 * Class responsible for managing the game state.
 * This class extends Observable and provides functionality for transitioning between game states.
 */
public class GameStateManager extends Observable {

    private GameState currentState;

    /**
     * Constructs a GameStateManager with the specified initial state.
     *
     * @param initialState the initial state of the game
     */
    public GameStateManager(GameState initialState) {
        this.currentState = initialState;
    }

    /**
     * Transitions to the next level if the next state is different from the current state.
     *
     * @param nextState the next state to transition to
     */
    public void goToNextLevel(GameState nextState) {
        if (currentState == nextState) {
            return;
        }
        setChanged();
        notifyObservers(nextState);
        currentState = nextState;
    }

    /**
     * Gets the current state of the game.
     *
     * @return the current state
     */
    public GameState getCurrentState() {
        return currentState;
    }
}