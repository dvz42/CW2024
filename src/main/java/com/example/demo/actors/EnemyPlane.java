package com.example.demo.actors;

import com.example.demo.projectiles.EnemyProjectile;

/**
 * Class representing an enemy plane in the game.
 * This class extends FighterPlane and provides specific behavior for the enemy plane,
 * including movement and firing projectiles.
 */
public class EnemyPlane extends FighterPlane {

    private static final int IMAGE_HEIGHT = 150;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private double fireRate = .02;

    /**
     * Constructs an EnemyPlane with the specified initial position, image name, and fire rate.
     *
     * @param initialXPos the initial x position of the enemy plane
     * @param initialYPos the initial y position of the enemy plane
     * @param imageName   the name of the image file for the enemy plane
     * @param fireRate    the rate at which the enemy plane fires projectiles
     */
    public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
        super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
        this.fireRate = fireRate;
    }

    /**
     * Updates the position of the enemy plane by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Fires a projectile from the enemy plane.
     *
     * @return a new EnemyProjectile if the enemy plane fires in the current frame, null otherwise
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < fireRate) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new EnemyProjectile(projectileXPosition, projectileYPostion);
        }
        Thread.yield();
        return null;
    }

    /**
     * Updates the state of the enemy plane, including its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}