package com.example.demo.projectiles;

/**
 * Class representing a projectile fired by the boss in the game.
 * This class extends Projectile and provides specific behavior for the boss's projectile,
 * including movement and updating its position.
 */
public class BossProjectile extends Projectile {

    private static final String IMAGE_NAME = "fireball.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int HORIZONTAL_VELOCITY = -15;
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructs a BossProjectile with the specified initial Y position.
     *
     * @param initialYPos the initial Y position of the projectile
     */
    public BossProjectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the boss's projectile by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the boss's projectile, including its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}