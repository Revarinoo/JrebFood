package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;


public class OrderModel {
	
	protected String tableName;
	protected Connect con = Connect.getConnection();

	private Integer orderId;
	private Date date;
	private String address;
	private Integer userId;
	private Integer driverId;
	private String status;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderModel() {
		// TODO Auto-generated constructor stub
		this.tableName = "orders";
				
	}
	
	public boolean updateStatus(Integer orderId, String status) {
		
		
		String query = String.format("UPDATE %s SET orderStatus=? WHERE orderId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, status);
			ps.setInt(2, orderId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	
	
	public Vector<OrderModel> getOrderForChef() {
		// TODO Auto-generated method stub
		Vector<OrderModel> data = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.executeQuery(query);
		
		try {
			while (rs.next()) {
				Integer Id = rs.getInt("orderId");
				Integer driverId = rs.getInt("driverId");
				String status = rs.getString("orderStatus");
				
				OrderModel order = new OrderModel();
							
				order.setOrderId(Id);
				order.setDriverId(driverId);
				order.setStatus(status);
				
				data.add(order);
				
			}
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
