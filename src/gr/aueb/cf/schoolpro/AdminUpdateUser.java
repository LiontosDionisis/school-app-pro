package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUpdateUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField usernameTxt;
	private JTextField passwordTxt;
	private JTextField roleTxt;
	private JLabel lblNewLabel;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRole;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JButton btnClose;
	private SecUtil secUtil;

	

	/**
	 * Create the frame.
	 */
	public AdminUpdateUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
				String username = Main.getAdminUserSearch().getUsername();
				try {
					Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, username + "%");
					ResultSet rs = ps.executeQuery();
					boolean userFound = rs.next();
					
					if (userFound) {
						idTxt.setText(rs.getString("ID"));
						usernameTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
					}
					else  {
						JOptionPane.showMessageDialog(null,  "No Users found", "Users", JOptionPane.WARNING_MESSAGE);
						Main.getAdminUserSearch().setEnabled(true);
						Main.getAdminUpdateUser().setVisible(false);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
						
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBounds(143, 62, 46, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(143, 148, 128, 20);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		passwordTxt = new JTextField();
		passwordTxt.setBounds(143, 234, 128, 20);
		contentPane.add(passwordTxt);
		passwordTxt.setColumns(10);
		
		roleTxt = new JTextField();
		roleTxt.setBounds(143, 320, 128, 20);
		contentPane.add(roleTxt);
		roleTxt.setColumns(10);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(81, 58, 26, 28);
		contentPane.add(lblNewLabel);
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(37, 144, 70, 28);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(37, 230, 70, 28);
		contentPane.add(lblPassword);
		
		lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRole.setBounds(75, 314, 32, 28);
		contentPane.add(lblRole);
		
		btnNewButton = new JButton("Update");
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, ROLE = ? WHERE ID = ?";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql)) {
					
					ps.setString(1, usernameTxt.getText());
					ps.setString(2, secUtil.hashPassword(passwordTxt.getText()));
					ps.setString(3, roleTxt.getText());
					ps.setString(4, idTxt.getText());
					
					int n = ps.executeUpdate();
					
					if (n >= 1) {
						JOptionPane.showMessageDialog(null, n + " rows affected", "UPDATE", JOptionPane.INFORMATION_MESSAGE);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(118, 367, 89, 36);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM USERS WHERE USERNAME = ?";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.setString(1, usernameTxt.getText());
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "DELETE Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, n + " rows affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} else return;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			}
		});
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(217, 367, 89, 36);
		contentPane.add(btnDelete);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUpdateUser().setVisible(false);
				Main.getAdminUserSearch().setEnabled(true);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClose.setBounds(385, 367, 89, 36);
		contentPane.add(btnClose);
	}

}
