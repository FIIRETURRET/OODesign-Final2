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
	
	public Fighter findCLosestTypeFighter(Fighter fighter, Fighter closestEnemy, Point enemyPoint, Fighter[] fighterList) {
		for (int x = 0; x < fighterList.length; x++) {
			// Check to see if current target is on the other team
			if (fighterList[x].team != fighter.team){
				if (findDistanceBetweenPoints(fighter.getPoint(), fighterList[x].getPoint()) < findDistanceBetweenPoints(fighter.getPoint(), fighter.getTargetPoint())) {
					// if our new enemy is closer than our old enemy update the saved point and closestEnemy
					// if our archer in the list is dead, don't consider it
					if (fighterList[x].health > 0) {
						enemyPoint = fighterList[x].getPoint();
						closestEnemy = fighterList[x];
						fighter.setTarget(closestEnemy);
						fighter.setTargetPoint(closestEnemy.getPoint());
					} else {
						closestEnemy = fighter.getTarget();
						enemyPoint = fighter.getTargetPoint();
						fighter.setTarget(closestEnemy);
						fighter.setTargetPoint(closestEnemy.getPoint());
					}
				}
				// Otherwise keep the current target
				else {
					// If the current target is dead take the new one
					if (closestEnemy.health > 0)
					{
						closestEnemy = fighter.getTarget();
						enemyPoint = fighter.getTargetPoint();
						fighter.setTarget(closestEnemy);
						fighter.setTargetPoint(closestEnemy.getPoint());
					} else {
						enemyPoint = fighterList[x].getPoint();
						closestEnemy = fighterList[x];
						fighter.setTarget(closestEnemy);
						fighter.setTargetPoint(closestEnemy.getPoint());
					}
				}
			}
		}
		
		return closestEnemy;
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
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, cavalryList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, archerList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, warriorList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			
		} else if (fighter.type == "archer") {
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, cavalryList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, archerList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, warriorList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			
		} else if(fighter.type == "cavalry") {
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, cavalryList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, archerList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
			closestEnemy = findCLosestTypeFighter(fighter, closestEnemy, enemyPoint, warriorList );
			if (closestEnemy != null){
				enemyPoint = closestEnemy.location;
			}
		}
		return closestEnemy;
	}
	
	public static Point subtract(Point p1, Point p2) {
		return new Point(p1.x - p2.x, p1.y - p2.y);
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	
	public void update() {
		
	}

}
