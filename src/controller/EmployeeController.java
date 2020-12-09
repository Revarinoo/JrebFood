package controller;

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
	
//	public View view() {
//		return new EmployeeController();
//	}
}
