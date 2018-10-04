import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cavalry extends Fighter{
	CollisionManager collisionManager;
	boolean charging;
	Point lastStopPoint;
	int chargingSpeed;

	public Cavalry(int newRadius, int newx, int newy, int team) {
		description = "A Knight on a horse.";
		type = "cavalry";
		health = 60;
		speed = 3;
		chargingSpeed = 5;
		radius = newRadius;
		x = newx;
		y = newy;
		this.team = team;
		location = new Point(x,y);
		collisionManager = new CollisionManager();
		charging = false;
		lastStopPoint = new Point(x,y);
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
		if (health > 0) {
			if (target.health > 0) {
				if (charging == true) {
					System.out.println("Cavalry charging target");
					target.takeDamage(40);
					charging = false;
				} else {
					target.takeDamage(10);
					System.out.println("Cavalry attacking target");
				}
			}
		}
	}
	
	public void takeDamage(int damage) {
		health = health-damage;
		System.out.println("Cavalry took damage");
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
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
		
		if (health > 0) {
			// check to see if we should be charging
			if (charging == false) {
				// if distance between our last collision point and our current location is far enough, then we are charging
				if(findDistanceBetweenPoints(lastStopPoint, location) > 100) {
					charging = true;
					System.out.println("Cavalry charging");
				}
			}
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
			   
			} else if(collisionManager.findCollision(myself, target) == true) {
				attack(target);
				lastStopPoint = new Point(x,y);
			} else {
				
				if (target.health > 0) {
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
					if(charging == true) {
						dx = (chargingSpeed / dist) * diffx;
						dy = (chargingSpeed / dist) * diffy;
					} else {
						dx = (speed / dist) * diffx;
						dy = (speed / dist) * diffy;
					}
					
					
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
				
			}
		}
		
		location = new Point(x,y);
		
	}
	
	/** Draw itself using the given graphics context. */
	public void draw(Graphics g) {
		if (health > 0) {
			if (charging == true) {
				g.setColor(Color.orange);
			} else {
				g.setColor(Color.red);
			}
			g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
			if (team == 1) {
				g.setColor(Color.magenta);
			} else if (team == 2) {
				g.setColor(Color.white);
			}
			
			g.fillArc((x-radius), (y-radius), (2*radius), (2*radius), 0, 90);
		}
	}
}