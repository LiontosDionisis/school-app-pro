package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JTextField usernameRegTxt;
	private JPasswordField passwordRegTxt;
	private JPasswordField passwordReg2Txt;
	private ResultSet rs1;

	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel login = new JPanel();
		tabbedPane.addTab("Login", null, login, null);
		login.setLayout(null);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(0, 0, 0));
		usernameLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		usernameLbl.setBounds(88, 61, 78, 25);
		login.add(usernameLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setForeground(Color.BLACK);
		passwordLbl.setFont(new Font("SansSerif", Font.BOLD, 15));
		passwordLbl.setBounds(88, 119, 78, 31);
		login.add(passwordLbl);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(176, 63, 141, 20);
		login.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginUsername = usernameTxt.getText();
				String loginPassword = String.valueOf(passwordTxt.getPassword()).trim();
				
				if (loginUsername.matches("[aA]dmin")) {
					if (loginPassword.equals("123456")) {
						Main.getAdminMenu().setVisible(true);
						Main.getLoginForm().setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong username/password. Check again" , "Error" , JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					String user = getUser(loginUsername, loginPassword);
					if (user == null) {
						JOptionPane.showMessageDialog(null, "Wrong username or Password", "Error", JOptionPane.INFORMATION_MESSAGE);
						return;
					} 
					if (user == "Teacher") {
						return;
					}
					if (user == "Student") {
						return;
					}
				}
				
				
			}
		});
		loginBtn.setForeground(new Color(0, 0, 255));
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginBtn.setBounds(292, 166, 103, 31);
		login.add(loginBtn);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(176, 124, 141, 20);
		login.add(passwordTxt);
		
		JPanel register = new JPanel();
		tabbedPane.addTab("Register", null, register, null);
		register.setLayout(null);
		
		JComboBox roleComboBox = new JComboBox();
		roleComboBox.setModel(new DefaultComboBoxModel(RoleType.values()));
		roleComboBox.setBounds(177, 33, 138, 22);
		register.add(roleComboBox);
		
		JLabel roleLbl = new JLabel("Role");
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		roleLbl.setBounds(132, 27, 56, 34);
		register.add(roleLbl);
		
		JLabel usernameRegLbl = new JLabel("Username");
		usernameRegLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameRegLbl.setBounds(91, 72, 97, 34);
		register.add(usernameRegLbl);
		
		JLabel passwordRegLbl = new JLabel("Password");
		passwordRegLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordRegLbl.setBounds(94, 117, 73, 34);
		register.add(passwordRegLbl);
		
		JLabel confPassRegLbl = new JLabel("Confirm Password");
		confPassRegLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		confPassRegLbl.setBounds(31, 162, 157, 44);
		register.add(confPassRegLbl);
		
		usernameRegTxt = new JTextField();
		usernameRegTxt.setBounds(177, 79, 138, 20);
		register.add(usernameRegTxt);
		usernameRegTxt.setColumns(10);
		
		passwordRegTxt = new JPasswordField();
		passwordRegTxt.setBounds(177, 124, 138, 20);
		register.add(passwordRegTxt);
		
		passwordReg2Txt = new JPasswordField();
		passwordReg2Txt.setBounds(177, 170, 138, 22);
		register.add(passwordReg2Txt);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameRegTxt.getText().trim();
				String password1 = String.valueOf(passwordRegTxt.getPassword()).trim();
				String password2 = String.valueOf(passwordReg2Txt.getPassword()).trim();
				String role = roleComboBox.getSelectedItem().toString();
				
				if (username == "" || password1 == "" || password2 == "") {
					JOptionPane.showMessageDialog(null, "Please fill username / password", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
	
				
				if (!password1.equals(password2)) {
					JOptionPane.showMessageDialog(null, "Passwords don't match", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
			
				String sql = "INSERT INTO USERS (USERNAME, PASSWORD, ROLE) VALUES(?, ?, ? )";
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql)) {		 
					ps.setString(1, username);
					ps.setString(2, SecUtil.hashPassword(password1));
					ps.setString(3, role);
					ps.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		registerBtn.setBackground(new Color(240, 240, 240));
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		registerBtn.setForeground(new Color(0, 0, 255));
		registerBtn.setBounds(255, 203, 89, 23);
		register.add(registerBtn);
	}
	
	private String getUser(String username, String password) {
		PreparedStatement ps = null;
		String user = null;
		ResultSet rs;
		
		try (Connection connection = DBUtil.getConnection()) {
			String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
				ps = connection.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if (!rs.next()) return null;
				String inputPassword = rs.getString("PASSWORD");
				user = rs.getString("ROLE");
				if (!SecUtil.checkPassword(password, inputPassword)) {
					return null;
				}
				
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return user;
	}
}
