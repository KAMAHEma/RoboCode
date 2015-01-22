package Selfmade_Robots;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;

public class RobotJulian extends AdvancedRobot {
	boolean movingForward; // Is set to true when setAhead is called, set to false on setBack
	int speed;
	boolean inWall; // Is true when robot is near the wall.
	int i = 0;
	int dist = 50; // distance to move when we're hit	

	/**
	 * run: j Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.blue);
		setScanColor(Color.green);
		setBulletColor(Color.red);
		
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
		movingForward = true;
		
		while(true) {
			turnGunRight(30);
			turnRadarRight(30);
		}
	}

	public void move(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}
	
	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
		double bearingFromRadar = normalRelativeAngleDegrees(absoluteBearing - getRadarHeading());
		double firePower = 0;
		double distance = e.getDistance();
		
		if (Math.abs(bearingFromGun) <= 4) {
			setTurnGunRight(bearingFromGun); 
			setTurnRadarRight(bearingFromRadar);
			
			if (getGunHeat() == 0 && getEnergy() > .2) {
				firePower = Math.min(4.5 - Math.abs(bearingFromGun) / 2 - e.getDistance() / 250, getEnergy() - .1);
				fire(firePower);
			} 
		}
		else {
			setTurnGunRight(bearingFromGun);
			setTurnRadarRight(bearingFromRadar);
		}
		if (bearingFromGun == 0) {
			scan();
		}
		speed = ((int) distance) / ((int) ((20 - firePower)*3));
		
		execute();
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		move(e);
	}

}
