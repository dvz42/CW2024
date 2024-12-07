package com.example.demo.actors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.projectiles.UserProjectile;

/**
 * Test class for the UserPlane class.
 * This class contains unit tests for the UserPlane class, testing its movement, firing projectiles, taking damage, and kill count.
 */
public class UserPlaneTest {

    private UserPlane userPlane;

    /**
     * Sets up the test environment by initializing a UserPlane instance before each test.
     */
    @BeforeEach
    public void setUp() {
        userPlane = new UserPlane(10);
    }

    /**
     * Tests the moveUp method to ensure the user's plane moves up correctly.
     */
    @Test
    public void testMoveUp() {
        userPlane.moveUp();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() < 0);
    }

    /**
     * Tests the moveDown method to ensure the user's plane moves down correctly.
     */
    @Test
    public void testMoveDown() {
        userPlane.moveDown();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() > 0);
    }

    /**
     * Tests the fireProjectile method to ensure the user's plane fires a projectile correctly.
     */
    @Test
    public void testFireProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        assertNotNull(projectile);
        assertTrue(projectile instanceof UserProjectile);
    }

    /**
     * Tests the takeDamage method to ensure the user's plane takes damage correctly.
     */
    @Test
    public void testTakeDamage() {
        int initialHealth = userPlane.getHealth();
        userPlane.takeDamage();
        assertEquals(initialHealth - 1, userPlane.getHealth());
    }

    /**
     * Tests the incrementKillCount method to ensure the user's plane increments the kill count correctly.
     */
    @Test
    public void testIncrementKillCount() {
        int initialKills = userPlane.getNumberOfKills();
        userPlane.incrementKillCount();
        assertEquals(initialKills + 1, userPlane.getNumberOfKills());
    }
}