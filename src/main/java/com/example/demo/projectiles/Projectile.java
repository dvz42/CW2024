package com.example.demo.projectiles;

import com.example.demo.actors.ActiveActorDestructible;

/**
 * Abstract class representing a projectile in the game.
 * This class extends ActiveActorDestructible and provides basic functionality for projectiles,
 * including taking damage and updating position.
 */
public abstract class Projectile extends ActiveActorDestructible {

    /**
     * Constructs a Projectile with the specified image, initial position, and height.
     *
     * @param imageName   the name of the image file
     * @param imageHeight the height of the image
     * @param initialXPos the initial x position of the projectile
     * @param initialYPos the initial y position of the projectile
     */
    public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Takes damage and destroys the projectile.
     */
    @Override
    public void takeDamage() {
        this.destroy();
    }

    /**
     * Updates the position of the projectile.
     * This method should be implemented by subclasses to define specific movement behavior.
     */
    @Override
    public abstract void updatePosition();
}