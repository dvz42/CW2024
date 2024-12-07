package com.example.demo.levels;

import com.example.demo.actors.Boss;

/**
 * Class representing the fourth level of the game.
 * This class extends LevelParent and provides specific behavior for the fourth level,
 * including initializing friendly units, checking game over conditions, and spawning enemy units.
 */
public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 10;
    private final Boss boss;
    private LevelFourView levelView;

    /**
     * Constructs a LevelFour with the specified screen dimensions.
     *
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     */
    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
    }

    /**
     * Initializes the friendly units for the level.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Checks if the game is over by verifying if the user or the boss is destroyed.
     * If the user is destroyed, the game is lost. If the boss is destroyed, the game is won.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    /**
     * Spawns enemy units for the level.
     * If there are no current enemies, the boss is added as an enemy unit and its health is displayed.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            levelView.showBossHealth(boss.getHealth());
        }
    }

    /**
     * Instantiates the level view for the fourth level.
     *
     * @return the instantiated LevelView
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelFourView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    /**
     * Updates the actors in the level, including updating the boss's health display.
     */
    @Override
    public void updateActors() {
        super.updateActors();
        levelView.updateBossHealth(boss.getHealth());
    }
}