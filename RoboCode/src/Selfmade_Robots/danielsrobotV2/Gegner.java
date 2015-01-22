package Selfmade_Robots.danielsrobotV2;
import robocode.ScannedRobotEvent;

public class Gegner {

	/**
	 * GegernObject welches representativ für den gescannten Gegner steht
	 */
	public Gegner() {
		// noch machen wir hir nichts, das knn sich aber ändern
		reset();
	}

	private double bearing;
	private double distance;
	private double energy;
	private double heading;
	private double velocity;
	private String name;

	public double getBearing() {
		return bearing;
	}
	public double getDistance() {
		return distance;
	}
	public double getEnergy() {
		return energy;
	}
	public double getHeading() {
		return heading;
	}
	public double getVelocity() {
		return velocity;
	}
	public String getName() {
		return name;
	}

	/**
	 * aktualisiert den gescannten Gegner mit den vorhanden Daten
	 * @param event
	 */
	public void update(ScannedRobotEvent event) {
		bearing = event.getBearing();
		distance = event.getDistance();
		heading = event.getHeading();
		energy = event.getEnergy();
		velocity = event.getVelocity();
		name = event.getName();
	}

	/**
	 * wir clearen alle GegnerDaten für später Zwecke
	 */
	public void reset() {
		bearing = 0.0;
		distance = 0.0;
		heading = 0.0;
		energy = 0.0;
		velocity = 0.0;
		name = "";
	}

	public boolean isEnemyKnown() {
		return !"".equals(name);
	}
}