import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField signupNameInput;
	private JTextField signupEmailInput;
	private JPasswordField signupPasswordInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement st;
	
	public void connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/easymod", "root", "Sidak@123");
		}
		catch(Exception e)
		{
			
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 16));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(400, 180, 720, 486);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 255));
		panel.setBounds(0, 0, 260, 449);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel signupSideGreeting = new JLabel("Login to EazyMod");
		signupSideGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		signupSideGreeting.setForeground(new Color(255, 255, 255));
		signupSideGreeting.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
		signupSideGreeting.setBounds(0, 120, 260, 43);
		panel.add(signupSideGreeting);
		
		JLabel signupSideText1 = new JLabel("Login to the system and");
		signupSideText1.setHorizontalAlignment(SwingConstants.CENTER);
		signupSideText1.setForeground(Color.WHITE);
		signupSideText1.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		signupSideText1.setBounds(0, 180, 260, 28);
		panel.add(signupSideText1);
		
		JLabel signupSideText2 = new JLabel("generate product bills.");
		signupSideText2.setHorizontalAlignment(SwingConstants.CENTER);
		signupSideText2.setForeground(Color.WHITE);
		signupSideText2.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		signupSideText2.setBounds(0, 200, 260, 28);
		panel.add(signupSideText2);
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Login().setVisible(true);
			}
		});
		loginButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		loginButton.setBounds(75, 260, 110, 36);
		panel.add(loginButton);
		loginButton.setContentAreaFilled(false);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		loginButton.setBackground(new Color(102, 0, 255));
		
		JLabel signupGreeting = new JLabel("Create Account");
		signupGreeting.setBounds(259, 50, 445, 43);
		getContentPane().add(signupGreeting);
		signupGreeting.setForeground(new Color(102, 0, 255));
		signupGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		signupGreeting.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
		
		JLabel signupName = new JLabel("NAME");
		signupName.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		signupName.setBounds(323, 118, 150, 20);
		getContentPane().add(signupName);
		
		signupNameInput = new JTextField();
		signupNameInput.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		signupNameInput.setBounds(323, 138, 330, 36);
		getContentPane().add(signupNameInput);
		signupNameInput.setColumns(10);
		
		JLabel signupEmail = new JLabel("EMAIL");
		signupEmail.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		signupEmail.setBounds(323, 188, 150, 20);
		getContentPane().add(signupEmail);
		
		signupEmailInput = new JTextField();
		signupEmailInput.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		signupEmailInput.setColumns(10);
		signupEmailInput.setBounds(323, 208, 330, 36);
		getContentPane().add(signupEmailInput);
		
		JLabel signupPassword = new JLabel("PASSWORD");
		signupPassword.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		signupPassword.setBounds(323, 258, 150, 20);
		getContentPane().add(signupPassword);
		
		signupPasswordInput = new JPasswordField();
		signupPasswordInput.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		signupPasswordInput.setBounds(323, 278, 330, 36);
		getContentPane().add(signupPasswordInput);
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.setBorderPainted(false);
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setBackground(new Color(102, 0, 255));
		signupButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aname = signupNameInput.getText();
				String aemail = signupEmailInput.getText();
				String apass = signupPasswordInput.getText();
				try
				{
					st = con.prepareStatement("insert into admin values('"+aname+"', '"+aemail+"', '"+apass+"');");
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Admin Added");
					setVisible(false);
					new Login().setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Customer already exists");
				}
			}
		});
		signupButton.setBounds(432, 350, 110, 36);
		getContentPane().add(signupButton);
	}
}
