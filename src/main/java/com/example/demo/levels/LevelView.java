package com.example.demo.levels;

import com.example.demo.views.GameOverImage;
import com.example.demo.views.HeartDisplay;
import com.example.demo.views.WinImage;

import javafx.scene.Group;

/**
 * Class representing the view for a level in the game.
 * This class provides functionality for displaying the level, including showing the heart display,
 * win image, and game over image.
 */
public class LevelView {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 175;
    private static final int LOSS_SCREEN_X_POSITION = -160;
    private static final int LOSS_SCREEN_Y_POSISITION = -375;
    private final Group root;
    private final WinImage winImage;
    private final GameOverImage gameOverImage;
    private final HeartDisplay heartDisplay;

    /**
     * Constructs a LevelView with the specified root group and number of hearts to display.
     *
     * @param root the root group for the level view
     * @param heartsToDisplay the number of hearts to display
     */
    public LevelView(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
        this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
    }

    /**
     * Shows the heart display on the screen.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Shows the win image on the screen.
     */
    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
    }

    /**
     * Shows the game over image on the screen.
     */
    public void showGameOverImage() {
        root.getChildren().add(gameOverImage);
    }

    /**
     * Removes hearts from the heart display based on the number of hearts remaining.
     *
     * @param heartsRemaining the number of hearts remaining
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
}