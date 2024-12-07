package com.example.demo.levels;

import com.example.demo.views.ShieldImage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LevelFourView extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;
	private Label bossHealthLabel;

	public LevelFourView(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

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

	public void updateBossHealth(int health) {
		if (bossHealthLabel != null) {
			System.out.println("Updating boss health");
			bossHealthLabel.setText("Boss Health: " + health);
		}
	}
}