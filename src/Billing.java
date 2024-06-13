import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.event.WindowListener;
import java.awt.geom.Arc2D.Float;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Billing extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblDate;
	private JLabel lblTime;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_5;
	private JTable table;
	private int finalTotal = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing window = new Billing();
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
	public Billing() {
		initialize();
		connect();
		LocalDateTime myDateObj = LocalDateTime.now();
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    String formattedDate = myDateObj.format(myFormatObj);
		lblDate.setText("Date : " + formattedDate);
		
		DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("HH:mm");
	    String formattedTime = myDateObj.format(myFormatObj1);
		lblTime.setText("Time : " + formattedTime);
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
		getContentPane().setBackground(new Color(0, 0, 0));
		setBounds(120, 50, 1280, 700);
		getContentPane().setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(0, 364, 1264, 299);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1264, 90);
		panel.setBackground(new Color(204, 204, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Billing");
		lblNewLabel.setBounds(25, 0, 158, 90);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD, 36));
		
		JLabel lblDate_1_2_1_1 = new JLabel("Bill Details");
		lblDate_1_2_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 24));
		lblDate_1_2_1_1.setBounds(925, 39, 209, 38);
		panel_3.add(lblDate_1_2_1_1);
		
		JLabel lblDate_1_1_3_1 = new JLabel("Total");
		lblDate_1_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_3_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblDate_1_1_3_1.setBounds(903, 115, 61, 30);
		panel_3.add(lblDate_1_1_3_1);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(985, 115, 200, 30);
		panel_3.add(textField_5);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(850, 0, 2, 297);
		panel_3.add(separator_2);
		
		JButton btnNewButton_2 = new JButton("Generate Bill");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = textField.getText();
				String cname = textField_1.getText();
				String address = textField_3.getText();
				String contactno = textField_2.getText();
				String email = textField_4.getText();
				
				String path = "D:\\Bills\\";
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				String filePath = path + cname + "Bill.pdf";
				try
				{
					PdfWriter.getInstance(doc, new FileOutputStream(filePath));
					doc.open();
					Paragraph paragraph1 = new Paragraph("                                                        EasyMod Billing System\n");
					doc.add(paragraph1);
					Paragraph paragraph2 = new Paragraph("\nCustomer Details:\nC. ID : "+cid+"\nC. Name : "+cname+"\nContact No. : "+contactno+"\nEmail : "+email+"\nAddress : "+address+"\n\n");
					doc.add(paragraph2);
					PdfPTable tbl = new PdfPTable(5);
					tbl.addCell("P. ID");
					tbl.addCell("Product");
					tbl.addCell("Price");
					tbl.addCell("Quantity");
					tbl.addCell("Sub Total");
					for(int i=0; i<table.getRowCount(); i++)
					{
						String n = table.getValueAt(i, 0).toString();
						String o = table.getValueAt(i, 1).toString();
						String p = table.getValueAt(i, 2).toString();
						String q = table.getValueAt(i, 3).toString();
						String r = table.getValueAt(i, 4).toString();
						tbl.addCell(n);
						tbl.addCell(o);
						tbl.addCell(p);
						tbl.addCell(q);
						tbl.addCell(r);
					}
					doc.add(tbl);
					Paragraph paragraph3 = new Paragraph("\nTotal : "+textField_5.getText()+"\n\nThank you for visiting! Please come again.");
					doc.add(paragraph3);
					doc.close();
					JOptionPane.showMessageDialog(null, "Bill Generated");
					setVisible(false);
					
					File file = new File(filePath);
					if(file.exists()) {
						Desktop.getDesktop().open(file);
					} else {
						System.out.println("File doesn't exist.");
					}
					
					new Billing().setVisible(true);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(153, 153, 255));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setBounds(926, 180, 259, 30);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Clear");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Do you really want to clear?", "Select", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textField_7.setText("");
					textField_8.setText("");
					textField_9.setText("");
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.setRowCount(0);
					textField.setEditable(true);
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					textField_3.setEditable(true);
					textField_4.setEditable(true);
					textField_9.setEditable(true);
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnNewButton_2_1.setBorderPainted(false);
		btnNewButton_2_1.setBackground(new Color(204, 204, 255));
		btnNewButton_2_1.setBounds(925, 225, 259, 30);
		panel_3.add(btnNewButton_2_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
		scrollPane.setBounds(60, 39, 718, 216);
		panel_3.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Segoe UI Variable", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"P. ID", "Product", "Price", "Quantity", "Sub Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Float.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		JTableHeader tableHeader = table.getTableHeader();
		Font headerFont = new Font("Seogre UI Variable", Font.PLAIN, 16);
		tableHeader.setFont(headerFont);
		
		scrollPane.setViewportView(table);
		
		lblDate = new JLabel("Date :");
		lblDate.setBounds(1082, 11, 180, 35);
		panel.add(lblDate);
		lblDate.setFont(new Font("Segoe UI Variable", Font.BOLD, 18));
		
		lblTime = new JLabel("Time :");
		lblTime.setBounds(1082, 48, 180, 30);
		panel.add(lblTime);
		lblTime.setFont(new Font("Segoe UI Variable", Font.BOLD, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 90, 1264, 136);
		panel_1.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(975, 76, 220, 30);
		panel_1.add(textField_3);
		textField_3.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_3.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(975, 34, 220, 30);
		panel_1.add(textField_2);
		textField_2.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_2.setColumns(10);
		
		JLabel lblDate_1_1_1 = new JLabel("Contact No");
		lblDate_1_1_1.setBounds(867, 34, 90, 30);
		panel_1.add(lblDate_1_1_1);
		lblDate_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		JLabel lblDate_1_1_1_1 = new JLabel("Address");
		lblDate_1_1_1_1.setBounds(867, 75, 90, 30);
		panel_1.add(lblDate_1_1_1_1);
		lblDate_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		textField_1 = new JTextField();
		textField_1.setBounds(622, 34, 220, 30);
		panel_1.add(textField_1);
		textField_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(622, 76, 220, 30);
		panel_1.add(textField_4);
		textField_4.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_4.setColumns(10);
		
		JLabel lblDate_1_1 = new JLabel("Name");
		lblDate_1_1.setBounds(513, 34, 90, 30);
		panel_1.add(lblDate_1_1);
		lblDate_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		JLabel lblDate_1_1_2 = new JLabel("Email");
		lblDate_1_1_2.setBounds(513, 76, 90, 30);
		panel_1.add(lblDate_1_1_2);
		lblDate_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		JLabel lblDate_1_2 = new JLabel("Customer Details");
		lblDate_1_2.setBounds(58, 25, 209, 38);
		panel_1.add(lblDate_1_2);
		lblDate_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 24));
		
		JLabel lblDate_1 = new JLabel("Customer ID");
		lblDate_1.setBounds(58, 76, 100, 30);
		panel_1.add(lblDate_1);
		lblDate_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.setBounds(168, 76, 150, 30);
		panel_1.add(textField);
		textField.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = textField.getText();	
				try
				{
					st = con.prepareStatement("select * from customers where `C.ID`='"+cid+"';");
					rs = st.executeQuery();
					if(rs.next())
					{
						textField.setEditable(false);
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(4));
						textField_3.setText(rs.getString(3));
						textField_4.setText(rs.getString(5));
						textField_1.setEditable(false);
						textField_2.setEditable(false);
						textField_3.setEditable(false);
						textField_4.setEditable(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Customer ID does not exist");
						textField.setText("");
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(328, 76, 100, 30);
		panel_1.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(153, 153, 255));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(492, 0, 14, 136);
		panel_1.add(separator);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 227, 1262, 136);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(0, 0, 1262, 136);
		panel_2.add(panel_1_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(975, 34, 220, 30);
		panel_1_1.add(textField_6);
		
		JLabel lblDate_1_1_1_2 = new JLabel("Quantity");
		lblDate_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_1_2.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblDate_1_1_1_2.setBounds(867, 34, 90, 30);
		panel_1_1.add(lblDate_1_1_1_2);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(622, 34, 220, 30);
		panel_1_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(622, 76, 220, 30);
		panel_1_1.add(textField_8);
		
		JLabel lblDate_1_1_3 = new JLabel("Name");
		lblDate_1_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_3.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblDate_1_1_3.setBounds(513, 34, 90, 30);
		panel_1_1.add(lblDate_1_1_3);
		
		JLabel lblDate_1_1_2_1 = new JLabel("Price");
		lblDate_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate_1_1_2_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblDate_1_1_2_1.setBounds(513, 76, 90, 30);
		panel_1_1.add(lblDate_1_1_2_1);
		
		JLabel lblDate_1_2_1 = new JLabel("Product Details");
		lblDate_1_2_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 24));
		lblDate_1_2_1.setBounds(58, 25, 209, 38);
		panel_1_1.add(lblDate_1_2_1);
		
		JLabel lblDate_1_3 = new JLabel("Product ID");
		lblDate_1_3.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		lblDate_1_3.setBounds(58, 76, 100, 30);
		panel_1_1.add(lblDate_1_3);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Segoe UI Variable", Font.PLAIN, 14));
		textField_9.setColumns(10);
		textField_9.setBounds(168, 76, 150, 30);
		panel_1_1.add(textField_9);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = textField_9.getText();	
				try
				{
					st = con.prepareStatement("select * from products where `P.ID`='"+pid+"';");
					rs = st.executeQuery();
					if(rs.next())
					{
						textField_9.setEditable(false);
						textField_7.setText(rs.getString(2));
						textField_8.setText(rs.getString(4));
						textField_6.setText("1");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Product ID does not exist");
						textField_9.setText("");
					}
					
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(new Color(153, 153, 255));
		btnNewButton_1.setBounds(328, 76, 100, 30);
		panel_1_1.add(btnNewButton_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(492, 0, 14, 136);
		panel_1_1.add(separator_1);
		
		JButton btnNewButton_1_1 = new JButton("Add");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int price = Integer.parseInt(textField_8.getText());
					int quantity = Integer.parseInt(textField_6.getText());
					int total = price*quantity;
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.addRow(new Object[] {textField_9.getText(), textField_7.getText(), price, quantity, total});
					finalTotal = finalTotal + total;
					String finalTotal1 = String.valueOf(finalTotal);
					textField_5.setText(finalTotal1);
					
					textField_6.setText("");
					textField_7.setText("");
					textField_8.setText("");
					textField_9.setText("");
					textField_9.setEditable(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBackground(new Color(153, 153, 255));
		btnNewButton_1_1.setBounds(892, 75, 140, 30);
		panel_1_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Clear");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_9.setText("");
				textField_9.setEditable(true);
			}
		});
		btnNewButton_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1_1_1.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setBackground(new Color(204, 204, 255));
		btnNewButton_1_1_1.setBounds(1055, 74, 140, 30);
		panel_1_1.add(btnNewButton_1_1_1);
		
		WindowListener listener = new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {           
				dispose();           
			}         
		};       
		addWindowListener(listener);
	}
}
