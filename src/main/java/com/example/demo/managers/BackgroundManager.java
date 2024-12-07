package com.example.demo.managers;

import com.example.demo.actors.UserPlane;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackgroundManager {

    private final ImageView background;

    public BackgroundManager(String backgroundImageName, double screenHeight, double screenWidth, Group root) {
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        initializeBackground(screenHeight, screenWidth, root);
    }

    private void initializeBackground(double screenHeight, double screenWidth, Group root) {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        root.getChildren().add(background);
    }

    public void requestFocus() {
        background.requestFocus();
    }
}