package com.example.demo.levels;

import com.example.demo.actors.Boss;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background3.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 50;
    private final Boss boss;
    private LevelFourView levelView;

    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            levelView.showBossHealth(boss.getHealth());
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelFourView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

    @Override
    public void updateActors() {
        super.updateActors();
        levelView.updateBossHealth(boss.getHealth());

    }
}