package com.example.demo.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;

import java.util.List;

/**
 * Class responsible for managing collisions between actors in the game.
 * This class provides functionality for handling collisions between friendly units, enemy units,
 * user projectiles, and enemy projectiles.
 */
public class CollisionManager {

    private final ActorManager actorManager;
    private final UserPlane user;

    /**
     * Constructs a CollisionManager with the specified actor manager and user plane.
     *
     * @param actorManager the actor manager managing the actors in the game
     * @param user the user's plane
     */
    public CollisionManager(ActorManager actorManager, UserPlane user) {
        this.actorManager = actorManager;
        this.user = user;
    }

    /**
     * Handles all types of collisions in the game.
     */
    public void handleCollisions() {
        handlePlaneCollisions();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handleEnemyPenetration();
    }

    /**
     * Handles collisions between friendly units and enemy units.
     */
    private void handlePlaneCollisions() {
        handleCollisions(actorManager.getFriendlyUnits(), actorManager.getEnemyUnits());
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    private void handleUserProjectileCollisions() {
        for (ActiveActorDestructible projectile : actorManager.getUserProjectiles()) {
            for (ActiveActorDestructible enemy : actorManager.getEnemyUnits()) {
                if (projectile.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    enemy.takeDamage();
                    projectile.takeDamage();
                    if (enemy.isDestroyed()) {
                        user.incrementKillCount();
                    }
                }
            }
        }
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    private void handleEnemyProjectileCollisions() {
        handleCollisions(actorManager.getEnemyProjectiles(), actorManager.getFriendlyUnits());
    }

    /**
     * Handles collisions between two lists of actors.
     *
     * @param actors1 the first list of actors
     * @param actors2 the second list of actors
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles enemy units that have penetrated the user's defenses.
     */
    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : actorManager.getEnemyUnits()) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Checks if an enemy unit has penetrated the user's defenses.
     *
     * @param enemy the enemy unit to check
     * @return true if the enemy has penetrated the defenses, false otherwise
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > actorManager.getScreenWidth();
    }
}