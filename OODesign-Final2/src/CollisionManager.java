import java.awt.Point;

public class CollisionManager {
	Fighter fighter;
	Fighter target;
	boolean collision;

	public CollisionManager(Fighter fighter, Fighter target){
		this.fighter = fighter;
		this.target = target;
		this.collision = false;
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p2.x) * (p2.x - p2.x));
	}
	
	public boolean findCollision(Fighter fighter, Fighter target) {
		int sumOfRadius = fighter.radius + target.radius;
		double distanceBetweenPoints = findDistanceBetweenPoints(fighter.location, target.location);
		if (distanceBetweenPoints <= sumOfRadius) {
			collision = true;
		}
		else {
			collision = false;
		}
		
		return collision;
	}
}
