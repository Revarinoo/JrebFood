package controller;

import java.util.Vector;


import core.view.View;
import model.FoodModel;
import model.FoodModel;
import view.FoodMenuView;


public class FoodController {
	
	private static FoodController instance=null;
	
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
	}

	public void addFood(String name, String desc, Integer price) {
		FoodModel food = new FoodModel();
		food.setName(name);
		food.setDescription(desc);
		food.setPrice(price);
		
		
		food.addFood(name, desc, price);
	}
	
	public boolean deleteFood(Integer id) {
		
		FoodModel food = new FoodModel();
		food.setFoodId(id);
		food.deleteFood(id);
		
		if (food.deleteFood(id) == false) {
			return false;
		}
		
		return true;
		
	}
	
	public boolean changeStatus(Integer id, String status) {
		FoodModel food = new FoodModel();
		
		food.setFoodId(id);
		food.changeStatus(id, status);
		
		if (food.changeStatus(id, status) == false) {
			return false;
		}
		return true;
		
	}

	public View viewManageFoodForm(View target) {
		return target;
	}

	public Vector<FoodModel> viewAll() {
		FoodModel food = new FoodModel();
		return food.viewAll();
	}
	
	public View viewMenu() {
		return new FoodMenuView();
	}

	public FoodModel getFood(Integer foodId) {
		FoodModel food = new FoodModel();
		return food.getFood(foodId);
	}
	
	public boolean checkStatus(FoodModel food) {
		if(food.getStatus().equals("unavailable"))return false;
		return true;
	}
}
