package com.example.demo.actors;

import com.example.demo.projectiles.UserProjectile;

/**
 * Class representing the user's plane in the game.
 * This class extends FighterPlane and provides specific behavior for the user's plane,
 * including movement, firing projectiles, and tracking the number of kills.
 */
public class UserPlane extends FighterPlane {

    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = -40;
    private static final double Y_LOWER_BOUND = 600.0;
    private static final double X_LEFT_BOUND = 0.0;
    private static final double X_RIGHT_BOUND = 800.0;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 150;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HORIZONTAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private int verticalVelocityMultiplier;
    private int horizontalVelocityMultiplier;
    private int numberOfKills;

    /**
     * Constructs a UserPlane with the specified initial health.
     *
     * @param initialHealth the initial health of the user's plane
     */
    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
    }

    /**
     * Updates the position of the user's plane based on its velocity multipliers.
     */
    @Override
    public void updatePosition() {
        if (isMoving()) {
            double initialTranslateY = getTranslateY();
            double initialTranslateX = getTranslateX();
            this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
            this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
            double newYPosition = getLayoutY() + getTranslateY();
            double newXPosition = getLayoutX() + getTranslateX();
            if (newYPosition < Y_UPPER_BOUND || newYPosition > Y_LOWER_BOUND) {
                this.setTranslateY(initialTranslateY);
            }
            if (newXPosition < X_LEFT_BOUND || newXPosition > X_RIGHT_BOUND) {
                this.setTranslateX(initialTranslateX);
            }
        }
    }

    /**
     * Updates the state of the user's plane, including its position.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Fires a projectile from the user's plane.
     *
     * @return a new UserProjectile representing the projectile
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        double projectileXPosition = getLayoutX() + getTranslateX() + PROJECTILE_X_POSITION;
        double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
        return new UserProjectile(projectileXPosition, projectileYPosition);
    }

    /**
     * Checks if the user's plane is moving.
     *
     * @return true if the plane is moving, false otherwise
     */
    private boolean isMoving() {
        return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
    }

    /**
     * Moves the user's plane up by setting the vertical velocity multiplier.
     */
    public void moveUp() {
        verticalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane down by setting the vertical velocity multiplier.
     */
    public void moveDown() {
        verticalVelocityMultiplier = 1;
    }

    /**
     * Moves the user's plane left by setting the horizontal velocity multiplier.
     */
    public void moveLeft() {
        horizontalVelocityMultiplier = -1;
    }

    /**
     * Moves the user's plane right by setting the horizontal velocity multiplier.
     */
    public void moveRight() {
        horizontalVelocityMultiplier = 1;
    }

    /**
     * Stops the vertical movement of the user's plane by resetting the vertical velocity multiplier.
     */
    public void stopVertical() {
        verticalVelocityMultiplier = 0;
    }

    /**
     * Stops the horizontal movement of the user's plane by resetting the horizontal velocity multiplier.
     */
    public void stopHorizontal() {
        horizontalVelocityMultiplier = 0;
    }

    /**
     * Gets the number of kills made by the user's plane.
     *
     * @return the number of kills
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the kill count of the user's plane by one.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }
}