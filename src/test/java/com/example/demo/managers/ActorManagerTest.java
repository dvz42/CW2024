package com.example.demo.managers;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActorManagerTest {

    private ActorManager actorManager;
    private UserPlane userPlane;

    @BeforeEach
    public void setUp() {
        Group root = new Group();
        userPlane = new UserPlane(10);
        actorManager = new ActorManager(root, 800, 600, userPlane);
    }

    @Test
    public void testAddFriendlyUnit() {
        ActiveActorDestructible friendlyUnit = new UserPlane(10);
        actorManager.addFriendlyUnit(friendlyUnit);
        assertTrue(actorManager.getFriendlyUnits().contains(friendlyUnit));
    }

    @Test
    public void testAddEnemyUnit() {
        ActiveActorDestructible enemyUnit = new UserPlane(10);
        actorManager.addEnemyUnit(enemyUnit);
        assertTrue(actorManager.getEnemyUnits().contains(enemyUnit));
    }

    @Test
    public void testAddUserProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        actorManager.addUserProjectile(projectile);
        assertTrue(actorManager.getUserProjectiles().contains(projectile));
    }

    @Test
    public void testAddEnemyProjectile() {
        ActiveActorDestructible projectile = new UserPlane(10).fireProjectile();
        actorManager.addEnemyProjectile(projectile);
        assertTrue(actorManager.getEnemyProjectiles().contains(projectile));
    }

    @Test
    public void testRemoveAllDestroyedActors() {
        ActiveActorDestructible enemyUnit = new UserPlane(10);
        actorManager.addEnemyUnit(enemyUnit);
        enemyUnit.destroy();
        actorManager.removeAllDestroyedActors();
        assertFalse(actorManager.getEnemyUnits().contains(enemyUnit));
    }
}