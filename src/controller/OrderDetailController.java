package controller;

public class OrderDetailController {

	private static OrderDetailController instance;
	
	public static OrderDetailController getInstance() {
		if(instance == null) {
			synchronized (OrderDetailController.class) {
				if(instance == null) {
					instance = new OrderDetailController();
				}
			}
		}
		return instance;
	}
	
	private OrderDetailController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new OrderDetailController();
//	}
}
