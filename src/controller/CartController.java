package controller;

import java.util.Vector;

import core.view.View;
import model.CartModel;
import model.FoodModel;
import view.CartView;
import view.MainFormView;

public class CartController {

	private static CartController instance;
	private String errorMsg;
	
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
		this.errorMsg = "";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public Vector<CartModel> viewAll(Integer userId){
		CartModel cart = new CartModel();
		
		Vector<CartModel> carts = cart.viewAll(userId);
		for (CartModel uCart : carts) {
			FoodModel food = FoodController.getInstance().getFood(uCart.getFoodId());
			if(!FoodController.getInstance().checkStatus(food)) {
				uCart.removeFromCart(userId, food.getFoodId());
			}
		}
	
		return cart.viewAll(userId);
	}
	
	public View viewManageCartForm() {
		return new CartView();
	}
	
	public boolean addToCart(Integer userId,Integer foodId,Integer qty) {
		CartModel cart = new CartModel();
		FoodModel food = FoodController.getInstance().getFood(foodId);
		boolean foodStatus = FoodController.getInstance().checkStatus(food);
		
		if(qty<1 || qty==null) {
			errorMsg = "Quantity must be at least 1";
			return false;
		}else if(!foodStatus) {
			errorMsg = "Food is unavailable at the moment, please pick another option";
			return false;
		}
		
		if(isFoodExist(food.getName())) {
			Vector<CartModel> carts = viewAll(userId);
			
			for (CartModel uCart : carts) {
				if(uCart.getFoodId()==foodId) {
					Integer totalQty = qty + uCart.getQty();
					return cart.updateQty(userId, foodId, totalQty);
				}
			}	
		}
		boolean insertStatus = cart.addToCart(userId, foodId, qty);
		if(!insertStatus) {
			errorMsg = "Add To Cart Failed";
		}
		return insertStatus;
	}

	public boolean isFoodExist(String foodName) {
		Integer userId=MainFormView.userID;
		Vector<CartModel> carts = viewAll(userId);
		
		for (CartModel cart : carts) {
			FoodModel food = FoodController.getInstance().getFood(cart.getFoodId());
			if(food.getName().equals(foodName))return true;
		}	
		return false;	
	}
	
	public boolean updateQty(Integer userId,Integer foodId,Integer qty) {
		CartModel cart = new CartModel();
		
		if(qty<1 || qty==null) {
			errorMsg = "Quantity must be at least 1";
			return false;
		}
	
		boolean updateStatus = cart.updateQty(userId, foodId, qty);
		if(!updateStatus) {
			errorMsg = "Update Cart Failed";
		}
		return updateStatus;
	}
	
	public boolean removeFromCart(Integer userId,Integer foodId) {
		CartModel cart = new CartModel();
		
		if(foodId<1) {
			errorMsg = "You Must Select Food to Remove";
			return false;
		}
		boolean deleteStatus = cart.removeFromCart(userId, foodId);
		if(!deleteStatus) {
			errorMsg = "Delete Cart Failed";
		}
		return deleteStatus;
	}
	
	public void removeAll(Integer userId) {
		CartModel cart = new CartModel();
		cart.removeAll(userId);
	}

}