import java.awt.Point;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**Fighter war1 = new Warrior();
		war1.setLocation(1,1);
		System.out.println(war1.getDescription());
		System.out.println(war1.getPoint());
		
		Fighter archer1 = new Archer();
		archer1.setLocation(10,10);
		System.out.println(archer1.getDescription());
		System.out.println(archer1.getPoint());**/
		
		// Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	    		JFrame frame = new JFrame("A World of Balls");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setContentPane(new BattleWorld(640, 480)); // BallWorld is a JPanel
	            frame.pack();            // Preferred size of BallWorld
	            frame.setVisible(true);  // Show it
	         }
	    });
	}

}
