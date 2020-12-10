package controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import core.view.View;
import model.EmployeeModel;
import model.UserModel;
import view.ManageEmployeeView;

public class EmployeeController {

	private static EmployeeController instance;
	
	public static EmployeeController getInstance() {
		if(instance == null) {
			synchronized (EmployeeController.class) {
				if(instance == null) {
					instance = new EmployeeController();
				}
			}
		}
		return instance;
	}
	
	private EmployeeController() {
		// TODO Auto-generated constructor stub
	}
	
	public View ManageEmployeeForm() {
		return new ManageEmployeeView();
	}
	
	public Vector<EmployeeModel> viewAll(Integer roleId){
		EmployeeModel employee = new EmployeeModel();
		return employee.viewAll(roleId);
	}
	
	public boolean createEmployee(Integer roleId, String name, Date DOB, String email, String password, String status) {
		try {
			EmployeeModel employee = new EmployeeModel();
			employee.setRoleId(roleId);
			if(!validateFields(name, DOB, email)) return false;
			employee.setName(name);
			employee.setDOB(DOB);
			employee.setEmail(email);
			employee.setPassword(password);
			employee.setStatus(status);
			if(!employee.createEmployee()) return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean validateFields(String name, Date DOB, String email) {
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		if(name.equals("")) return false;
		if(DOB == null || DOB.after(today)) return false;
		if(email.equals("") || !email.contains("@") || !email.contains("."))return false;
		return true;
	}
	
	public boolean changeStatus(Integer id) {
		EmployeeModel employee = new EmployeeModel();
		employee.setId(id);
		if(!employee.changeStatus()) return false;
		return true;
	}
}
