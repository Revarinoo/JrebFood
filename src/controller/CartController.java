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
	
	/* Penjelasan fungsi viewAll() 
	 	Ini merupakan fungsi untuk menampilkan makanan yang ada dalam cart(keranjang) customer. 
	 	Fungsi ini dijalankan pada class CartView.java
	 	Fungsi ini menerima sebuah parameter yaitu (userId) dan mengembalikan(return) sebuah Vector dari CartModel(Cart).
	 	
	 	Fungsi ini akan mengambil seluruh data dari database melalui CartModel, kemudian data yang sudah diambil dari database
	 	akan diperiksa kembali dan ada makanan yang statusnya tiba-tiba menjadi "unavailable" maka makanan akan dihapus dari cart.
	 */
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
	
	
	/* Penjelasan fungsi addToCart() 
	   	 	Ini merupakan fungsi untuk menambahkan makanan ke dalam cart(keranjang) customer. 
	   	 	Fungsi ini dijalankan pada class FoodMenuView.java
	   	 	Fungsi ini menerima 3 buah parameter yaitu (userId,foodId dan quantity) dan mengembalikan(return) tipe data boolean.
	   	 	
	   	 	Pertama fungsi ini akan melakukan pada validasi input pada jumlah makanan yang ingin dimasukan ke cart,
	   	 	lalu akan dilanjutkan dengan validasi status dari makanan(apabila status makanan unavailable maka customer
	   	 	tidak dapat menambahnya ke dalam cart.
	   	 	
	   	 	Kemudian proses dilanjukan dengan memeriksa apakah makanan yang dipilih sudah ada sebelumnya dalam cart,
	   	 	apabila makanan sudah ada dalam cart maka hanya quantity dari makanan tersebut yang akan ditambahkan dan 
	   	 	apabila makanan belum ada dalam cart maka data makanan tersebut akan ditambahkan ke dalam cart.	
	*/
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
	
	/* Penjelasan fungsi removeAll() 
	 	Ini merupakan fungsi untuk menghapus seluruh makanan yang ada dalam cart(keranjang) customer. 
	 	Fungsi ini dijalankan untuk proses Checkout(order) pada CartView.java
	 	Fungsi ini menerima sebuah parameter yaitu (userId).
	 	
	 	Fungsi ini bertugas untuk menghapus seluruh makanan yag ada dalam cart user setelah melakukan 
	 	chekout terhadap makanan yang dipesan
	 */
	public void removeAll(Integer userId) {
		CartModel cart = new CartModel();
		cart.removeAll(userId);
	}

}