package com.example.demo.managers;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class responsible for managing the background of the game.
 * This class provides functionality for initializing and managing the background image.
 */
public class BackgroundManager {

    private final ImageView background;

    /**
     * Constructs a BackgroundManager with the specified background image, screen dimensions, and root group.
     *
     * @param backgroundImageName the name of the background image file
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param root the root group for the background
     */
    public BackgroundManager(String backgroundImageName, double screenHeight, double screenWidth, Group root) {
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        initializeBackground(screenHeight, screenWidth, root);
    }

    /**
     * Initializes the background with the specified screen dimensions and adds it to the root group.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param root the root group for the background
     */
    private void initializeBackground(double screenHeight, double screenWidth, Group root) {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        root.getChildren().add(background);
    }

    /**
     * Requests focus for the background.
     */
    public void requestFocus() {
        background.requestFocus();
    }
}