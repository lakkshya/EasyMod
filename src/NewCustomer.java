import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;

public class NewCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer window = new NewCustomer();
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
	public NewCustomer() {
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
		getContentPane().setBackground(Color.WHITE);
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Add New Customer");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1266, 128);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 148, 821, 448);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(543, 178, 200, 30);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Honda", "Tata", "Toyota", "Audi", "BMW", "Mercedes"}));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(184, 235, 200, 30);
		panel.add(comboBox_1_1);
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Amaze", "City", "Swift", "GWagon","Tiago","Fortuner","A6","S Class"}));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(543, 235, 200, 30);
		panel.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		textField_4 = new JTextField();
		textField_4.setBounds(543, 66, 200, 30);
		panel.add(textField_4);
		textField_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_4.setColumns(10);
		
		JComboBox comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setToolTipText("");
		comboBox_1_1_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		comboBox_1_1_1.setBounds(184, 178, 200, 30);
		comboBox_1_1_1.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Others"}));
		panel.add(comboBox_1_1_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cname = textField_4.getText();
				String cid = textField_2.getText();
				String contact = textField_1.getText();
				String email = textField.getText();
				String address = textField_6.getText();
				String gender = (String)comboBox_1_1_1.getSelectedItem();
				String brand = (String)comboBox.getSelectedItem();
				String model = (String)comboBox_1_1.getSelectedItem();
				String year = (String)comboBox_1.getSelectedItem();
				try
				{
					st = con.prepareStatement("insert into customers values('"+cid+"', '"+cname+"', '"+address+"', '"+contact+"', '"+email+"', '"+gender+"', '"+brand+"', '"+model+"', '"+year+"');");
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added");
					setVisible(false);
					new NewCustomer().setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Customer already exists");
				}
			}
		});
		btnAdd.setBounds(184, 367, 200, 30);
		panel.add(btnAdd);
		btnAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnAdd.setBorderPainted(false);
		btnAdd.setBackground(new Color(153, 153, 255));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_4.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.setText("");
				textField_6.setText("");
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_1_1.setSelectedIndex(0);
				comboBox_1_1_1.setSelectedIndex(0);
			}
		});
		btnClear.setBounds(440, 367, 200, 30);
		panel.add(btnClear);
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(153, 153, 255));
		
		
		
		JLabel lblNewLabel_1_4 = new JLabel("Customer Name");
		lblNewLabel_1_4.setBounds(394, 66, 134, 30);
		panel.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setOpaque(true);
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Contact No.");
		lblNewLabel_1_4_1.setBounds(54, 121, 115, 30);
		panel.add(lblNewLabel_1_4_1);
		lblNewLabel_1_4_1.setOpaque(true);
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1.setBackground(new Color(255, 255, 255));
		
		textField_1 = new JTextField();
		textField_1.setBounds(184, 122, 200, 30);
		panel.add(textField_1);
		textField_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Brand");
		lblNewLabel_1_4_1_1.setBounds(413, 178, 115, 30);
		panel.add(lblNewLabel_1_4_1_1);
		lblNewLabel_1_4_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1 = new JLabel("Year");
		lblNewLabel_1_4_1_1_1.setBounds(413, 235, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1);
		lblNewLabel_1_4_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1_1 = new JLabel("Model");
		lblNewLabel_1_4_1_1_1_1.setBounds(54, 235, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1_1);
		lblNewLabel_1_4_1_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		lblNewLabel_1.setBounds(54, 65, 115, 30);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(184, 66, 200, 30);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(543, 66, 200, 30);
		panel.add(textField_3);
		
		JLabel lblNewLabel_1_4_1_2 = new JLabel("Email");
		lblNewLabel_1_4_1_2.setOpaque(true);
		lblNewLabel_1_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_4_1_2.setBounds(394, 120, 134, 30);
		panel.add(lblNewLabel_1_4_1_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(543, 121, 200, 30);
		panel.add(textField);
		
		JLabel lblNewLabel_1_4_1_3 = new JLabel("Gender");
		lblNewLabel_1_4_1_3.setOpaque(true);
		lblNewLabel_1_4_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_3.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_3.setBackground(Color.WHITE);
		lblNewLabel_1_4_1_3.setBounds(54, 177, 115, 30);
		panel.add(lblNewLabel_1_4_1_3);
		
		JLabel lblNewLabel_1_4_1_3_1 = new JLabel("Address");
		lblNewLabel_1_4_1_3_1.setOpaque(true);
		lblNewLabel_1_4_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_3_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_3_1.setBackground(Color.WHITE);
		lblNewLabel_1_4_1_3_1.setBounds(54, 292, 115, 30);
		panel.add(lblNewLabel_1_4_1_3_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(184, 293, 559, 30);
		panel.add(textField_6);
		
		
		
		JLabel lblNewLabel4 = new JLabel("New label");
		lblNewLabel4.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\newcustomer.jpg"));
		lblNewLabel4.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\newcustomer.jpg");
		Image backgroundImg = background.getImage();	
		Image backgroundScale = backgroundImg.getScaledInstance(lblNewLabel4.getWidth(), lblNewLabel4.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledBackground = new ImageIcon(backgroundScale);
		lblNewLabel4.setIcon(scaledBackground);
		getContentPane().add(lblNewLabel4);
		
		WindowListener listener = new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {           
				dispose();           
			}         
		};       
		addWindowListener(listener);
		
	}
}
