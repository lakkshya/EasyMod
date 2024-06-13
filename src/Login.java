import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField loginEmailInput;
	private JPasswordField loginPasswordInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement st1;
	PreparedStatement st2;
	ResultSet rs1;
	ResultSet rs2;
	
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
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(400, 180, 720, 486);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 0, 255));
		panel.setBounds(444, 0, 262, 449);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel loginSideGreeting = new JLabel("Add an Admin");
		loginSideGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		loginSideGreeting.setForeground(new Color(255, 255, 255));
		loginSideGreeting.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
		loginSideGreeting.setBounds(0, 120, 260, 43);
		panel.add(loginSideGreeting);
		
		JLabel loginSideText1 = new JLabel("An admin can login to the");
		loginSideText1.setHorizontalAlignment(SwingConstants.CENTER);
		loginSideText1.setForeground(Color.WHITE);
		loginSideText1.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		loginSideText1.setBounds(0, 180, 260, 28);
		panel.add(loginSideText1);
		
		JLabel loginSideText2 = new JLabel("system and generate product biils.");
		loginSideText2.setHorizontalAlignment(SwingConstants.CENTER);
		loginSideText2.setForeground(Color.WHITE);
		loginSideText2.setFont(new Font("Segoe UI Variable", Font.BOLD, 14));
		loginSideText2.setBounds(0, 200, 260, 28);
		panel.add(loginSideText2);
		
		JButton signupButton = new JButton("Sign Up");
		signupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Register().setVisible(true);
			}
		});
		signupButton.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		signupButton.setBounds(75, 260, 110, 36);
		panel.add(signupButton);
		signupButton.setContentAreaFilled(false);
		signupButton.setForeground(new Color(255, 255, 255));
		signupButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		signupButton.setBackground(new Color(102, 0, 255));
		
		JLabel loginGreeting = new JLabel("Sign in to Account");
		loginGreeting.setBounds(0, 80, 445, 43);
		getContentPane().add(loginGreeting);
		loginGreeting.setForeground(new Color(102, 0, 255));
		loginGreeting.setHorizontalAlignment(SwingConstants.CENTER);
		loginGreeting.setFont(new Font("Segoe UI Variable", Font.BOLD, 28));
		
		JLabel loginEmail = new JLabel("EMAIL");
		loginEmail.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		loginEmail.setBounds(63, 140, 150, 20);
		getContentPane().add(loginEmail);
		
		loginEmailInput = new JTextField();
		loginEmailInput.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		loginEmailInput.setColumns(10);
		loginEmailInput.setBounds(63, 160, 330, 36);
		getContentPane().add(loginEmailInput);
		
		JLabel loginPassword = new JLabel("PASSWORD");
		loginPassword.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		loginPassword.setBounds(63, 210, 150, 20);
		getContentPane().add(loginPassword);
		
		loginPasswordInput = new JPasswordField();
		loginPasswordInput.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		loginPasswordInput.setBounds(63, 230, 330, 36);
		getContentPane().add(loginPasswordInput);
		
		JButton loginButton = new JButton("Log In");
		loginButton.setBorderPainted(false);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(102, 0, 255));
		loginButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String aemail = loginEmailInput.getText();
			     String apass = loginPasswordInput.getText();

			     try {
			    	 st1 = con.prepareStatement("select Password from admin where Email=?;");
			         st1.setString(1, aemail);
			         rs1 = st1.executeQuery();

			         if (rs1.next()) {
			        	 String dbPassword = rs1.getString("Password");

			             if (apass.equals(dbPassword)) {
			            	 setVisible(false);
			                 new Home().setVisible(true);
			             } else {
			                 JOptionPane.showMessageDialog(null, "Incorrect Password");
			             }
			         } else {
			             JOptionPane.showMessageDialog(null, "Email not found");
			         }
			     } catch (SQLException e1) {
			         e1.printStackTrace();
			     }
			}
		});
		loginButton.setBounds(173, 310, 110, 36);
		getContentPane().add(loginButton);
	}

}
