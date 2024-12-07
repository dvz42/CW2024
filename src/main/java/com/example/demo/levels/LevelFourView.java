package com.example.demo.levels;

import com.example.demo.views.ShieldImage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Class representing the view for the fourth level of the game.
 * This class extends LevelView and provides specific behavior for displaying the fourth level,
 * including showing the boss's health and shield status.
 */
public class LevelFourView extends LevelView {

    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final Group root;
    private final ShieldImage shieldImage;
    private Label bossHealthLabel;

    /**
     * Constructs a LevelFourView with the specified root group and number of hearts to display.
     *
     * @param root the root group for the level view
     * @param heartsToDisplay the number of hearts to display
     */
    public LevelFourView(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
        addImagesToRoot();
    }

    /**
     * Adds images to the root group.
     */
    private void addImagesToRoot() {
        root.getChildren().addAll(shieldImage);
    }

    /**
     * Shows the shield image.
     */
    public void showShield() {
        shieldImage.showShield();
    }

    /**
     * Hides the shield image.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }

    /**
     * Shows the boss's health on the screen.
     *
     * @param health the health of the boss
     */
    public void showBossHealth(int health) {
        if (bossHealthLabel == null) {
            bossHealthLabel = new Label("Boss Health: " + health);
            bossHealthLabel.setLayoutX(100);
            bossHealthLabel.setLayoutY(100);
            bossHealthLabel.setTextFill(Color.RED);
            bossHealthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            root.getChildren().add(bossHealthLabel);
        } else {
            bossHealthLabel.setText("Boss Health: " + health);
        }
    }

    /**
     * Updates the boss's health display on the screen.
     *
     * @param health the updated health of the boss
     */
    public void updateBossHealth(int health) {
        if (bossHealthLabel != null) {
            bossHealthLabel.setText("Boss Health: " + health);
        }
    }
}