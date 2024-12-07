package com.example.demo.managers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ActorManager class.
 * This class contains unit tests for the ActorManager class, testing its functionality for adding, updating, and removing actors.
 */
public class ActorManagerTest {

    private ActorManager actorManager;
    private UserPlane userPlane;

    /**
     * Sets up the test environment by initializing an ActorManager instance and a UserPlane instance before each test.
     */
    @BeforeEach
    public void setUp() {
        Group root = new Group();
        userPlane = new UserPlane(10);
        actorManager = new ActorManager(root, 800, 600, userPlane);
    }

    /**
     * Tests the addFriendlyUnit method to ensure a friendly unit is added correctly.
     */
    @Test
    public void testAddFriendlyUnit() {
        ActiveActorDestructible friendlyUnit = new UserPlane(10);
        actorManager.addFriendlyUnit(friendlyUnit);
        assertTrue(actorManager.getFriendlyUnits().contains(friendlyUnit));
    }

    /**
     * Tests the addEnemyUnit method to ensure an enemy unit is added correctly.
     */
    @Test
    public void testAddEnemyUnit() {
        ActiveActorDestructible enemyUnit = new UserPlane(10);
        actorManager.addEnemyUnit(enemyUnit);
        assertTrue(actorManager.getEnemyUnits().contains(enemyUnit));
    }

    /**
     * Tests the addUserProjectile method to ensure a user projectile is added correctly.
     */
    @Test
    public void testAddUserProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        actorManager.addUserProjectile(projectile);
        assertTrue(actorManager.getUserProjectiles().contains(projectile));
    }

    /**
     * Tests the addEnemyProjectile method to ensure an enemy projectile is added correctly.
     */
    @Test
    public void testAddEnemyProjectile() {
        ActiveActorDestructible projectile = new UserPlane(10).fireProjectile();
        actorManager.addEnemyProjectile(projectile);
        assertTrue(actorManager.getEnemyProjectiles().contains(projectile));
    }

    /**
     * Tests the removeAllDestroyedActors method to ensure all destroyed actors are removed correctly.
     */
    @Test
    public void testRemoveAllDestroyedActors() {
        ActiveActorDestructible enemyUnit = new UserPlane(10);
        actorManager.addEnemyUnit(enemyUnit);
        enemyUnit.destroy();
        actorManager.removeAllDestroyedActors();
        assertFalse(actorManager.getEnemyUnits().contains(enemyUnit));
    }
}