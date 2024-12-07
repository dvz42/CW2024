package com.example.demo.projectiles;

/**
 * Class representing a projectile fired by an enemy in the game.
 * This class extends Projectile and provides specific behavior for the enemy's projectile,
 * including movement and updating its position.
 */
public class EnemyProjectile extends Projectile {

    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -10;

    /**
     * Constructs an EnemyProjectile with the specified initial X and Y positions.
     *
     * @param initialXPos the initial X position of the projectile
     * @param initialYPos the initial Y position of the projectile
     */
    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    /**
     * Updates the position of the enemy's projectile by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the enemy's projectile, including its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
