package controller;

import core.view.View;

public class CartController {

	private static CartController instance;
	
	public static CartController getInstance() {
		if(instance == null) {
			synchronized (CartController.class) {
				if(instance == null) {
					instance = new CartController();
				}
			}
		}
		return instance;
	}
	
	private CartController() {
		// TODO Auto-generated constructor stub
	}
	
//	public View view() {
//		return new CartView();
//	}

}
