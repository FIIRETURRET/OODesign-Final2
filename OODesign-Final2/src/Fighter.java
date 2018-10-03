import java.awt.Graphics;
import java.awt.Point;

public abstract class Fighter {
	
	int health;
	String description = "Unkown fighter";
	String type;
	int speed;
	int radius;
	int x;
	int y;
	int team;
	Point location = new Point(x,y);
	Fighter target = null;
	Point targetPoint = new Point(1000,1000);
	
	
	public String getDescription() {
		return description;
	}
	
	public void setLocation(int x, int y) {
		location = new Point(x,y);
	}
	
	public Point getPoint() {
		return location;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setTarget(Fighter newTarget) {
		target = newTarget;
	}
	public Fighter getTarget() {
		return target;
	}
	
	public void setTargetPoint(Point newPoint) {
		targetPoint = newPoint;
	}
	public Point getTargetPoint() {
		return targetPoint;
	}
	
	public abstract void targetFighter();
	public abstract void move();
	public abstract void attack(Fighter fighter);
	public abstract void update(Fighter fighter, Fighter myself, ContainerBox box);
	public abstract void draw(Graphics g);
	public abstract void takeDamage(int damage);

	public void drawAttack(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
