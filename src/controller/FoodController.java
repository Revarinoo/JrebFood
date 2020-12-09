package controller;

import java.util.Vector;

import model.FoodModel;
import view.FoodMenuView;

public class FoodController {
	
	private static FoodController instance=null;
	private FoodModel foodModel;
	
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
		foodModel = new FoodModel();
	}
	
	public Vector<FoodModel> viewAll(){
		return foodModel.viewAll();
	}
	
	public FoodMenuView showUserFoodMenu() {
		return new FoodMenuView();
	}
}
