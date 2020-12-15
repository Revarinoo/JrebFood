package controller;

import java.util.Vector;


import core.view.View;
import model.FoodModel;
import model.FoodModel;
import view.ManageFoodView;
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

	/*
	 * Method ini bekerja saat Chef ingin menambah makanan dalam menu.
	 * Pertama, memanggil model untuk menyimpan data nama, desc, dan price
	 * dari hasil input pada View. Setelah itu, akan dijalankan method addFood pada model
	 * agar model dapat memasukkan data yang telah dioper ke dalam database
	 * */
	public boolean addFood(String name, String desc, Integer price) {
		FoodModel food = new FoodModel();
		
		if (!validateInput(name, desc, price)) {
			return false;
		}
		
		food.setName(name);
		food.setDescription(desc);
		food.setPrice(price);
		

		if(!food.addFood(name, desc, price)) return false;
		
		return true;
	}
	
	/*
	 * Merupakan validasi yang dijalankan saat ingin menambah makanan untuk
	 * memastikan bahwa Chef tidak mengkosongkan data yang akan dimasukkan, seperti 
	 * nama, desc, dan price.
	 * */
	private boolean validateInput(String name, String desc, Integer price) {
		
		if(name.isEmpty() || desc.isEmpty() || (price == 0 || price == null)) {
			return false;
		}
		return true;
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
	
	/*
	 *Function yang bekerja saat Chef ingin mengganti status dari Makanan (menjadi available/unavailable)
	 *Nantinya, function ini akan memanggil model untuk menyimpan data dan mengubah data yang sudah ada di database 
	 **/
	public boolean changeStatus(Integer id, String status) {
		FoodModel food = new FoodModel();
		
		food.setFoodId(id);
		food.changeStatus(id, status);
		
		if (food.changeStatus(id, status) == false) {
			return false;
		}
		return true;
		
	}

	public View viewManageFoodForm() {
		// TODO Auto-generated method stub
		return new ManageFoodView();
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
