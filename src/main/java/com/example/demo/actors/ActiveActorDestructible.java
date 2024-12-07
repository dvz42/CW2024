package com.example.demo.actors;

/**
 * Abstract class representing a destructible active actor in the game.
 * This class extends ActiveActor and implements the Destructible interface,
 * providing functionality for actors that can be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the specified image, initial
	 * position, and height.
	 *
	 * @param imageName   the name of the image file
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x position of the actor
	 * @param initialYPos the initial y position of the actor
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * This method should be implemented by subclasses to define specific movement
	 * behavior.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the actor.
	 * This method should be implemented by subclasses to define specific update
	 * behavior.
	 */
	public abstract void updateActor();

	/**
	 * Takes damage and updates the actor's state accordingly.
	 * This method should be implemented by subclasses to define specific damage
	 * behavior.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor, setting its destroyed state to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed state of the actor.
	 *
	 * @param isDestroyed the new destroyed state
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}