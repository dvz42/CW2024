package com.example.demo.managers;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.EnemyPlane;
import com.example.demo.actors.FighterPlane;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActorManager {

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;
    private final Group root;
    private final double screenWidth;
    private final double screenHeight;

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

    public void addFriendlyUnit(ActiveActorDestructible unit) {
        friendlyUnits.add(unit);
        root.getChildren().add(unit);
    }

    public void addEnemyUnit(ActiveActorDestructible unit) {
        enemyUnits.add(unit);
        root.getChildren().add(unit);
    }

    public void addUserProjectile(ActiveActorDestructible projectile) {
        userProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        enemyProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

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

    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedBullets(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private void removeDestroyedBullets(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(actor -> actor.isDestroyed() || isOutOfBounds(actor))
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    private boolean isOutOfBounds(ActiveActorDestructible actor) {
        double x = actor.getBoundsInParent().getMinX();
        double y = actor.getBoundsInParent().getMinY();
        return x < 0 || x > screenWidth - actor.getBoundsInParent().getWidth() || y < 0
                || y > screenHeight - actor.getBoundsInParent().getHeight();
    }

    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public double getScreenWidth() {
        return screenWidth;
    }

    public double getScreenHeight() {
        return screenHeight;
    }
}