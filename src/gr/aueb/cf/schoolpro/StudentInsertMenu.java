package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class StudentInsertMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField birthTxt;


	/**
	 * Create the frame.
	 */
	public StudentInsertMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Firstname");
		firstnameLbl.setForeground(new Color(255, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstnameLbl.setBounds(38, 34, 94, 22);
		contentPane.add(firstnameLbl);
		
		JLabel lastnameLbl = new JLabel("Lastname");
		lastnameLbl.setForeground(Color.RED);
		lastnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lastnameLbl.setBounds(38, 90, 94, 22);
		contentPane.add(lastnameLbl);
		
		JLabel genderLbl = new JLabel("Gender");
		genderLbl.setForeground(Color.RED);
		genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		genderLbl.setBounds(48, 146, 62, 22);
		contentPane.add(genderLbl);
		
		JRadioButton genderMRadioBtn = new JRadioButton("Male");
		genderMRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderMRadioBtn.setForeground(new Color(0, 0, 0));
		genderMRadioBtn.setBounds(142, 146, 62, 23);
		contentPane.add(genderMRadioBtn);
		
		JRadioButton genderFRadioBtn = new JRadioButton("Female");
		genderFRadioBtn.setForeground(Color.BLACK);
		genderFRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		genderFRadioBtn.setBounds(221, 146, 73, 23);
		contentPane.add(genderFRadioBtn);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(142, 35, 192, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(142, 91, 192, 20);
		contentPane.add(lastnameTxt);
		
		JLabel birthdayLbl = new JLabel("BirthDate");
		birthdayLbl.setForeground(Color.RED);
		birthdayLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		birthdayLbl.setBounds(38, 202, 94, 22);
		contentPane.add(birthdayLbl);
		
		birthTxt = new JTextField();
		birthTxt.setBounds(146, 203, 118, 20);
		contentPane.add(birthTxt);
		birthTxt.setColumns(10);
		
		JLabel cityLbl = new JLabel("City");
		cityLbl.setForeground(Color.RED);
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cityLbl.setBounds(38, 258, 37, 22);
		contentPane.add(cityLbl);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 258, 118, 22);
		contentPane.add(comboBox);
		
		JButton insertBtn = new JButton("Insert Student");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO STUDENTS (FIRSTNAME,LASTNAME,GENDER,BIRTH_DATE) VALUES (?,?,?,?)";
				try {
					Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);
					String gender = getSelectedGender(genderMRadioBtn, genderFRadioBtn);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String birthDateString = birthTxt.getText();
				    java.util.Date utilDate = dateFormat.parse(birthDateString);
				    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

					
					ps.setString(1, firstnameTxt.getText());
					ps.setString(2, lastnameTxt.getText());
					ps.setString(3, gender);
					ps.setDate(4, sqlDate);
					
					int n = ps.executeUpdate();
					
					if (n > 0) {
						JOptionPane.showMessageDialog(null, "Insert Complete", "Update", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Insert Error", "Update", JOptionPane.ERROR_MESSAGE);
					}
					
					 
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		insertBtn.setForeground(new Color(0, 0, 255));
		insertBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		insertBtn.setBounds(418, 274, 192, 31);
		contentPane.add(insertBtn);
	}
	
	private static String getSelectedGender(JRadioButton maleRadioButton, JRadioButton femaleRadioButton) {
        if (maleRadioButton.isSelected()) {
            return "M";
        } else if (femaleRadioButton.isSelected()) {
            return "F";
        } else {
            return null; // No gender selected
        }
    }
}
