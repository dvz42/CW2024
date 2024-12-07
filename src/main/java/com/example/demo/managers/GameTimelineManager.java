package com.example.demo.managers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameTimelineManager {

    private static final int MILLISECOND_DELAY = 50;
    private final Timeline timeline;

    public GameTimelineManager(Runnable updateScene) {
        this.timeline = new Timeline();
        initializeTimeline(updateScene);
    }

    private void initializeTimeline(Runnable updateScene) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }
}