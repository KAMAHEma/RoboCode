package Selfmade_Robots;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

public class RobotJulian extends AdvancedRobot {
	boolean movingForward; // Is set to true when setAhead is called, set to false on setBack
	boolean inWall; // Is true when robot is near the wall.
	int dist = 50; // distance to move when we're hit

	/**
	 * run:  Fire's main run function
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
		
		while(true) {
			turnGunRight(10);
			turnRadarRight(10);			
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
		double bearingFromRadar = normalRelativeAngleDegrees(absoluteBearing - getRadarHeading());
 
		if (Math.abs(bearingFromGun) <= 4) {
			setTurnGunRight(bearingFromGun); 
			setTurnRadarRight(bearingFromRadar);
			
			if (getGunHeat() == 0 && getEnergy() > .2) {
				fire(Math.min(4.5 - Math.abs(bearingFromGun) / 2 - e.getDistance() / 250, getEnergy() - .1));
			} 
		}
		else {
			setTurnGunRight(bearingFromGun);
			setTurnRadarRight(bearingFromRadar);
		}
		if (bearingFromGun == 0) {
			scan();
		}
		execute();
	}
	
	public void onHitWall(HitWallEvent e) {
	}
	
	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
	}
}
