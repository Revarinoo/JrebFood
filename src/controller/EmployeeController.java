package controller;

import java.util.Vector;

import core.view.View;
import model.EmployeeModel;
import model.UserModel;

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
	
	public View view(View target) {
		return target;
	}
	
	public Vector<EmployeeModel> viewAll(Integer roleId){
		EmployeeModel employee = new EmployeeModel();
		return employee.viewAll(roleId);
	}
}
