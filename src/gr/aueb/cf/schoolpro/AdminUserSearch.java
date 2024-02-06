package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminUserSearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxt;
	public String username;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public AdminUserSearch() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				userTxt.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLb = new JLabel("Username");
		userLb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLb.setBounds(164, 47, 92, 22);
		contentPane.add(userLb);
		
		userTxt = new JTextField();
		userTxt.setBounds(118, 80, 185, 32);
		contentPane.add(userTxt);
		userTxt.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = userTxt.getText().trim();
				Main.getAdminUserSearch().setEnabled(false);
				Main.getAdminUpdateUser().setVisible(true);
				
			}
		});
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setBounds(164, 123, 89, 23);
		contentPane.add(searchBtn);
		
		JButton insertUserBtn = new JButton("Insert a user");
		insertUserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUserSearch().setEnabled(false);
				Main.getAdminUserInsert().setVisible(true);
			}
		});
		insertUserBtn.setForeground(new Color(255, 0, 0));
		insertUserBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		insertUserBtn.setBounds(131, 218, 158, 32);
		contentPane.add(insertUserBtn);
		
		JButton closeBtn = new JButton("-->");
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUserSearch().setVisible(false);
				Main.getAdminMenu().setEnabled(true);
			}
		});
		closeBtn.setBounds(335, 225, 89, 23);
		contentPane.add(closeBtn);
	}

	public String getUsername() {
		return userTxt.getText();
	}
}
