import java.awt.Point;

public class General {
	
	Fighter[] warriorList;
	Fighter[] archerList;
	Fighter[] cavalryList;
	
	public General(Fighter[] newWarriorList, Fighter[] newArcherList, Fighter[] newCavalryList) {
		warriorList = newWarriorList;
		archerList = newArcherList;
		cavalryList = newCavalryList;
		
	}
	
	public Fighter findClosestFighter(Fighter fighter) {
		Point enemyPoint;
		Fighter closestEnemy;
		// Get position of current fighter
		Point fighterPos = fighter.getPoint();
		if (fighter.getTarget() == null) {
			// Set the first point rediculously far away
			enemyPoint = new Point(10000,10000);
			closestEnemy = null;
		} else {
			enemyPoint = fighter.getTargetPoint();
			closestEnemy = fighter.getTarget();
		}
		
		// Check to see if we are dealing with a warrior or an archer
		if(fighter.type == "warrior") {
			// Run through the list of archers so see which is closest
			for (int x = 0; x < archerList.length; x++) {
				// Find the closest enemy by running though a list of all archers in the field
				if (findDistanceBetweenPoints(fighter.getPoint(), archerList[x].getPoint()) < findDistanceBetweenPoints(fighter.getPoint(), fighter.getTargetPoint())) {
					// if our new enemy is closer than our old enemy update the saved point and closestEnemy
					enemyPoint = archerList[x].getPoint();
					closestEnemy = archerList[x];
					fighter.setTarget(closestEnemy);
					fighter.setTargetPoint(closestEnemy.getPoint());
				}
				// Otherwise keep the current target
				else {
					closestEnemy = fighter.getTarget();
					enemyPoint = fighter.getTargetPoint();
					fighter.setTarget(closestEnemy);
					fighter.setTargetPoint(closestEnemy.getPoint());
				}
			}
		}
		else if (fighter.type == "archer") {
			// Run through the list of archers so see which is closest
			for (int x = 0; x < warriorList.length; x++) {
				// Find the closest enemy by running though a list of all fighters in the field
				if (findDistanceBetweenPoints(fighter.getPoint(), warriorList[x].getPoint()) < findDistanceBetweenPoints(fighter.getPoint(), fighter.getTargetPoint())) {
					// if our new enemy is closer than our old enemy update the saved point and closestEnemy
					enemyPoint = warriorList[x].getPoint();
					closestEnemy = warriorList[x];
				}
				// Otherwise keep the current target
				else {
					closestEnemy = fighter.getTarget();
					enemyPoint = fighter.getTargetPoint();
				}
			}
		}
		else if(fighter.type == "cavalry") {
			// Run through the list of archers so see which is closest
			for (int x = 0; x < warriorList.length; x++) {
				// Find the closest enemy by running though a list of all fighters in the field
				if (findDistanceBetweenPoints(fighter.getPoint(), warriorList[x].getPoint()) < findDistanceBetweenPoints(fighter.getPoint(), fighter.getTargetPoint())) {
					// if our new enemy is closer than our old enemy update the saved point and closestEnemy
					enemyPoint = warriorList[x].getPoint();
					closestEnemy = warriorList[x];
				}
				// Otherwise keep the current target
				else {
					closestEnemy = fighter.getTarget();
					enemyPoint = fighter.getTargetPoint();
				}
			}
		}
		else {
			
		}
		return closestEnemy;
	}
	
	public static Point subtract(Point p1, Point p2) {
		return new Point(p1.x - p2.x, p1.y - p2.y);
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p2.x) * (p2.x - p2.x));
	}
	
	public void update() {
		
	}

}