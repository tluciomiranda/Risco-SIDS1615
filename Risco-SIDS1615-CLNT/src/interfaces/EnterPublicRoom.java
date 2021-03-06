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
		
		JButton button = new JButton("Cancel");
		button.setBounds(0, 677, 94, 34);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				returnChooseRoom(e);
			}			
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(50, 172, 905, 450);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Available public rooms");
		label.setBounds(10, 5, 273, 32);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(label);
		
		JLabel lblRoomId = new JLabel("Room ID");
		lblRoomId.setBounds(20, 48, 46, 14);
		panel_1.add(lblRoomId);
		
		JLabel label_1 = new JLabel("Players");
		label_1.setBounds(646, 48, 64, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Maximum");
		label_2.setBounds(720, 48, 111, 14);
		panel_1.add(label_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(10, 73, 885, 332);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		
		// botao login
		JButton btnLogin = new JButton("Check");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<String[]> availableRooms = new ArrayList<String[]>();
				availableRooms = getAvailableRooms();
				int pos = 11;
				
				if(availableRooms != null)
				{
					for(int i = 0; i < availableRooms.size(); i++)
					{
						String id = availableRooms.get(i)[0];
						String actual = availableRooms.get(i)[1];
						String maxPlayers = availableRooms.get(i)[2];
						
						JPanel panel_6 = new JPanel();
						panel_6.setBounds(10, pos, 865, 32);
						panel_5.add(panel_6);
						panel_6.setLayout(null);
						
						JLabel lblNewLabel = new JLabel(id);
						lblNewLabel.setBounds(10, 0, 561, 32);
						panel_6.add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel(actual);
						lblNewLabel_1.setBounds(612, 0, 51, 32);
						panel_6.add(lblNewLabel_1);
						
						JLabel lblNewLabel_2 = new JLabel(maxPlayers);
						lblNewLabel_2.setBounds(689, 0, 51, 32);
						panel_6.add(lblNewLabel_2);
						
						JButton btnJoinThis = new JButton("Join This");
						btnJoinThis.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent arg0)
							{
								performEnter(Long.parseLong(id));
							}
						});
						btnJoinThis.setBounds(766, 0, 89, 32);
						panel_6.add(btnJoinThis);
						
						repaint();
						
						System.out.println("Room: " + id + " nrPlayers: " + actual + " maxPlayers: " + maxPlayers);
						
						pos += 40;
					}
				}
			}			
		});
		btnLogin.setBounds(806, 416, 89, 23);
		panel_1.add(btnLogin);
					
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
	
	public void performEnter(long roomid)
	{
		String result = null;
		
		try
		{
			result = Main.cl.httpReq("/game?action=join&type=public&id=" + roomid);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		//ajustar resultado
		if(result.equals("OK"))
		{
			try {
				MainFrame frame = new MainFrame();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.dispose();
		}
	}
	
	public ArrayList<String[]> getAvailableRooms()
	{
		String result = "";

		try
		{
			result = Main.cl.httpReq("/game?action=getAvailable");
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		//ajustar resultado
		if(!result.equals("NOK"))
		{
			ArrayList<String[]> rs = new ArrayList<String[]>();
			String[] roomData;
			
			// processa resultado no formato IDRoom:nrPlayers:MaxPlayers;IDRoom:nrPlayers:MaxPlayers
			
			roomData = result.split(";");
			
			for(int i = 0; i < roomData.length; i++)
			{
				rs.add(roomData[i].split(":")); 
			}
			
			return rs;
		}
		else
		{
			System.out.println("sem salas disponiveis");
			
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
