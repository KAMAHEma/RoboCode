package Selfmade_Robots;
import robocode.*;


public class RobotJulian extends AdvancedRobot {
	
	public void run() {
		turnGunLeft(360);
		turnGunRight(360);
		while(true) {
			ahead(100);
			back(100);
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		
	}
}