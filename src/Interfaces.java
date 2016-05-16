import java.awt.*;
import javax.swing.*;

public class Interfaces
{
	private static final int BUTTON_HEIGHT = 75;
	private static final int BUTTON_WIDTH = 400;
	private static final int TEXTFIELD_HEIGHT = 400;
	private static final int TEXTFIELD_WIDTH = 50;
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 720;
	
	// Login objects
	
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
	    {
	        public void run() {
	        	createAndShowLoginUI();
	        }
	    });
	}

	private static void createAndShowLoginUI() 
	{
	    //Create and set up the window.
	    JFrame frame = new JFrame("Risk");
	    JPanel panel1 = new JPanel(new GridLayout(3,2));
		JLabel welcomeLabel = new JLabel("Welcome to Risk Game.");
		JLabel pleaseLoginLabel = new JLabel("Please login below, or create an account:");
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	    
	    panel1.add(welcomeLabel, BorderLayout.CENTER);
	    panel1.add(pleaseLoginLabel, BorderLayout.CENTER);
	    panel1.setSize(400, 400);
	    frame.getContentPane().add(panel1, BorderLayout.CENTER);
	    
	    //Display the window.
	    frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	    frame.setVisible(true);
	}
}