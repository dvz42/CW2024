package com.example.demo.levels;

import java.util.Observer;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.actors.UserPlane;
import com.example.demo.managers.ActorManager;
import com.example.demo.managers.BackgroundManager;
import com.example.demo.managers.CollisionManager;
import com.example.demo.managers.GameStateManager;
import com.example.demo.managers.GameTimelineManager;
import com.example.demo.utils.GameState;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class LevelParent {

    private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
    private final Scene scene;
    private final UserPlane user;
    private final GameTimelineManager gameTimeline;
    private final BackgroundManager backgroundManager;
    private final ActorManager actorManager;
    private final CollisionManager collisionManager;
    private final GameStateManager gameStateManager;
    private final LevelView levelView;

    public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        this.root = new Group();
        this.scene = new Scene(root, screenWidth, screenHeight);
        this.user = new UserPlane(playerInitialHealth);
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
        this.levelView = instantiateLevelView();
        this.actorManager = new ActorManager(root, screenWidth, screenHeight, user);
        this.collisionManager = new CollisionManager(actorManager, user);
        this.gameStateManager = new GameStateManager(GameState.LEVEL_ONE);
        this.backgroundManager = new BackgroundManager(backgroundImageName, screenHeight, screenWidth, root);
        this.gameTimeline = new GameTimelineManager(this::updateScene);
        initializeKeyHandlers();
    }

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits();

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeFriendlyUnits();
        levelView.showHeartDisplay();
        return scene;
    }

    public void startGame() {
        backgroundManager.requestFocus();
        gameTimeline.start();
    }

    public void stopGame() {
        gameTimeline.stop();
    }

    private void updateScene() {
        try {
            spawnEnemyUnits();
            actorManager.updateActors();
            collisionManager.handleCollisions();
            actorManager.removeAllDestroyedActors();
            updateLevelView();
            checkIfGameOver();
            updateActors();
        } catch (Exception e) {
            e.printStackTrace();
            stopGame();
            showErrorAlert(e);
        }
    }

    protected void updateActors() {
        // To be implemented by subclasses
    }

    private void updateLevelView() {
        levelView.removeHearts(user.getHealth());
    }

    protected void winGame() {
        gameTimeline.stop();
        levelView.showWinImage();
    }

    protected void loseGame() {
        gameTimeline.stop();
        levelView.showGameOverImage();
    }

    protected UserPlane getUser() {
        return user;
    }

    protected Group getRoot() {
        return root;
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        actorManager.addEnemyUnit(enemy);
    }

    protected int getCurrentNumberOfEnemies() {
        return actorManager.getEnemyUnits().size();
    }

    protected void goToNextLevel(GameState gameState) {
        gameStateManager.goToNextLevel(gameState);
    }

    private void showErrorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(e.getClass().toString() + ": " + e.getMessage());
        alert.show();
    }

    public void addObserver(Observer observer) {
        gameStateManager.addObserver(observer);
    }

    private void initializeKeyHandlers() {
        scene.setOnKeyPressed(e -> handleKeyPressed(e));
        scene.setOnKeyReleased(e -> handleKeyReleased(e));
    }

    private void handleKeyPressed(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.W)
            user.moveUp();
        if (kc == KeyCode.DOWN || kc == KeyCode.S)
            user.moveDown();
        if (kc == KeyCode.LEFT || kc == KeyCode.A)
            user.moveLeft();
        if (kc == KeyCode.RIGHT || kc == KeyCode.D)
            user.moveRight();
        if (kc == KeyCode.SPACE) {
            ActiveActorDestructible projectile = user.fireProjectile();
            actorManager.addUserProjectile(projectile);
        }
    }

    private void handleKeyReleased(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.UP || kc == KeyCode.DOWN || kc == KeyCode.W || kc == KeyCode.S)
            user.stopVertical();
        else if (kc == KeyCode.LEFT || kc == KeyCode.RIGHT || kc == KeyCode.A || kc == KeyCode.D)
            user.stopHorizontal();
    }
}