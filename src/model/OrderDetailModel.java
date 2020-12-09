package model;

import connect.Connect;

public class OrderDetailModel {
	
	protected String tableName;
	protected Connect con = Connect.getConnection();
	
	private Integer orderId;
	private Integer foodId;
	private Integer qty;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
