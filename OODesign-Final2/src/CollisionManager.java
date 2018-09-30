import java.awt.Point;

public class CollisionManager {
	Fighter fighter;
	Fighter target;
	boolean collision;

	public CollisionManager(){
		this.collision = false;
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	
	public boolean findCollision(Fighter fighter, Fighter target) {
		int sumOfRadius = fighter.radius + target.radius;
		double distanceBetweenPoints = findDistanceBetweenPoints(fighter.location, target.location);
		if (distanceBetweenPoints < sumOfRadius) {
			collision = true;
		}
		else {
			collision = false;
		}
		
		return collision;
	}
}
