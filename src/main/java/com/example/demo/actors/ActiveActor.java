package com.example.demo.actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Abstract class representing an active actor in the game.
 * This class extends ImageView and provides basic functionality for moving the actor.
 */
public abstract class ActiveActor extends ImageView {

    private static final String IMAGE_LOCATION = "/com/example/demo/images/";

    /**
     * Constructs an ActiveActor with the specified image, initial position, and height.
     *
     * @param imageName   the name of the image file
     * @param imageHeight the height of the image
     * @param initialXPos the initial x position of the actor
     * @param initialYPos the initial y position of the actor
     */
    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    /**
     * Updates the position of the actor.
     * This method should be implemented by subclasses to define specific movement behavior.
     */
    public abstract void updatePosition();

    /**
     * Moves the actor horizontally by the specified amount.
     *
     * @param horizontalMove the amount to move horizontally
     */
    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    /**
     * Moves the actor vertically by the specified amount.
     *
     * @param verticalMove the amount to move vertically
     */
    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}