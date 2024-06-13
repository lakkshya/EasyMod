import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewProduct extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewProduct window = new NewProduct();
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
	public NewProduct() {
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
		getContentPane().setBackground(SystemColor.info);
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Add New Product");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(SystemColor.info);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
		lblNewLabel_2.setBounds(0, 0, 1266, 109);
		getContentPane().add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(233, 148, 821, 407);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(543, 121, 200, 30);
		panel.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Honda", "Tata", "Toyota", "Audi", "BMW", "Mercedes" }));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(184, 178, 200, 30);
		panel.add(comboBox_1_1);
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"Amaze", "City", "Swift", "GWagon","Tiago","Fortuner","A6","S Class"}));
		comboBox_1_1.setToolTipText("");
		comboBox_1_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(543, 178, 200, 30);
		panel.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2023", "2022", "2021", "2020", "2019"}));
		comboBox_1.setToolTipText("");
		comboBox_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		
		JButton btnDelete = new JButton("Add");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pname = textField_4.getText();
				String pid = textField_2.getText();
				String price = textField_1.getText();
				String description = textField.getText();
				String brand = (String)comboBox.getSelectedItem();
				String model = (String)comboBox_1_1.getSelectedItem();
				String year = (String)comboBox_1.getSelectedItem();
				try
				{
					st = con.prepareStatement("insert into products values('"+pid+"', '"+pname+"', '"+description+"', '"+price+"', '"+brand+"', '"+model+"', '"+year+"');");
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Product Added");
					setVisible(false);
					new NewProduct().setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Product already exists");
				}
			}
		});
		btnDelete.setBounds(184, 312, 200, 30);
		panel.add(btnDelete);
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(153, 153, 255));
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_4.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.setText("");
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_1_1.setSelectedIndex(0);
			}
		});
		btnClear.setBounds(438, 312, 200, 30);
		panel.add(btnClear);
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setForeground(Color.BLACK);
		btnClear.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(153, 153, 255));
		
		textField_4 = new JTextField();
		textField_4.setBounds(543, 66, 200, 30);
		panel.add(textField_4);
		textField_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_4 = new JLabel("Product Name");
		lblNewLabel_1_4.setBounds(413, 66, 115, 30);
		panel.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setOpaque(true);
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Price");
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
		lblNewLabel_1_4_1_1.setBounds(413, 121, 115, 30);
		panel.add(lblNewLabel_1_4_1_1);
		lblNewLabel_1_4_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1.setBackground(new Color(255, 255, 255));
		
		textField = new JTextField();
		textField.setBounds(184, 236, 559, 30);
		panel.add(textField);
		textField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_4_1_2 = new JLabel("Description");
		lblNewLabel_1_4_1_2.setBounds(54, 236, 115, 30);
		panel.add(lblNewLabel_1_4_1_2);
		lblNewLabel_1_4_1_2.setOpaque(true);
		lblNewLabel_1_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_2.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1 = new JLabel("Year");
		lblNewLabel_1_4_1_1_1.setBounds(413, 178, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1);
		lblNewLabel_1_4_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1_4_1_1_1_1 = new JLabel("Model");
		lblNewLabel_1_4_1_1_1_1.setBounds(54, 178, 115, 30);
		panel.add(lblNewLabel_1_4_1_1_1_1);
		lblNewLabel_1_4_1_1_1_1.setOpaque(true);
		lblNewLabel_1_4_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1_4_1_1_1_1.setBackground(new Color(255, 255, 255));
		
		textField_2 = new JTextField();
		textField_2.setBounds(184, 66, 200, 30);
		panel.add(textField_2);
		textField_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		lblNewLabel_1.setBounds(54, 65, 115, 30);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\newproduct.jpg"));
		lblNewLabel.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\newproduct.jpg");
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
