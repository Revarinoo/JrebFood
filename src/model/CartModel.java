package model;

import connect.Connect;

public class CartModel {

	protected String tableName;
	protected Connect con = Connect.getConnection();
	
	private Integer userId;
	private Integer foodId;
	private Integer qty;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
