package controller;

public class OrderController {

	private static OrderController instance;
	
	public static OrderController getInstance() {
		if(instance == null) {
			synchronized (OrderController.class) {
				if(instance == null) {
					instance = new OrderController();
				}
			}
		}
		return instance;
	}
	
	private OrderController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new OrderController();
//	}
}
