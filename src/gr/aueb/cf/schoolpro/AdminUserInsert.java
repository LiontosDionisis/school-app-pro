package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminUserInsert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private JTextField roleTxt;
	private SecUtil secUtil;



	/**
	 * Create the frame.
	 */
	public AdminUserInsert() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				usernameTxt.setText("");
				passwordTxt.setText("");
				roleTxt.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(32, 65, 70, 28);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(32, 130, 70, 28);
		contentPane.add(lblPassword);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRole.setBounds(32, 195, 32, 28);
		contentPane.add(lblRole);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(152, 71, 165, 20);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(152, 136, 165, 20);
		contentPane.add(passwordTxt);
		
		roleTxt = new JTextField();
		roleTxt.setColumns(10);
		roleTxt.setBounds(152, 201, 86, 20);
		contentPane.add(roleTxt);
		
		JButton insertBtn = new JButton("INSERT");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO USERS (USERNAME,PASSWORD,ROLE) VALUES (?,?,?)";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.setString(1,usernameTxt.getText());
					ps.setString(2, secUtil.hashPassword(passwordTxt.getText()));
					ps.setString(3, roleTxt.getText());
					
					
					
					if (usernameTxt.getText().equals("") || passwordTxt.getText().equals("") || roleTxt.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "All information is required for the insert", "INSERT ERROR", JOptionPane.INFORMATION_MESSAGE );
						return;
					} else {
						int n = ps.executeUpdate();
						if (n >= 1) {
							JOptionPane.showMessageDialog(null,  n + " rows affected", "INSERT", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					
					
					
					
					
					
					
					
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		insertBtn.setBounds(152, 300, 89, 36);
		contentPane.add(insertBtn);
	}

}
