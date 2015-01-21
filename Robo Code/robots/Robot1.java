package Selfmade;
import robocode.*;
import robocode.Robot;


public class Robot1 extends Robot {
	
	public void run() {
		turnGunLeft(360);
		turnGunRight(360);
		while(true) {
			
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(1);
	}
}
