package controller;

public class RoleController {

	private static RoleController instance;
	
	public static RoleController getInstance() {
		if(instance == null) {
			synchronized (RoleController.class) {
				if(instance == null) {
					instance = new RoleController();
				}
			}
		}
		return instance;
	}
	
	private RoleController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new RoleController();
//	}
}
