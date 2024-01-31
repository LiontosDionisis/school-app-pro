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

public class AdminStudentSearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lastnameTxt;
	private String lastname;


	/**
	 * Create the frame.
	 */
	public AdminStudentSearch() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lastnameTxt.setText("");
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lastnameLbl = new JLabel("Lastname");
		lastnameLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastnameLbl.setBounds(153, 70, 87, 25);
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(102, 106, 203, 33);
		contentPane.add(lastnameTxt);
		lastnameTxt.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastname = lastnameTxt.getText().trim();
				Main.getAdminUpdateDeleteStudents().setVisible(true);
				Main.getAdminStudentSearch().setEnabled(false);
			}
		});
		searchBtn.setForeground(new Color(0, 0, 255));
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchBtn.setBounds(153, 158, 109, 33);
		contentPane.add(searchBtn);
	}


	public String getLastname() {
		return lastname;
	}


	

}
