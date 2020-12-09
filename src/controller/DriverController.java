package controller;

public class DriverController {

	private static DriverController instance;
	
	public static DriverController getInstance() {
		if(instance == null) {
			synchronized (DriverController.class) {
				if(instance == null) {
					instance = new DriverController();
				}
			}
		}
		return instance;
	}
	
	private DriverController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new DriverController();
//	}
}
