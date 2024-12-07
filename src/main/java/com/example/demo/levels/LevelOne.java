package com.example.demo.levels;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.EnemyPlane;
import com.example.demo.utils.GameState;

/**
 * Class representing the first level of the game.
 * This class extends LevelParent and provides specific behavior for the first level,
 * including initializing friendly units, checking game over conditions, and spawning enemy units.
 */
public class LevelOne extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .05;
    private static final int PLAYER_INITIAL_HEALTH = 7;
    private static final String ENEMY_IMAGE = "enemyplane.png";
    private static final double ENEMY_FIRE_RATE = .01;

    /**
     * Constructs a LevelOne with the specified screen dimensions.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     */
    public LevelOne(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the game is over by verifying if the user is destroyed or if the user has reached the kill target.
     * If the user is destroyed, the game is lost. If the user has reached the kill target, the game advances to the next level.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(GameState.LEVEL_TWO);
        }
    }

    /**
     * Initializes the friendly units for the level.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawns enemy units for the level.
     * If the number of current enemies is less than the total enemies, new enemies are spawned based on the spawn probability.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition,
                        ENEMY_IMAGE, ENEMY_FIRE_RATE);
                addEnemyUnit(newEnemy);
            } else {
                Thread.yield();
            }
        }
    }

    /**
     * Instantiates the level view for the first level.
     *
     * @return the instantiated LevelView
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    /**
     * Checks if the user has reached the kill target to advance to the next level.
     *
     * @return true if the user has reached the kill target, false otherwise
     */
    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}