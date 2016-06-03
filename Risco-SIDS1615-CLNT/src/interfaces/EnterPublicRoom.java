package interfaces;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javafx.util.Pair;
import main.Main;
import javax.swing.JList;

@SuppressWarnings("serial")
public class EnterPublicRoom extends JFrame {

	private JPanel contentPane;

	private ChooseRoom chooseRoom;

	/**
	 * Create the frame.
	 */
	public EnterPublicRoom()
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(50, 172, 905, 450);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Available public rooms");
		label.setBounds(10, 5, 273, 32);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(label);
		
		// botao login
		JButton btnLogin = new JButton("Check");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<String[]> availableRooms = getAvailableRooms();
				
				for(int i = 0; i < availableRooms.size(); i++)
				{
					String id = availableRooms.get(i)[0];
					String actual = availableRooms.get(i)[1];
					String available = availableRooms.get(i)[2];
				}				
			}			
		});
		btnLogin.setBounds(806, 416, 89, 23);
		panel_1.add(btnLogin);
		
		JButton button = new JButton("Cancel");
		button.setBounds(10, 416, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				returnChooseRoom(e);
			}			
		});
		panel_1.add(button);
		
		JLabel lblRoomId = new JLabel("Room ID");
		lblRoomId.setBounds(20, 48, 46, 14);
		panel_1.add(lblRoomId);
		
		JLabel label_1 = new JLabel("Players");
		label_1.setBounds(646, 48, 46, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Maximum");
		label_2.setBounds(720, 48, 46, 14);
		panel_1.add(label_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 73, 885, 332);
		panel_1.add(panel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 998, 123);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 958, 37);
		panel_2.add(panel_3);
		
		JLabel lblWelcomeToRisk = new JLabel("Welcome to RISK");
		lblWelcomeToRisk.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblWelcomeToRisk);
		lblWelcomeToRisk.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 77, 425, 35);
		panel_2.add(panel_4);
		
		JLabel lblPleaseLoginBelow = new JLabel("Enter public room");
		panel_4.add(lblPleaseLoginBelow);
		lblPleaseLoginBelow.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseLoginBelow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(904, 677, 92, 34);
		panel.add(btnExit);
		
		JLabel background = new JLabel(new ImageIcon("risk.jpg"));
		background.setBounds(0, 50, 1024, 768);
		panel.add(background);
	}
	
	public void performEnter()
	{
		String result = null;
		int usr = Main.userID;
		
		try
		{
			result = Main.cl.httpReq("/game?action=join&user=" + usr + "&type=public&id="/* + roomid*/);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		//ajustar resultado
		if(result.equals("OK"))
		{
			this.dispose();
		}
	}
	
	public ArrayList<String[]> getAvailableRooms()
	{
		String result = null;
		int usr = Main.userID;
		
		try
		{
			result = Main.cl.httpReq("/game?action=getAvailable&user=" + usr);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		//ajustar resultado
		if(!result.equals("!OK"))
		{
			ArrayList<String[]> rs = new ArrayList<String[]>();
			
			// processa resultado no formato IDRoom:nrPlayers:MaxPlayers;IDRoom:nrPlayers:MaxPlayers
			
			String[] roomData = result.split(";");
			
			for(int i = 0; i < roomData.length; i++)
			{
				rs.add(roomData[i].split(":")); 
			}
			
			return rs;
		}
		else
		{
			//resposta feia
			
			return null;
		}
	}
	
	public void setChooseRoom(ChooseRoom cr)
	{
		this.chooseRoom = cr;
	}
	
	public void returnChooseRoom(ActionEvent e) 
	{
		chooseRoom.setVisible(true);
		this.dispose();
	}
}
