package com.example.demo;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class for the Sky Battle game.
 * This class extends Application and provides the entry point for launching the game.
 */
public class SkyBattleApplication extends Application {

    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";
    private Controller myController;

    /**
     * Starts the application by setting up the stage and launching the game.
     *
     * @param stage the primary stage for this application
     * @throws ClassNotFoundException if the level class is not found
     * @throws NoSuchMethodException if the level class does not have the expected constructor
     * @throws SecurityException if there is a security violation
     * @throws InstantiationException if the level class cannot be instantiated
     * @throws IllegalAccessException if the level class or its constructor is not accessible
     * @throws IllegalArgumentException if the level class constructor arguments are invalid
     * @throws InvocationTargetException if the level class constructor throws an exception
     */
    @Override
    public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        myController = new Controller(stage);
        myController.launchGame();
    }

    /**
     * The main method that launches the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}