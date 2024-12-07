package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import com.example.demo.GameState;
import com.example.demo.LevelParent;

public class Controller implements Observer {

	private static final Map<GameState, String> LEVEL_MAP = Map.of(
			GameState.LEVEL_ONE, "com.example.demo.LevelOne",
			GameState.LEVEL_TWO, "com.example.demo.LevelTwo",
			GameState.LEVEL_THREE, "com.example.demo.LevelFour"
	// Add more levels as needed
	);

	private final Stage stage;
	private LevelParent currentLevel;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		stage.show();
		goToLevel(GameState.LEVEL_ONE);
	}

	private void goToLevel(GameState state) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String className = LEVEL_MAP.get(state);
		if (className == null) {
			throw new IllegalArgumentException("Invalid game state: " + state);
		}
		System.out.println("Transitioning to level: " + className);

		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		currentLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
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