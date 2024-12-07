package com.example.demo.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class representing the shield image in the game.
 * This class extends ImageView and provides functionality for displaying the shield image.
 */
public class ShieldImage extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
    private static final int SHIELD_SIZE = 200;

    /**
     * Constructs a ShieldImage with the specified X and Y positions.
     *
     * @param xPosition the X position of the shield image
     * @param yPosition the Y position of the shield image
     */
    public ShieldImage(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    /**
     * Shows the shield image.
     */
    public void showShield() {
        this.setVisible(true);
    }

    /**
     * Hides the shield image.
     */
    public void hideShield() {
        this.setVisible(false);
    }
}