package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import com.example.demo.levels.LevelFactory;
import com.example.demo.levels.LevelParent;
import com.example.demo.utils.GameState;

public class Controller implements Observer {

    private final Stage stage;
    private LevelParent currentLevel;
    private final LevelFactory levelFactory;

    public Controller(Stage stage) {
        this.stage = stage;
        this.levelFactory = new LevelFactory();
    }

    public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.show();
        goToLevel(GameState.LEVEL_ONE);
    }

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

    private void showErrorAlert(Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(e.getClass().toString() + ": " + e.getMessage());
        alert.show();
    }

    private void stopGame() {
        if (currentLevel != null) {
            currentLevel.stopGame();
        }
    }
}