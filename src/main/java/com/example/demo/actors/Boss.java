package com.example.demo.actors;

import java.util.*;

import com.example.demo.projectiles.BossProjectile;

/**
 * Class representing the boss enemy in the game.
 * This class extends FighterPlane and provides specific behavior for the boss,
 * including movement patterns, firing projectiles, and shield activation.
 */
public class Boss extends FighterPlane {

    private static final String IMAGE_NAME = "bossplane.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = .002;
    private static final int IMAGE_HEIGHT = 300;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 100;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 500;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;

    /**
     * Constructs a Boss with the specified initial position and health.
     */
    public Boss() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
    }

    /**
     * Updates the position of the boss based on its movement pattern.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the state of the boss, including its position and shield status.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
    }

    /**
     * Fires a projectile from the boss.
     *
     * @return a new BossProjectile if the boss fires in the current frame, null otherwise
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
    }

    /**
     * Takes damage and updates the boss's health.
     */
    @Override
    public void takeDamage() {
        // if (!isShielded) {
            super.takeDamage();
            System.out.println("Boss health: " + getHealth());
        // }
    }

    /**
     * Initializes the movement pattern for the boss.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Updates the shield status of the boss.
     */
    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Gets the next move in the boss's movement pattern.
     *
     * @return the next vertical move
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Determines if the boss should fire a projectile in the current frame.
     *
     * @return true if the boss should fire, false otherwise
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Gets the initial position for the boss's projectile.
     *
     * @return the y position for the projectile
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Determines if the boss's shield should be activated.
     *
     * @return true if the shield should be activated, false otherwise
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Determines if the boss's shield is exhausted.
     *
     * @return true if the shield is exhausted, false otherwise
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the boss's shield.
     */
    private void activateShield() {
        isShielded = true;
    }

    /**
     * Deactivates the boss's shield.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
    }
}