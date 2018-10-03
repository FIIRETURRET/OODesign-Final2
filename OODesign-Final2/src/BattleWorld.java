import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
public class BattleWorld extends JPanel {
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   int numWarriors = 20;
   int numArchers = 20;
   int numCavalry = 10;
   Fighter[] listOfWarriors;
   Fighter[] listOfArchers;
   Fighter[] listOfCavalry;
   private General general;
   
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BattleWorld(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      // Init the ball at a random location (inside the box) and moveAngle
      Random rand = new Random();
      int radius = 10;
      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed = 5;
      int angleInDegree = rand.nextInt(360);
      
      listOfWarriors = makeWarriors(numWarriors, 1);
      listOfArchers = makeArchers(numArchers, 1);
      listOfCavalry = makeCavalry(numCavalry, 1);
      general = new General(listOfWarriors, listOfArchers, listOfCavalry);
     
      // Init the Container Box to fill the screen
      box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            box.set(0, 0, canvasWidth, canvasHeight);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
	  for (int x = 0; x < numWarriors; x++) {
		  listOfWarriors[x].update(general.findClosestFighter(listOfWarriors[x]), listOfWarriors[x], box);
	  }
	  for (int x = 0; x < numArchers; x++) {
		  listOfArchers[x].update(general.findClosestFighter(listOfArchers[x]), listOfArchers[x], box);
	  }
	  for (int x = 0; x < numCavalry; x++) {
		  listOfCavalry[x].update(general.findClosestFighter(listOfCavalry[x]), listOfCavalry[x], box);
	  }
      
   }
   
   public Fighter[] makeWarriors(int num, int team) {
	   Fighter[] listOfFighters = new Fighter[num];
	   int radius = 10;
	   Random rand = new Random();
		
	   for (int q = 0; q <= num-1; q++) {
		   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
		   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
		   listOfFighters[q] = new Warrior(radius, x, y, team);;
		}
		
	   return listOfFighters;   
   }
   
   public Fighter[] makeArchers(int num, int team) {
	   Fighter[] listOfFighters = new Fighter[num];
	   int radius = 10;
	   Random rand = new Random();
		
	   for (int q = 0; q <= num-1; q++) {
		   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
		   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
		   listOfFighters[q] = new Archer(radius, x, y, team);;
		}
		
	   return listOfFighters;   
   }
   
   public Fighter[] makeCavalry(int num, int team) {
	   Fighter[] listOfFighters = new Fighter[num];
	   int radius = 10;
	   Random rand = new Random();
		
	   for (int q = 0; q <= num-1; q++) {
		   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
		   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
		   listOfFighters[q] = new Cavalry(radius, x, y, team);;
		}
		
	   return listOfFighters;   
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         box.draw(g);
         for (int x = 0; x < numWarriors; x++) {
        	 listOfWarriors[x].draw(g);
         }
         for (int x = 0; x < numArchers; x++) {
        	 listOfArchers[x].draw(g);
        	 listOfArchers[x].drawAttack(g);
         }
         for (int x = 0; x < numCavalry; x++) {
        	 listOfCavalry[x].draw(g);
         }
         
         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}