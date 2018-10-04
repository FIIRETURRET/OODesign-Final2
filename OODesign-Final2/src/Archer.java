import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;

public class Archer extends Fighter{
	
	private int randomNum;
	private boolean attacking;
	private long lastShotTime;

	public Archer(int newRadius, int newx, int newy, int team) {
		description = "An Archer";
		type = "archer";
		health = 25;
		speed = 1;
		radius = newRadius;
		x = newx;
		y = newy;
		this.team = team;
		location = new Point(x,y);
		lastShotTime = 0;
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

	@Override
	public void attack(Fighter fighter) {
		// TODO Auto-generated method stub
		// Draw a line from archer to target
		// buffer the archers attack based on time
		long elapsedTime = System.currentTimeMillis() - lastShotTime;
		// If the time since the archer last took a shot is 1 second then attack
		if (elapsedTime/1000 >=1 )
		{
			target.takeDamage(15);
			System.out.println("Archer attacking target");
			attacking = true;
			lastShotTime = System.currentTimeMillis();
		} else {
			attacking = false;
		}
		
	}
	
	public void takeDamage(int damage) {
		health = health - damage;
		System.out.println("Archer took damage");
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	
	public void update(Fighter target, Fighter myself, ContainerBox box) {
		// Update current target information
		this.target = target;
		// If we don't have a target don't update
		if (target == null) {
			attacking = false;
			return;
		}
		this.targetPoint = target.getPoint();
		
		location = new Point(x,y);
		targetPoint = target.getPoint();
		// Get the ball's bounds, offset by the radius of the ball
		int ballMinX = box.minX + radius;
		int ballMinY = box.minY + radius;
		int ballMaxX = box.maxX - radius;
		int ballMaxY = box.maxY - radius;
		
		if (health > 0) {
		
			if (target.health > 0) {
				
				// If the target is outside our range
				if (findDistanceBetweenPoints(target.location, location) >= 200) {
					attacking = false;
					// if the target is another archer
					if (target.type == "archer") {
						//Movement away from target
						// Check if the ball moves over the bounds. If so, adjust the position and speed.
						if (x < ballMinX) {
						   x = ballMinX;     // Re-position the ball at the edge
						   
						} else if (x > ballMaxX) {
						   x = ballMaxX;
						}
						// If the ball is to the left of the target, move right
						else if(x < targetPoint.x) {
							x += speed;
						}
						// If the ball is to the right of the target, move left
						else if(x > targetPoint.x) {
							x -= speed;
						}
						// If the ball is on the same spot as the target, move a random direction
						else if(x == targetPoint.x) {
							randomNum = ThreadLocalRandom.current().nextInt(0,2);
							if (randomNum == 0) {
								x += speed;
							}
							else {
								x -= speed;
							}
						}
						else if(x == targetPoint.x && y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,4);
							if (randomNum == 0) {
								x += speed+1*2;
								y += speed+1*2;
							}
							else if(randomNum == 1){
								x -= speed+1*2;
								y -= speed+1*2;
							}
							else if (randomNum == 2) {
								x += speed+1*2;
								y -= speed+1*2;
							}
							else {
								x -= speed+1*2;
								y += speed+1*2;
							}
						}
						// May cross both x and y bounds
						if (y < ballMinY) {
							
						   y = ballMinY;
						} else if (y > ballMaxY) {
						   
						   y = ballMaxY;
						}
						// If the ball is under the target, move up
						else if(y < targetPoint.y) {
							y += speed;
						}
						// if the ball is above the target, move down
						else if(y > targetPoint.y) {
							y -= speed;
						}
						else if(y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,2);
							if (randomNum == 0) {
								y += speed;
							}
							else {
								y -= speed;
							}
						}
						else if(x == targetPoint.x && y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,4);
							if (randomNum == 0) {
								x += speed+1*2;
								y += speed+1*2;
							}
							else if(randomNum == 1){
								x -= speed+1*2;
								y -= speed+1*2;
							}
							else if (randomNum == 2) {
								x += speed+1*2;
								y -= speed+1*2;
							}
							else {
								x -= speed+1*2;
								y += speed+1*2;
							}
						}
					}
				} else {
					// if the target is not another archer, run away from it
					if(findDistanceBetweenPoints(location, target.location) < 100) {
						attacking = false;
						//Movement away from target
						// Check if the ball moves over the bounds. If so, adjust the position and speed.
						if (x < ballMinX) {
						   x = ballMinX;     // Re-position the ball at the edge
						   
						} else if (x > ballMaxX) {
						   x = ballMaxX;
						}
						// If the ball is to the left of the target, move left
						else if(x < targetPoint.x) {
							x -= speed;
						}
						// If the ball is to the right of the target, move right
						else if(x > targetPoint.x) {
							x += speed;
						}
						// If the ball is on the same spot as the target, move a random direction
						else if(x == targetPoint.x) {
							randomNum = ThreadLocalRandom.current().nextInt(0,2);
							if (randomNum == 0) {
								x += speed;
							}
							else {
								x -= speed;
							}
						}
						else if(x == targetPoint.x && y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,4);
							if (randomNum == 0) {
								x += speed+1*2;
								y += speed+1*2;
							}
							else if(randomNum == 1){
								x -= speed+1*2;
								y -= speed+1*2;
							}
							else if (randomNum == 2) {
								x += speed+1*2;
								y -= speed+1*2;
							}
							else {
								x -= speed+1*2;
								y += speed+1*2;
							}
						}
						// May cross both x and y bounds
						if (y < ballMinY) {
							
						   y = ballMinY;
						} else if (y > ballMaxY) {
						   
						   y = ballMaxY;
						}
						// If the ball is under the target, move down
						else if(y < targetPoint.y) {
							y -= speed;
						}
						// if the ball is above the target, move up
						else if(y > targetPoint.y) {
							y += speed;
						}
						else if(y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,2);
							if (randomNum == 0) {
								y += speed;
							}
							else {
								y -= speed;
							}
						}
						else if(x == targetPoint.x && y == targetPoint.y) {
							randomNum = ThreadLocalRandom.current().nextInt(0,4);
							if (randomNum == 0) {
								x += speed+1*2;
								y += speed+1*2;
							}
							else if(randomNum == 1){
								x -= speed+1*2;
								y -= speed+1*2;
							}
							else if (randomNum == 2) {
								x += speed+1*2;
								y -= speed+1*2;
							}
							else {
								x -= speed+1*2;
								y += speed+1*2;
							}
						}
					} else {
						// The range in which an archer can shoot a target.
						if (findDistanceBetweenPoints(location,target.location) < 200) {
							attack(target);
						} else {
							attacking = false;
						}
					}
				}
			} else {
				attacking = false;
			}
		}
		
		location = new Point(x,y);
		
	}
	
	public Point getPoint() {
		return location;
	}
	
	/** Draw itself using the given graphics context. */
	public void draw(Graphics g) {
		if (health > 0) {
			g.setColor(Color.green);
			g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
			if (team == 1) {
				g.setColor(Color.magenta);
			} else if (team == 2) {
				g.setColor(Color.white);
			}
			
			g.fillArc((x-radius), (y-radius), (2*radius), (2*radius), 0, 90);
		}
	}
	
	public void drawAttack(Graphics g) {
		if (health > 0) {
			if (attacking == true) {
				g.drawLine(x, y, target.location.x, target.location.y);
			}	
		}
	}

	
}
