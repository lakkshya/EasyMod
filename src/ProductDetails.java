import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProductDetails extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetails window = new ProductDetails();
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
	public ProductDetails() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try
				{
					st = con.prepareStatement("select * from products;");
					rs = st.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
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
		getContentPane().setLayout(null);
		setBounds(120, 50, 1280, 700);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.print(JTable.PrintMode.NORMAL);
				} 
				catch (PrinterException ex) 
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnPrint.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPrint.setForeground(Color.BLACK);
		btnPrint.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnPrint.setBorderPainted(false);
		btnPrint.setBackground(new Color(153, 153, 255));
		btnPrint.setBounds(543, 581, 200, 30);
		getContentPane().add(btnPrint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(132, 132, 1010, 410);
		getContentPane().add(scrollPane);
		table = new JTable();
		JTableHeader tableHeader = table.getTableHeader();
		Font headerFont = new Font("Seogre UI Variable", Font.PLAIN, 16);
		tableHeader.setFont(headerFont);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Product Details");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(SystemColor.info);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
		lblNewLabel_2.setBounds(0, 0, 1266, 109);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\productdetails.jpg"));
		lblNewLabel.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\productdetails.jpg");
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
