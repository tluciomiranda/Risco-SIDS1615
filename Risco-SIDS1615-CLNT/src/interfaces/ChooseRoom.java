package interfaces;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
public class ChooseRoom extends JFrame {

	private JPanel contentPane;
	
	private CreatePrivateRoom cprivr;
	private CreatePublicRoom cpubr;
	private EnterPrivateRoom entprivr;
	private EnterPublicRoom entpubr;

	/**
	 * Create the frame.
	 */
	public ChooseRoom() 
	{				
		cprivr = new CreatePrivateRoom();
		cpubr = new CreatePublicRoom();
		entprivr = new EnterPrivateRoom();
		entpubr = new EnterPublicRoom();
		
		cprivr.setChooseRoom(this);
		cpubr.setChooseRoom(this);
		entprivr.setChooseRoom(this);
		entpubr.setChooseRoom(this);
				
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
		panel_1.setBounds(263, 201, 445, 411);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Enter Public Room");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				gotoEnterPublic(e);
			}
		});
		btnNewButton.setBounds(30, 36, 379, 63);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("Create Public Room");
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				gotoCreatePublic();
			}
		});
		button.setBounds(30, 131, 379, 63);
		panel_1.add(button);
		
		JButton button_1 = new JButton("Enter Private Room");
		button_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				gotoEnterPrivate();
			}
		});
		button_1.setBounds(30, 224, 379, 63);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("Create Private Room");
		button_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		button_2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				gotoCreatePrivate();
			}
		});
		button_2.setBounds(30, 318, 379, 63);
		panel_1.add(button_2);
		
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
		
		JLabel lblPleaseLoginBelow = new JLabel("Please choose your type of game");
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
	
	private void gotoEnterPublic(ActionEvent e)
	{
		entpubr.setVisible(true);
		this.dispose();		
	}
	
	private void gotoEnterPrivate()
	{
		entprivr.setVisible(true);
		this.dispose();		
	}
	
	private void gotoCreatePublic()
	{
		cpubr.setVisible(true);
		this.dispose();		
	}
	
	private void gotoCreatePrivate()
	{
		this.cprivr.setVisible(true);
		this.dispose();		
	}
}
