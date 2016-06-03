package interfaces;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.Main;
import utils.Utils;

@SuppressWarnings("serial")
public class EnterPrivateRoom extends JFrame {

	private JPanel contentPane;
	private JTextField roomIdField;
	private JPasswordField passwordField;

	private ChooseRoom chooseRoom;


	/**
	 * Create the frame.
	 */
	public EnterPrivateRoom()
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
		panel_1.setBounds(261, 318, 445, 231);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Enter room id and password");
		label.setBounds(10, 5, 273, 32);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(label);
		
		roomIdField = new JTextField();
		roomIdField.setBounds(23, 73, 397, 32);
		panel_1.add(roomIdField);
		
		JButton btnLogin = new JButton("Enter");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				performEnter();
			}			
		});
		btnLogin.setBounds(331, 195, 89, 23);
		panel_1.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Room ID:");
		lblUsername.setBounds(33, 48, 65, 14);
		panel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 116, 65, 14);
		panel_1.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(23, 141, 397, 32);
		panel_1.add(passwordField);
		
		JButton button = new JButton("Cancel");
		button.setBounds(23, 195, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				returnChooseRoom(e);
			}			
		});
		panel_1.add(button);
		
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
		
		JLabel lblPleaseLoginBelow = new JLabel("Enter private room");
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
		String roomid = roomIdField.getText();
		String password = new String(passwordField.getPassword());
		password = Utils.encrypt(password);
		
		try
		{
			result = Main.cl.httpReq("/game?action=join&type=private&id=" + roomid + "&password=" + password);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		//ajustar resultado
		if(!result.equals("NOK"))
		{
			this.dispose();
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
