package controller;

public class FoodController {
	
	private static FoodController instance;
	
	public static FoodController getInstance() {
		if(instance == null) {
			synchronized (FoodController.class) {
				if(instance == null) {
					instance = new FoodController();
				}
			}
		}
		return instance;
	}
	
	private FoodController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new FoodController();
//	}
}
