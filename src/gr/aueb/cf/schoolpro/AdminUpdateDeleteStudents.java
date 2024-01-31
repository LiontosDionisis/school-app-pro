package gr.aueb.cf.schoolpro;


import javax.swing.JFrame;
import javax.swing.JPanel;


import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminUpdateDeleteStudents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField birthTxt;
	private JTextField genderTxt;
	private JComboBox<String> cityComboBox;
	private ResultSet rs;
	private PreparedStatement ps;
	private Connection connection;

	/**
	 * Create the frame.
	 */
	public AdminUpdateDeleteStudents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
				try {
					connection = DBUtil.getConnection();
					ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getAdminStudentSearch().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						genderTxt.setText(rs.getString("GENDER"));
						java.sql.Date sqlDate = rs.getDate("BIRTH_DATE");
						birthTxt.setText(sqlDate.toString());
					} 
					else if (!rs.next()) {
						JOptionPane.showMessageDialog(null,  "No Students found", "Teachers", JOptionPane.WARNING_MESSAGE);
						Main.getAdminStudentSearch().setEnabled(true);
						Main.getAdminUpdateDeleteStudents().setVisible(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();	
				} 
			
					
//				String sqlCity = "SELECT * FROM CITIES";
//				try {
//				    PreparedStatement psCity = connection.prepareStatement(sqlCity);
//				    ResultSet rsCity = psCity.executeQuery();
//
//				    HashMap<Integer, String> cities = new HashMap<>();
//				    DefaultComboBoxModel<String> citiesModel = new DefaultComboBoxModel<>();
//
//				    // Iterate through all rows in the result set
//				    while (rsCity.next()) {
//				        String city = rsCity.getString("CITY");
//				        int cityId = rsCity.getInt("ID");
//
//				        cities.put(cityId, city);
//				        citiesModel.addElement(city);
//				    }
//
//				    // Set the model after processing all rows
//				    cityComboBox.setModel(citiesModel);		
//				} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
				
		}});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idLbl.setBounds(60, 49, 19, 14);
		contentPane.add(idLbl);
		
		JLabel firstnameLbl = new JLabel("Firstname");
		firstnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstnameLbl.setBounds(10, 108, 69, 20);
		contentPane.add(firstnameLbl);
		
		JLabel lastnameLbl = new JLabel("Lastname");
		lastnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastnameLbl.setBounds(10, 175, 69, 20);
		contentPane.add(lastnameLbl);
		
		JLabel genderLbl = new JLabel("Gender");
		genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		genderLbl.setBounds(10, 309, 58, 20);
		contentPane.add(genderLbl);
		
		JLabel cityLbl = new JLabel("City");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cityLbl.setBounds(35, 376, 27, 20);
		contentPane.add(cityLbl);
		
		JLabel birthLbl = new JLabel("Birthdate");
		birthLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		birthLbl.setBounds(10, 244, 69, 20);
		contentPane.add(birthLbl);
		
		JButton btnNewButton = new JButton("Prev");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
				try {
					if (rs.first()) {
						idTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						genderTxt.setText(rs.getString("GENDER"));
						java.sql.Date sqlDate = rs.getDate("BIRTH_DATE");
						birthTxt.setText(sqlDate.toString());
					} 
				} catch (SQLException e1) {
					e1.printStackTrace();	
				}
		}});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(11, 414, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
				try {
					
					if (rs.next()) {
						idTxt.setText(rs.getString("ID"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						genderTxt.setText(rs.getString("GENDER"));
						java.sql.Date sqlDate = rs.getDate("BIRTH_DATE");
						birthTxt.setText(sqlDate.toString());
					}
					else rs.last();
					
				} catch (SQLException e1) {
					e1.printStackTrace();	
				}
		}});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNext.setBounds(111, 414, 89, 23);
		contentPane.add(btnNext);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "UPDATE STUDENTS SET FIRSTNAME = ? , LASTNAME = ?, GENDER = ?, BIRTH_DATE = ? WHERE ID = ?";
				
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					String firstname = firstnameTxt.getText().trim();
					String lastname = lastnameTxt.getText().trim();
					String gender = genderTxt.getText().trim().toUpperCase();
					String currentBirthday = birthTxt.getText().trim();
					String updatedBirthday = currentBirthday;
					
					currentBirthday.equals(updatedBirthday);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date utilDate = dateFormat.parse(updatedBirthday);
					java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					ps.setString(1, firstname);
					ps.setString(2, lastname);
					ps.setString(3, gender);
					ps.setDate(4, sqlDate);
					ps.setString(5, idTxt.getText());
					
					int n = ps.executeUpdate();
					
					if (n > 0) {
						JOptionPane.showMessageDialog(null, "Successful Update", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Update Error", "Update", JOptionPane.ERROR_MESSAGE);
					}
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(211, 414, 88, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM STUDENTS WHERE ID = ?";
				
				try {
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, idTxt.getText());
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
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
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(310, 415, 89, 23);
		contentPane.add(btnDelete);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBackground(new Color(255, 255, 170));
		idTxt.setBounds(119, 46, 58, 20);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(119, 108, 149, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(119, 175, 149, 20);
		contentPane.add(lastnameTxt);
		
		birthTxt = new JTextField();
		birthTxt.setBounds(119, 244, 86, 20);
		contentPane.add(birthTxt);
		birthTxt.setColumns(10);
		
		genderTxt = new JTextField(1);
		genderTxt.setBounds(119, 311, 34, 20);
		contentPane.add(genderTxt);
		genderTxt.setColumns(10);
		
		JComboBox cityComboBox = new JComboBox();
		cityComboBox.setBounds(119, 376, 149, 22);
		contentPane.add(cityComboBox);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUpdateDeleteStudents().setVisible(false);
				Main.getAdminStudentSearch().setEnabled(true);
				try {
					rs.close();
					ps.close();
					connection.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				
				
			}
		});
		closeBtn.setBounds(410, 415, 69, 23);
		contentPane.add(closeBtn);
	}
}
