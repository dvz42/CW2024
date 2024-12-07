package com.example.demo.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class representing the game over image in the game.
 * This class extends ImageView and provides functionality for displaying the game over image.
 */
public class GameOverImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

    /**
     * Constructs a GameOverImage with the specified X and Y positions.
     *
     * @param xPosition the X position of the game over image
     * @param yPosition the Y position of the game over image
     */
    public GameOverImage(double xPosition, double yPosition) {
        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setLayoutX(xPosition);
        setLayoutY(yPosition);
    }
}