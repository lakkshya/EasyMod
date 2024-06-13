import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CustomerDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDetails frame = new CustomerDetails();
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
	public CustomerDetails() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try
				{
					st = con.prepareStatement("select * from customers;");
					rs = st.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
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
	
	private void initialize()
	{
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(Color.WHITE);
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
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
		
		JLabel lblNewLabel = new JLabel("Customer Details");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 48));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 1266, 128);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(132, 132, 1010, 410);
		getContentPane().add(scrollPane);	
		table = new JTable();
		JTableHeader tableHeader = table.getTableHeader();
		Font headerFont = new Font("Seogre UI Variable", Font.PLAIN, 16);
		tableHeader.setFont(headerFont);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel4 = new JLabel("New label");
		lblNewLabel4.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\customerdetails.jpg"));
		lblNewLabel4.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\customerdetails.jpg");
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
