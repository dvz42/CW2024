package com.example.demo.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class representing the win image in the game.
 * This class extends ImageView and provides functionality for displaying the win image.
 */
public class WinImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;

    /**
     * Constructs a WinImage with the specified X and Y positions.
     *
     * @param xPosition the X position of the win image
     * @param yPosition the Y position of the win image
     */
    public WinImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Shows the win image.
     */
    public void showWinImage() {
        this.setVisible(true);
    }
}