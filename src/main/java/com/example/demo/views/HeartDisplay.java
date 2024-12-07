package com.example.demo.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class representing the heart display in the game.
 * This class provides functionality for displaying and managing the heart images.
 */
public class HeartDisplay {

    private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
    private static final int HEART_HEIGHT = 50;
    private static final int INDEX_OF_FIRST_ITEM = 0;
    private HBox container;
    private double containerXPosition;
    private double containerYPosition;
    private int numberOfHeartsToDisplay;

    /**
     * Constructs a HeartDisplay with the specified position and number of hearts to display.
     *
     * @param xPosition the X position of the heart display
     * @param yPosition the Y position of the heart display
     * @param heartsToDisplay the number of hearts to display
     */
    public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    /**
     * Initializes the container for the heart display.
     */
    private void initializeContainer() {
        container = new HBox();
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    /**
     * Initializes the hearts in the heart display.
     */
    private void initializeHearts() {
        for (int i = 0; i < numberOfHeartsToDisplay; i++) {
            ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
            heart.setFitHeight(HEART_HEIGHT);
            heart.setPreserveRatio(true);
            container.getChildren().add(heart);
        }
    }

    /**
     * Removes a heart from the heart display.
     */
    public void removeHeart() {
        if (!container.getChildren().isEmpty())
            container.getChildren().remove(INDEX_OF_FIRST_ITEM);
    }

    /**
     * Gets the container for the heart display.
     *
     * @return the container for the heart display
     */
    public HBox getContainer() {
        return container;
    }
}