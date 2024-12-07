package com.example.demo.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import com.example.demo.actors.UserPlane;

import java.util.List;

public class CollisionManager {

    private final ActorManager actorManager;
    private final UserPlane user;

    public CollisionManager(ActorManager actorManager, UserPlane user) {
        this.actorManager = actorManager;
        this.user = user;
    }

    public void handleCollisions() {
        handlePlaneCollisions();
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handleEnemyPenetration();
    }

    private void handlePlaneCollisions() {
        handleCollisions(actorManager.getFriendlyUnits(), actorManager.getEnemyUnits());
    }

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

    private void handleEnemyProjectileCollisions() {
        handleCollisions(actorManager.getEnemyProjectiles(), actorManager.getFriendlyUnits());
    }

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

    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : actorManager.getEnemyUnits()) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > actorManager.getScreenWidth();
    }
}