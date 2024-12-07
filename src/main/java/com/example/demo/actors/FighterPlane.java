package com.example.demo.actors;

/**
 * Abstract class representing a fighter plane in the game.
 * This class extends ActiveActorDestructible and provides functionality for firing projectiles and taking damage.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

    private int health;

    /**
     * Constructs a FighterPlane with the specified image, initial position, and health.
     *
     * @param imageName   the name of the image file
     * @param imageHeight the height of the image
     * @param initialXPos the initial x position of the fighter plane
     * @param initialYPos the initial y position of the fighter plane
     * @param health      the initial health of the fighter plane
     */
    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    /**
     * Fires a projectile from the fighter plane.
     *
     * @return a new ActiveActorDestructible representing the projectile
     */
    public abstract ActiveActorDestructible fireProjectile();

    /**
     * Takes damage and updates the fighter plane's health.
     * If health reaches zero, the fighter plane is destroyed.
     */
    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    /**
     * Gets the x position for the projectile based on the specified offset.
     *
     * @param xPositionOffset the offset for the x position
     * @return the x position for the projectile
     */
    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    /**
     * Gets the y position for the projectile based on the specified offset.
     *
     * @param yPositionOffset the offset for the y position
     * @return the y position for the projectile
     */
    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    /**
     * Checks if the fighter plane's health is zero.
     *
     * @return true if health is zero, false otherwise
     */
    private boolean healthAtZero() {
        return health == 0;
    }

    /**
     * Gets the current health of the fighter plane.
     *
     * @return the current health
     */
    public int getHealth() {
        return health;
    }
}