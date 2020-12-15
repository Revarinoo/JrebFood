package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class OrderDetailModel {
	
	private String tableName;
	private Connect con = Connect.getConnection();
	
	private Integer orderId;
	private Integer foodId;
	private Integer qty;
	
	
	public OrderDetailModel() {
		this.tableName = "order_details";
	}
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
	

	// untuk mendapatkan nilai total transaksi dari semua driver
	public Integer totalFinishedTransaction() {
		Integer total = 0;
		String query = String.format("SELECT SUM(f.foodPrice) AS total FROM orders o JOIN order_details od  ON o.orderId = od.orderId JOIN foods f ON od.foodId = f.foodId WHERE o.orderStatus = 'finished'");
		ResultSet rs = con.executeQuery(query);
		try {
			while(rs.next()) {
				total = rs.getInt("total");
			}
			return total;
		} catch (SQLException e) {
			
			return 0;
		}
		
	}
	public boolean deleteDetailByOrder(Integer orderId) {
		String query = String.format("DELETE FROM %s WHERE orderId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, orderId);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void addOrderDetail(Integer orderId,Integer foodId,Integer qty) {
		String query = String.format("INSERT INTO %s VALUES (?, ?, ?)", tableName) ;
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, orderId);
			ps.setInt(2, foodId);
			ps.setInt(3, qty);		
			ps.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
