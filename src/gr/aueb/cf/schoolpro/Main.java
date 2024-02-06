package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;


public class Main {
	private static Login loginForm;
	private static AdminMenu adminMenu;
	private static AdminUpdateDeleteStudents adminUpdateDeleteStudents;
	private static AdminStudentSearch adminStudentSearch;
	private static StudentInsertMenu studentInsertMenu;
	private static AdminUserSearch adminUserSearch;
	private static AdminUserInsert adminUserInsert;
	private static AdminUpdateUser adminUpdateUser;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm = new Login();
					loginForm.setVisible(true);
					
					adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
					
					adminUpdateDeleteStudents = new AdminUpdateDeleteStudents();
					adminUpdateDeleteStudents.setVisible(false);
					
					adminStudentSearch = new AdminStudentSearch();
					adminStudentSearch.setVisible(false);
					
					studentInsertMenu = new StudentInsertMenu();
					studentInsertMenu.setVisible(false);
					
					adminUserSearch = new AdminUserSearch();
					adminUserSearch.setVisible(false);
					
					adminUserInsert = new AdminUserInsert();
					adminUserInsert.setVisible(false);
					
					adminUpdateUser = new AdminUpdateUser();
					adminUpdateUser.setVisible(false);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Login getLoginForm() {
		return loginForm;
	}

	public static AdminMenu getAdminMenu() {
		return adminMenu;
	}

	public static AdminUpdateDeleteStudents getAdminUpdateDeleteStudents() {
		return adminUpdateDeleteStudents;
	}

	public static AdminStudentSearch getAdminStudentSearch() {
		return adminStudentSearch;
	}

	public static StudentInsertMenu getStudentInsertMenu() {
		return studentInsertMenu;
	}

	public static AdminUserSearch getAdminUserSearch() {
		return adminUserSearch;
	}

	public static AdminUserInsert getAdminUserInsert() {
		return adminUserInsert;
	}

	public static AdminUpdateUser getAdminUpdateUser() {
		return adminUpdateUser;
	}
	
}
