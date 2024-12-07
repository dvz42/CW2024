package com.example.demo.actors;

import com.example.demo.projectiles.EnemyProjectile;

public class EnemyPlane extends FighterPlane {

	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private double fireRate = .02;

	public EnemyPlane(double initialXPos, double initialYPos, String imageName, double fireRate) {
		super(imageName, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		this.fireRate = fireRate;
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < fireRate) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}
