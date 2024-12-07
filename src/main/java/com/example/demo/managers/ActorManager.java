package com.example.demo.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.FighterPlane;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for managing the actors in the game.
 * This class provides functionality for adding, updating, and removing actors,
 * as well as managing projectiles and checking for out-of-bounds actors.
 */
public class ActorManager {

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;
    private final double screenWidth;
    private final double screenHeight;

    /**
     * Constructs an ActorManager with the specified root group, screen dimensions, and user plane.
     *
     * @param root the root group for the actors
     * @param screenWidth the width of the screen
     * @param screenHeight the height of the screen
     * @param user the user's plane
     */
    public ActorManager(Group root, double screenWidth, double screenHeight, FighterPlane user) {
        this.root = root;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        friendlyUnits.add(user);
    }

    /**
     * Adds a friendly unit to the game.
     *
     * @param unit the friendly unit to add
     */
    public void addFriendlyUnit(ActiveActorDestructible unit) {
        friendlyUnits.add(unit);
        root.getChildren().add(unit);
    }

    /**
     * Adds an enemy unit to the game.
     *
     * @param unit the enemy unit to add
     */
    public void addEnemyUnit(ActiveActorDestructible unit) {
        enemyUnits.add(unit);
        root.getChildren().add(unit);
    }

    /**
     * Adds a user projectile to the game.
     *
     * @param projectile the user projectile to add
     */
    public void addUserProjectile(ActiveActorDestructible projectile) {
        userProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    /**
     * Adds an enemy projectile to the game.
     *
     * @param projectile the enemy projectile to add
     */
    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        enemyProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    /**
     * Updates the state of all actors in the game.
     */
    public void updateActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(enemy -> {
            enemy.updateActor();
            ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
            if (projectile != null) {
                addEnemyProjectile(projectile);
            }
        });
        userProjectiles.forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all destroyed actors from the game.
     */
    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedBullets(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from the specified list.
     *
     * @param actors the list of actors to check for destruction
     */
    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Removes destroyed or out-of-bounds bullets from the specified list.
     *
     * @param actors the list of bullets to check for destruction or out-of-bounds
     */
    private void removeDestroyedBullets(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(actor -> actor.isDestroyed() || isOutOfBounds(actor))
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Checks if an actor is out of bounds.
     *
     * @param actor the actor to check
     * @return true if the actor is out of bounds, false otherwise
     */
    private boolean isOutOfBounds(ActiveActorDestructible actor) {
        double x = actor.getBoundsInParent().getMinX();
        double y = actor.getBoundsInParent().getMinY();
        return x < 0 || x > screenWidth - actor.getBoundsInParent().getWidth() || y < 0
                || y > screenHeight - actor.getBoundsInParent().getHeight();
    }

    /**
     * Gets the list of friendly units.
     *
     * @return the list of friendly units
     */
    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    /**
     * Gets the list of enemy units.
     *
     * @return the list of enemy units
     */
    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    /**
     * Gets the list of user projectiles.
     *
     * @return the list of user projectiles
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /**
     * Gets the list of enemy projectiles.
     *
     * @return the list of enemy projectiles
     */
    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    /**
     * Gets the screen width.
     *
     * @return the screen width
     */
    public double getScreenWidth() {
        return screenWidth;
    }

    /**
     * Gets the screen height.
     *
     * @return the screen height
     */
    public double getScreenHeight() {
        return screenHeight;
    }
}