package com.example.demo.managers;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Class responsible for managing the game timeline.
 * This class provides functionality for initializing and managing the game loop.
 */
public class GameTimelineManager {

    private static final int MILLISECOND_DELAY = 50;
    private final Timeline timeline;

    /**
     * Constructs a GameTimelineManager with the specified update scene runnable.
     *
     * @param updateScene the runnable to update the game scene
     */
    public GameTimelineManager(Runnable updateScene) {
        this.timeline = new Timeline();
        initializeTimeline(updateScene);
    }

    /**
     * Initializes the timeline with the specified update scene runnable.
     *
     * @param updateScene the runnable to update the game scene
     */
    private void initializeTimeline(Runnable updateScene) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Starts the game timeline.
     */
    public void start() {
        timeline.play();
    }

    /**
     * Stops the game timeline.
     */
    public void stop() {
        timeline.stop();
    }
}