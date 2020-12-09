package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public Vector<OrderDetailModel> viewDetailById(Integer orderId){
		Vector<OrderDetailModel> listDetail = new Vector<OrderDetailModel>();
		String query = String.format("SELECT * From order_details WHERE orderId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setInt(1, orderId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				Integer orderIdTemp = rs.getInt("orderId");
				Integer foodId = rs.getInt("foodId");
				Integer qty = rs.getInt("quantity");
				
				OrderDetailModel detail = new OrderDetailModel();
				detail.setOrderId(orderIdTemp);
				detail.setFoodId(foodId);
				detail.setQty(qty);
				
				listDetail.add(detail);
			}
			return listDetail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
