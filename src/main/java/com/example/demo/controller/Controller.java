package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import com.example.demo.levels.LevelFactory;
import com.example.demo.levels.LevelParent;
import com.example.demo.utils.GameState;

/**
 * Controller class responsible for managing the game flow and interactions
 * between the view and the model.
 */
public class Controller implements Observer {

    private final Stage stage;
    private LevelParent currentLevel;
    private final LevelFactory levelFactory;

    /**
     * Constructs a Controller with the specified stage.
     *
     * @param stage the primary stage of the application
     */
    public Controller(Stage stage) {
        this.stage = stage;
        this.levelFactory = new LevelFactory();
    }

    /**
     * Launches the game by showing the stage and starting the first level.
     *
     * @throws ClassNotFoundException    if the level class is not found
     * @throws NoSuchMethodException     if the level class does not have the
     *                                   expected constructor
     * @throws SecurityException         if there is a security violation
     * @throws InstantiationException    if the level class cannot be instantiated
     * @throws IllegalAccessException    if the level class or its constructor is
     *                                   not accessible
     * @throws IllegalArgumentException  if the level class constructor arguments
     *                                   are invalid
     * @throws InvocationTargetException if the level class constructor throws an
     *                                   exception
     */
    public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.show();
        goToLevel(GameState.LEVEL_ONE);
    }

    /**
     * Transitions to the specified level.
     *
     * @param state the game state representing the level to transition to
     * @throws ClassNotFoundException    if the level class is not found
     * @throws NoSuchMethodException     if the level class does not have the
     *                                   expected constructor
     * @throws SecurityException         if there is a security violation
     * @throws InstantiationException    if the level class cannot be instantiated
     * @throws IllegalAccessException    if the level class or its constructor is
     *                                   not accessible
     * @throws IllegalArgumentException  if the level class constructor arguments
     *                                   are invalid
     * @throws InvocationTargetException if the level class constructor throws an
     *                                   exception
     */
    private void goToLevel(GameState state) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        currentLevel = levelFactory.createLevel(state, stage.getHeight(), stage.getWidth());
        if (currentLevel == null) {
            throw new IllegalArgumentException("Invalid game state: " + state);
        }

        currentLevel.addObserver(this);
        Scene scene = currentLevel.initializeScene();
        stage.setScene(scene);
        currentLevel.startGame();
    }

    /**
     * Updates the controller based on the observed changes.
     *
     * @param o   the observable object
     * @param arg the argument passed to the notifyObservers method
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GameState) {
            try {
                goToLevel((GameState) arg);
            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                    | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                stopGame();
                showErrorAlert(e);
                stage.close();
            }
        }
    }

    /**
     * Shows an error alert with the specified exception details.
     *
     * @param e the exception to be displayed in the alert
     */
    private void showErrorAlert(Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(e.getClass().toString() + ": " + e.getMessage());
        alert.show();
    }

    /**
     * Stops the current level.
     */
    private void stopGame() {
        if (currentLevel != null) {
            currentLevel.stopGame();
        }
    }
}