import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateProduct extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateProduct window = new UpdateProduct();
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
	public UpdateProduct() {
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
		getContentPane().setBackground(SystemColor.info);
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Update Product");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(SystemColor.info);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
		lblNewLabel_2.setBounds(0, 0, 1266, 109);
		getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 129, 821, 451);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(213, 252, 200, 30);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Honda", "Tata", "Toyota", "Audi", "BMW", "Mercedes"}));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(213, 309, 200, 30);
		panel.add(comboBox_1_1);
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Amaze", "City", "Swift", "GWagon","Tiago","Fortuner","A6","S Class"}));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(213, 370, 200, 30);
		panel.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = textField_2.getText();
				String pname = textField_4.getText();	
				String price = textField_1.getText();
				String description = textField.getText();
				String brand = (String)comboBox.getSelectedItem();
				String model = (String)comboBox_1_1.getSelectedItem();
				String year = (String)comboBox_1.getSelectedItem();
				try
				{
					st = con.prepareStatement("update products set `P.Name`='"+pname+"', Description='"+description+"', Price='"+price+"', Brand='"+brand+"', Model='"+model+"', Year='"+year+"' where `P.ID`='"+pid+"';");
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Updated");
					setVisible(false);
					new UpdateProduct().setVisible(true);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(527, 252, 200, 30);
		panel.add(btnUpdate);
		btnUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setBackground(new Color(153, 153, 255));
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = textField_2.getText();
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					try
					{
						st = con.prepareStatement("delete from products where `P.ID`='"+pid+"';");
						st.executeUpdate();
						JOptionPane.showMessageDialog(null, "Product deleted successfully");
						setVisible(false);
						new UpdateProduct().setVisible(true);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex);
						ex.printStackTrace();				
					}
				}
			}
		});
		btnDelete.setBounds(527, 309, 200, 30);
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
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_1_1.setSelectedIndex(0);
				textField_2.setEditable(true);
			}
		});
		btnClear.setBounds(527, 370, 200, 30);
		panel.add(btnClear);
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(153, 153, 255));
		
		textField_4 = new JTextField();
		textField_4.setBounds(213, 139, 200, 30);
		panel.add(textField_4);
		textField_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Product Name");
		lblNewLabel_1_4.setBounds(83, 139, 115, 30);
		panel.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setOpaque(true);
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Price");
		lblNewLabel_1_4_1.setBounds(428, 141, 81, 30);
		panel.add(lblNewLabel_1_4_1);
		lblNewLabel_1_4_1.setOpaque(true);
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1.setBackground(new Color(255, 255, 255));
		
		textField_1 = new JTextField();
		textField_1.setBounds(527, 139, 200, 30);
		panel.add(textField_1);
		textField_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Brand");
		lblNewLabel_1_4_1_1.setBounds(83, 252, 115, 30);
		panel.add(lblNewLabel_1_4_1_1);
		lblNewLabel_1_4_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1.setBackground(new Color(255, 255, 255));
		
		textField = new JTextField();
		textField.setBounds(213, 196, 514, 30);
		panel.add(textField);
		textField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_2 = new JLabel("Description");
		lblNewLabel_1_4_1_2.setBounds(83, 196, 115, 30);
		panel.add(lblNewLabel_1_4_1_2);
		lblNewLabel_1_4_1_2.setOpaque(true);
		lblNewLabel_1_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_2.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1 = new JLabel("Year");
		lblNewLabel_1_4_1_1_1.setBounds(83, 370, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1);
		lblNewLabel_1_4_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1_1 = new JLabel("Model");
		lblNewLabel_1_4_1_1_1_1.setBounds(83, 309, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1_1);
		lblNewLabel_1_4_1_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1_1.setBackground(new Color(255, 255, 255));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = textField_2.getText();	
				try
				{
					st = con.prepareStatement("select * from products where `P.ID`='"+pid+"';");
					rs = st.executeQuery();
					if(rs.next())
					{
						textField_2.setEditable(false);
						textField.setText(rs.getString(3));
						textField_1.setText(rs.getString(4));
						textField_4.setText(rs.getString(2));
						comboBox.setSelectedItem(rs.getString(5));
						comboBox_1_1.setSelectedItem(rs.getString(6));
						comboBox_1.setSelectedItem(rs.getString(7));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Product ID does not exist");
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
		btnNewButton.setBounds(526, 48, 100, 30);
		panel.add(btnNewButton);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(153, 153, 255));
		btnNewButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		textField_2 = new JTextField();
		textField_2.setBounds(307, 49, 200, 30);
		panel.add(textField_2);
		textField_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setBounds(177, 48, 115, 30);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(44, 107, 734, 22);
		panel.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\updateproduct.jpg"));
		lblNewLabel.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\updateproduct.jpg");
		Image backgroundImg = background.getImage();	
		Image backgroundScale = backgroundImg.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledBackground = new ImageIcon(backgroundScale);
		lblNewLabel.setIcon(scaledBackground);
		getContentPane().add(lblNewLabel);
		
		WindowListener listener = new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {           
				dispose();           
			}         
		};       
		addWindowListener(listener);
	}
}
