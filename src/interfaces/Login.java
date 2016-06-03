package interfaces;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
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
		panel_1.setBounds(259, 197, 445, 231);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Login with your account:");
		label.setBounds(10, 5, 273, 32);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_1.add(label);
		
		textField = new JTextField();
		textField.setBounds(23, 63, 397, 32);
		panel_1.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(23, 121, 397, 32);
		panel_1.add(passwordField);
		
		// botao login
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				System.out.println("click login");
			}
		});
		btnLogin.setBounds(331, 164, 89, 23);
		panel_1.add(btnLogin);
		
		// botao criar conta
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				System.out.println("click addconta");
			}
		});
		btnCreateAccount.setBounds(157, 197, 126, 23);
		panel_1.add(btnCreateAccount);
		
		JLabel lblPassword = new JLabel("Username:");
		lblPassword.setBounds(33, 48, 65, 14);
		panel_1.add(lblPassword);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(33, 106, 65, 14);
		panel_1.add(label_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 998, 122);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 958, 37);
		panel_2.add(panel_3);
		
		JLabel lblWelcomeToRisk = new JLabel("Welcome to RISK");
		panel_3.add(lblWelcomeToRisk);
		lblWelcomeToRisk.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 77, 425, 35);
		panel_2.add(panel_4);
		
		JLabel lblPleaseLoginBelow = new JLabel("Please login below or create an account");
		panel_4.add(lblPleaseLoginBelow);
		lblPleaseLoginBelow.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseLoginBelow.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(259, 464, 445, 231);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 284, 29);
		panel_5.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblLoginWithFacebook = new JLabel("Login with Facebook:");
		lblLoginWithFacebook.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_6.add(lblLoginWithFacebook);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		btnExit.setBounds(904, 677, 92, 34);
		panel.add(btnExit);
		
		JLabel background =  new JLabel(new ImageIcon("risk.jpg"));
		background.setBounds(0, 50, 1024, 768);
		panel.add(background);
	}
}
