package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton studentsBtn = new JButton("");
		studentsBtn.setBounds(24, 41, 53, 34);
		contentPane.add(studentsBtn);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(24, 116, 53, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(24, 191, 53, 34);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(24, 266, 53, 34);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(24, 341, 53, 34);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(24, 416, 53, 34);
		contentPane.add(btnNewButton_5);
		
		JLabel studentsLbl = new JLabel("Students");
		studentsLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		studentsLbl.setBounds(111, 41, 83, 14);
		contentPane.add(studentsLbl);
		
		JLabel lblTeachers = new JLabel("Teachers");
		lblTeachers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTeachers.setBounds(111, 116, 94, 14);
		contentPane.add(lblTeachers);
		
		JLabel lblMeetings = new JLabel("Meetings");
		lblMeetings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMeetings.setBounds(111, 191, 94, 22);
		contentPane.add(lblMeetings);
		
		JLabel lblDummytext = new JLabel("dummytext");
		lblDummytext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDummytext.setBounds(111, 269, 94, 31);
		contentPane.add(lblDummytext);
		
		JLabel lblDummytext_1 = new JLabel("dummytext");
		lblDummytext_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDummytext_1.setBounds(111, 341, 94, 22);
		contentPane.add(lblDummytext_1);
		
		JLabel lblDummytext_2 = new JLabel("dummytext");
		lblDummytext_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDummytext_2.setBounds(111, 416, 94, 22);
		contentPane.add(lblDummytext_2);
	}
}
