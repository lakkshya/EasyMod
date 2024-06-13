import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EASYMOD");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 58));
		lblNewLabel.setBounds(126, 325, 400, 69);
		getContentPane().add(lblNewLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(700, 62, 520, 548);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton addNewCust = new JButton("");
		addNewCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewCustomer().setVisible(true);
			}
		});
		addNewCust.setBounds(74, 59, 100, 100);
		panel.add(addNewCust);
		addNewCust.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addNewCust.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 1.png"));
		ImageIcon custAdd = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 1.png");
		Image custImg = custAdd.getImage();	
		Image custScale = custImg.getScaledInstance(addNewCust.getWidth(), addNewCust.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledCust = new ImageIcon(custScale);
		addNewCust.setIcon(scaledCust);
		
		JButton updateCust = new JButton("");
		updateCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateCustomer().setVisible(true);
			}
		});
		updateCust.setBounds(208, 59, 100, 100);
		panel.add(updateCust);
		updateCust.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		updateCust.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 2.png"));
		ImageIcon updateCustDet = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 2.png");
		Image updateCustImg = updateCustDet.getImage();	
		Image updateCustScale = updateCustImg.getScaledInstance(updateCust.getWidth(), updateCust.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledUpdateCust = new ImageIcon(updateCustScale);
		updateCust.setIcon(scaledUpdateCust);
		
		JButton showCust = new JButton("");
		showCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerDetails().setVisible(true);
			}
		});
		showCust.setBounds(344, 59, 100, 100);
		panel.add(showCust);
		showCust.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		showCust.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 3.png"));
		ImageIcon showCustDet = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 3.png");
		Image showCustImg = showCustDet.getImage();
		Image showCustScale = showCustImg.getScaledInstance(showCust.getWidth(), showCust.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledShowCust = new ImageIcon(showCustScale);
		showCust.setIcon(scaledShowCust);
		
		JButton addNewProduct = new JButton("");
		addNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewProduct().setVisible(true);
			}
		});
		addNewProduct.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		addNewProduct.setBounds(74, 221, 100, 100);
		panel.add(addNewProduct);
		addNewProduct.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 4.png"));
		ImageIcon productAdd = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 4.png");
		Image productAddImg = productAdd.getImage();
		Image productAddScale = productAddImg.getScaledInstance(addNewProduct.getWidth(), addNewProduct.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledProductAdd = new ImageIcon(productAddScale);
		addNewProduct.setIcon(scaledProductAdd);
		
		JButton updateProduct = new JButton("");
		updateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateProduct().setVisible(true);
			}
		});
		updateProduct.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		updateProduct.setBounds(208, 221, 100, 100);
		panel.add(updateProduct);
		updateProduct.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 5.png"));
		ImageIcon productUpdate = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 5.png");
		Image productUpdateImg = productUpdate.getImage();
		Image productUpdateScale = productUpdateImg.getScaledInstance(updateProduct.getWidth(), updateProduct.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledProductUpdate = new ImageIcon(productUpdateScale);
		updateProduct.setIcon(scaledProductUpdate);
		
		JButton showProduct = new JButton("");
		showProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProductDetails().setVisible(true);
			}
		});
		showProduct.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		showProduct.setBounds(344, 221, 100, 100);
		panel.add(showProduct);
		showProduct.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 6.png"));
		ImageIcon productShow = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 6.png");
		Image productShowImg = productShow.getImage();
		Image productShowScale = productShowImg.getScaledInstance(showProduct.getWidth(), showProduct.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledProductShow = new ImageIcon(productShowScale);
		showProduct.setIcon(scaledProductShow);
		
		JButton generateBill = new JButton("");
		generateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Billing().setVisible(true);
			}
		});
		generateBill.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		generateBill.setBounds(74, 390, 100, 100);
		panel.add(generateBill);
		generateBill.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 7.png"));
		ImageIcon bill = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 7.png");
		Image billImg = bill.getImage();
		Image billScale = billImg.getScaledInstance(generateBill.getWidth(), generateBill.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledBill = new ImageIcon(billScale);
		generateBill.setIcon(scaledBill);
		
		JButton logoutSys = new JButton("");
		logoutSys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					setVisible(false);
					new Login().setVisible(true);
				}
			}
		});
		logoutSys.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		logoutSys.setBounds(208, 390, 100, 100);
		panel.add(logoutSys);
		logoutSys.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 8.png"));
		ImageIcon logout = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 8.png");
		Image logoutImg = logout.getImage();
		Image logoutScale = logoutImg.getScaledInstance(logoutSys.getWidth(), logoutSys.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogout = new ImageIcon(logoutScale);
		logoutSys.setIcon(scaledLogout);
		
		JButton closeSys = new JButton("");
		closeSys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the application?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(a==0)
				{
					System.exit(0);
				}
			}
		});
		closeSys.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		closeSys.setBounds(344, 390, 100, 100);
		panel.add(closeSys);
		closeSys.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 9.png"));
		ImageIcon close = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\Artboard 9.png");
		Image closeImg = close.getImage();
		Image closeScale = closeImg.getScaledInstance(closeSys.getWidth(), closeSys.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledClose = new ImageIcon(closeScale);
		closeSys.setIcon(scaledClose);
		
		JLabel lblWelcomeTo = new JLabel("Welcome to");
		lblWelcomeTo.setForeground(new Color(255, 255, 255));
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeTo.setFont(new Font("Segoe UI Black", Font.BOLD, 54));
		lblWelcomeTo.setBounds(126, 259, 400, 69);
		getContentPane().add(lblWelcomeTo);
		
		JLabel lblNewLabel4 = new JLabel("New label");
		lblNewLabel4.setIcon(new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\home.jpg"));
		lblNewLabel4.setBounds(0, 0, 1266, 663);
		ImageIcon background = new ImageIcon("C:\\Users\\TUSHAR SOHAL\\Desktop\\Project\\EasyMod\\Assets\\home.jpg");
		Image backgroundImg = background.getImage();	
		Image backgroundScale = backgroundImg.getScaledInstance(lblNewLabel4.getWidth(), lblNewLabel4.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledBackground = new ImageIcon(backgroundScale);
		lblNewLabel4.setIcon(scaledBackground);
		getContentPane().add(lblNewLabel4);
		
		setBounds(120, 50, 1280, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
