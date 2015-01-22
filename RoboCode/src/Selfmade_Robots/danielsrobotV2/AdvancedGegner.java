package Selfmade_Robots.danielsrobotV2;

import robocode.Robot;
import robocode.ScannedRobotEvent;

public class AdvancedGegner extends Gegner {

	public AdvancedGegner() {
		reset();
	}

	private double xCord;
	private double yCord;

	public double getxCord() {
		return xCord;
	}
	public double getyCord() {
		return yCord;
	}

	@Override
	public void reset() {
		xCord = 0;
		yCord = 0;
		super.reset();
	}

	public void update(ScannedRobotEvent event, Robot robot) {
		super.update(event);

		double absBearingDeg = (robot.getHeading() + event.getBearing());
		if (absBearingDeg < 0) {
			absBearingDeg += 360;
		}

		xCord = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) * event.getDistance();
		yCord = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) * event.getDistance();
	}

	public double getPredictedX(long bulletTravelTime) {
		return xCord + Math.sin(Math.toRadians(getHeading())) * getVelocity() * bulletTravelTime;
	}	

	public double getPredictedY(long bulletTravelTime) {
		return yCord + Math.sin(Math.toRadians(getHeading())) * getVelocity() * bulletTravelTime;
	}
}