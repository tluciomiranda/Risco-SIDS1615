package interfaces;
import logic.*;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import logic.GameState.Phase;

public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private boolean mouseIsActive;
	
	private MapPanel map;
	private BottomPanel bottom;
	private GameState gs;


	
	public MainFrame() throws InterruptedException{
		
		generateGameComponents();
		createAndShowGui();
		
		//setHumanInterface();
		
		
		gs.deliverTerritories();
		//gs.deliverArmies();
		
		map.repaint();

		
		
		
	}
	
	/*
	public void setHumanInterface(){
		for(int i = 0; i < gs.getPlayers().size();++i){
			if(gs.getPlayers().get(i).getPlayerType() == Player.PlayerType.HUMAN){
				gs.getPlayers().get(i).setMap(map);
			}
			
		}
	}*/
	
	
	
	
	public void generateGameComponents(){
		this.gs = new GameState(6);
	}
	
	
	public void createAndShowGui(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        this.map = new MapPanel(gs);
        map.setPreferredSize(new Dimension(1400,750));
		this.add(map, BorderLayout.CENTER);
		
		
		
		/*
		this. bottom = new BottomPanel(gs);
        bottom.setPreferredSize(new Dimension(200, 200));
        this.add(bottom, BorderLayout.SOUTH);
        */
        this.pack();
        
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	
	
	
	//game rules stuff
	public void distributeCountries(){
		
		
	}
	
	public void deploy(){
		
	}
	
	public void attack(){
		
		
	}
	
	
	public boolean playerGameOver(){
		
		return false;
	}
	
	public boolean globalGameOver(){
		
		return false;
	}
	
	
	
	//main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
