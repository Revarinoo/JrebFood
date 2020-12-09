package controller;

public class ChefController {

	private static ChefController instance;
	
	public static ChefController getInstance() {
		if(instance == null) {
			synchronized (ChefController.class) {
				if(instance == null) {
					instance = new ChefController();
				}
			}
		}
		return instance;
	}
	
	private ChefController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new ChefController();
//	}
}
