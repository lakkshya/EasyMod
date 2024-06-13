import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.sql.*;

public class UpdateCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomer window = new UpdateCustomer();
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
	public UpdateCustomer() {
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement st;
	ResultSet rs;
	
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
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(Color.WHITE);
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Customer");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1266, 128);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 129, 821, 476);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(525, 287, 200, 30);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Honda", "Tata", "Toyota", "Audi", "BMW", "Mercedes"}));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(525, 343, 200, 30);
		panel.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(211, 343, 200, 30);
		panel.add(comboBox_1_1);
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Amaze", "City", "Swift", "GWagon","Tiago","Fortuner","A6","S Class"}));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("");
		comboBox_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		comboBox_2.setBounds(211, 287, 200, 30);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Others"}));
		panel.add(comboBox_2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = textField_2.getText();
				String cname = textField_4.getText();	
				String contact = textField_5.getText();
				String email = textField_1.getText();
				String address = textField.getText();
				String gender = (String)comboBox_2.getSelectedItem();
				String brand = (String)comboBox.getSelectedItem();
				String model = (String)comboBox_1_1.getSelectedItem();
				String year = (String)comboBox_1.getSelectedItem();
				try
				{
					st = con.prepareStatement("update customers set `C.Name`='"+cname+"', Address='"+address+"', `Contact No.`='"+contact+"', Email='"+email+"', Gender='"+gender+"', `Car Brand`='"+brand+"', `Car Model`='"+model+"', `Car Year`='"+year+"' where `C.ID`='"+cid+"';");
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					setVisible(false);
					new UpdateCustomer().setVisible(true);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnUpdate.setBounds(81, 408, 200, 30);
		panel.add(btnUpdate);
		btnUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(153, 153, 255));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = textField_2.getText();
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					try
					{
						st = con.prepareStatement("delete from customers where `C.ID`='"+cid+"';");
						st.executeUpdate();
						JOptionPane.showMessageDialog(null, "Customer deleted successfully");
						setVisible(false);
						new UpdateCustomer().setVisible(true);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex);
						ex.printStackTrace();				
					}
				}
			}
		});
		btnDelete.setBounds(302, 408, 200, 30);
		panel.add(btnDelete);
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(153, 153, 255));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_4.setText("");
				textField_5.setText("");
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_1_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				textField_2.setEditable(true);
			}
		});
		btnClear.setBounds(525, 408, 200, 30);
		panel.add(btnClear);
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(153, 153, 255));
		
		textField_4 = new JTextField();
		textField_4.setBounds(211, 119, 514, 30);
		panel.add(textField_4);
		textField_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Customer Name");
		lblNewLabel_1_4.setBounds(64, 119, 132, 30);
		panel.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setOpaque(true);
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Email");
		lblNewLabel_1_4_1.setBounds(426, 176, 81, 30);
		panel.add(lblNewLabel_1_4_1);
		lblNewLabel_1_4_1.setOpaque(true);
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1.setBackground(new Color(255, 255, 255));
		
		textField_1 = new JTextField();
		textField_1.setBounds(525, 174, 200, 30);
		panel.add(textField_1);
		textField_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Brand");
		lblNewLabel_1_4_1_1.setBounds(426, 287, 84, 30);
		panel.add(lblNewLabel_1_4_1_1);
		lblNewLabel_1_4_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1.setBackground(new Color(255, 255, 255));
		
		textField = new JTextField();
		textField.setBounds(211, 231, 514, 30);
		panel.add(textField);
		textField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_2 = new JLabel("Address");
		lblNewLabel_1_4_1_2.setBounds(81, 231, 115, 30);
		panel.add(lblNewLabel_1_4_1_2);
		lblNewLabel_1_4_1_2.setOpaque(true);
		lblNewLabel_1_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_2.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1 = new JLabel("Year");
		lblNewLabel_1_4_1_1_1.setBounds(426, 343, 84, 30);
		panel.add(lblNewLabel_1_4_1_1_1);
		lblNewLabel_1_4_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1_1 = new JLabel("Model");
		lblNewLabel_1_4_1_1_1_1.setBounds(81, 343, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1_1);
		lblNewLabel_1_4_1_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1_1.setBackground(new Color(255, 255, 255));
		
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = textField_2.getText();	
				try
				{
					st = con.prepareStatement("select * from customers where `C.ID`='"+cid+"';");
					rs = st.executeQuery();
					if(rs.next())
					{
						textField_2.setEditable(false);
						textField.setText(rs.getString(3));
						textField_1.setText(rs.getString(5));
						textField_4.setText(rs.getString(2));
						textField_5.setText(rs.getString(4));
						comboBox.setSelectedItem(rs.getString(7));
						comboBox_2.setSelectedItem(rs.getString(6));
						comboBox_1_1.setSelectedItem(rs.getString(8));
						comboBox_1.setSelectedItem(rs.getString(9));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Customer ID does not exist");
						textField_2.setText("");
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(526, 38, 100, 30);
		panel.add(btnNewButton);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(153, 153, 255));
		btnNewButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		textField_2 = new JTextField();
		textField_2.setBounds(307, 39, 200, 30);
		panel.add(textField_2);
		textField_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		lblNewLabel_1.setBounds(177, 38, 115, 30);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 101, 734, 22);
		panel.add(separator_1);
		
		JLabel lblNewLabel_1_4_1_1_2 = new JLabel("Gender");
		lblNewLabel_1_4_1_1_2.setOpaque(true);
		lblNewLabel_1_4_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_2.setBackground(Color.WHITE);
		lblNewLabel_1_4_1_1_2.setBounds(81, 287, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_2);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(211, 174, 200, 30);
		panel.add(textField_5);
		
		JLabel lblNewLabel_1_4_1_3 = new JLabel("Contact No.");
		lblNewLabel_1_4_1_3.setOpaque(true);
		lblNewLabel_1_4_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_3.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_3.setBackground(Color.WHITE);
		lblNewLabel_1_4_1_3.setBounds(81, 176, 112, 30);
		panel.add(lblNewLabel_1_4_1_3);
		
		
		JLabel lblNewLabel4 = new JLabel("New label");
		lblNewLabel4.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\updatecustomer.jpg"));
		lblNewLabel4.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\updatecustomer.jpg");
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
