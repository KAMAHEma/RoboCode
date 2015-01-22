package Selfmade_Robots.danielsrobot;

import java.awt.Color;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;

public class RobotDaniel extends AdvancedRobot {
	private boolean isMovingForward;
	private double turnDegree = 450;

	private Gegner gegner = new Gegner();

	public void run() {
		initProperties();

		while (true) {
			setAhead(getRandomDistance());
			pushMovement();
		}
	}

	private void initProperties() {
		setBodyColor(Color.BLACK);
		setGunColor(Color.RED);
		setRadarColor(Color.CYAN);
		setBulletColor(Color.ORANGE);
		setScanColor(Color.RED);

		setAdjustGunForRobotTurn(true);
		gegner.reset();

		execute();
	}

	private void pushMovement() {
		isMovingForward = true;

		setTurnRight(90);
		turnGunRight(10000);
		waitFor(new TurnCompleteCondition(this));
//		turnGunRight(turnDegree);
		setTurnLeft(180);
		waitFor(new TurnCompleteCondition(this));
//		turnGunRight(turnDegree);
		setTurnRight(180);
		waitFor(new TurnCompleteCondition(this));
		execute();
	}

	public int getRandomDistance() {
		Random rand = new Random();
		int n = rand.nextInt(40000) + 25000;
		return n;
	}

	public void onHitWall(HitWallEvent e) {
		fireTurnAround();
	}

	public void fireTurnAround() {
		if (isMovingForward) {
			setBack(getRandomDistance());
			isMovingForward = false;
		} else {
			setAhead(getRandomDistance());
			isMovingForward = true;
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		if (!gegner.isEnemyKnown() || e.getName().equals(gegner.getName())) {
			gegner.update(e);
		}

		int power4distance = renderFireDistance(e.getDistance());
		setTurnGunRight(getHeading() - getRadarHeading() + e.getBearing());
		if (power4distance != -1) {
//			long shotTravelTime = (long) (e.getDistance() / (20 - power4distance * 3));
//			setTurnGunRight(0);
			fire(power4distance);
//			setTurnGunRight(turnDegree);
		}
	}

	public int renderFireDistance(double robotDistance) {
		int power4distance = -1;
		if (robotDistance > 200 || getEnergy() < 15) {
			power4distance = 1;
		} else if (robotDistance > 50) {
			power4distance = 2;
		} else {
			power4distance = 3;
		}
		return power4distance;
	}

	public void onHitRobot(HitRobotEvent e) {
		if (e.isMyFault()) {
			fireTurnAround();
		}
	}

	public void onRobotDeath(RobotDeathEvent e) {
		if (e.getName().equals(gegner.getName())) {
			gegner.reset();
		}
	}
}