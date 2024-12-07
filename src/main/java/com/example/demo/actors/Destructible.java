package com.example.demo.actors;

/**
 * Interface representing a destructible entity in the game.
 * This interface provides methods for taking damage and being destroyed.
 */
public interface Destructible {

    /**
     * Takes damage and updates the entity's state accordingly.
     */
    void takeDamage();

    /**
     * Destroys the entity, setting its destroyed state to true.
     */
    void destroy();
}