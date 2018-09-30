import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Warrior extends Fighter{
	CollisionManager collisionManager;
	
	public Warrior(int newRadius, int newx, int newy) {
		description = "A Warrior";
		type = "warrior";
		health = 50;
		speed = 3;
		radius = newRadius;
		x = newx;
		y = newy;
		location = new Point(x,y);
		localSearchSpace = 50;
		collisionManager = new CollisionManager();
	}
	
	// Find the closest Archer
	@Override
	public void targetFighter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	public void attack(Fighter target) {
		// TODO Auto-generated method stub
		target.takeDamage(10);
		System.out.println("Warrior attack");
	}
	
	public void takeDamage(int damage) {
		
	}
	
	public void update(Fighter target, Fighter myself, ContainerBox box) {
		
		// Update current target information
		this.target = target;
		// If we don't have a target don't update
		if (target == null) {
			return;
		}
		this.targetPoint = target.getPoint();
		// Get the ball's bounds, offset by the radius of the ball
		int ballMinX = box.minX + radius;
		int ballMinY = box.minY + radius;
		int ballMaxX = box.maxX - radius;
		int ballMaxY = box.maxY - radius;
		
		// Movement
		location = new Point(x,y);
		targetPoint = target.getPoint();
		// Check if the ball moves over the bounds. If so, adjust the position and speed.
		if (x < ballMinX) {
		   x = ballMinX;     // Re-position the ball at the edge
		   
		} else if (x > ballMaxX) {
		   x = ballMaxX;	// Re-position the ball at the edge
		   
		} else if (y < ballMinY) {
		   y = ballMinY;
		   
		} else if (y > ballMaxY) {
		   y = ballMaxY;
		   
		} else if (collisionManager.findCollision(myself, target) == true){
			System.out.println("Collision Detected");
			
		} else {
			
			// The x and y offsets to move the ball
			double dx;
			double dy;
			
			// The values for the target's x and y
			int targetx;
			int targety;
			
			// Set the target to the current target's position
			targetx = target.x;
			targety = target.y;
			
			// Find the x and y differences between the ball and the target
			int diffx = targetx - x;
			int diffy = targety - y;
			
			double diffxSquare = Math.pow(diffx, 2);
			double diffySquare = Math.pow(diffy, 2);
			// Calculate the distance between the ball and the target
			double dist = Math.sqrt(diffxSquare + diffySquare);
			
			// Calculate the x and y offsets to move the ball
			dx = (speed / dist) * diffx;
			dy = (speed / dist) * diffy;
			
			// If the ball moves past the target, keep it at the target
			if (Math.abs(diffx) < Math.abs(dx)) {
				x = targetx;
			}
			if (Math.abs(diffy) < Math.abs(dy)) {
				y = targety;
			}
			
			// Move the ball
			x += Math.round(dx);
			y += Math.round(dy);
			
		}
		
		location = new Point(x,y);
		
	}
	
	/** Draw itself using the given graphics context. */
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
	}

	
}
