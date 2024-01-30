package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;


public class Main {
	private static Login loginForm;
	private static AdminMenu adminMenu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login loginForm = new Login();
					loginForm.setVisible(true);
					
					AdminMenu adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Login getLoginForm() {
		if (loginForm == null) {
			loginForm = new Login();
		}
		return loginForm;
	}

	public static AdminMenu getAdminMenu() {
		if (adminMenu == null) {
			adminMenu = new AdminMenu();
		}
		return adminMenu;
	}
	
}
