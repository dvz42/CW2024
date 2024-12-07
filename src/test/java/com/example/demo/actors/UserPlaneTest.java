package com.example.demo.actors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.projectiles.UserProjectile;

public class UserPlaneTest {

    private UserPlane userPlane;

    @BeforeEach
    public void setUp() {
        userPlane = new UserPlane(10);
    }

    @Test
    public void testMoveUp() {
        userPlane.moveUp();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() < 0);
    }

    @Test
    public void testMoveDown() {
        userPlane.moveDown();
        userPlane.updatePosition();
        assertTrue(userPlane.getTranslateY() > 0);
    }

    @Test
    public void testFireProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        assertNotNull(projectile);
        assertTrue(projectile instanceof UserProjectile);
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = userPlane.getHealth();
        userPlane.takeDamage();
        assertEquals(initialHealth - 1, userPlane.getHealth());
    }

    @Test
    public void testIncrementKillCount() {
        int initialKills = userPlane.getNumberOfKills();
        userPlane.incrementKillCount();
        assertEquals(initialKills + 1, userPlane.getNumberOfKills());
    }
}