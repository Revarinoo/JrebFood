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
	
	public OrderModel() {
		this.tableName="orders";
	}
	
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
	
	public Vector<OrderModel> getAll() {
		Vector<OrderModel> availableOrderList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where orderStatus=\"\"",tableName);

		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				
				availableOrderList.add(temp);
			}
			return availableOrderList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean takeOrder(Integer orderId, Integer driverId) {
		String query = String.format("UPDATE %s Set orderStatus=?,driverId=? WHERE orderId=?",tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, "accepted");
			ps.setInt(2, driverId);
			ps.setInt(3, orderId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateStatus(Integer orderId, String status) {
		String query = String.format("UPDATE orders Set orderStatus=? WHERE orderId=?",tableName);
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, status);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public OrderModel getOne(Integer orderId) {
		String query = String.format("SELECT * from %s where orderId=?", tableName);
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
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderIdTemp);
				temp.setAddress(address);
				temp.setDate(orderDate);
				
				
				return temp;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<OrderModel> viewAllHistoryForUser(Integer userId){
		Vector<OrderModel> historyList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where userId=? AND orderStatus=\"finished\"",tableName);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setInt(1, userId);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				
				historyList.add(temp);
			}
			return historyList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<OrderModel> viewAllHistoryForDriver(Integer driverId){
		Vector<OrderModel> historyList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where driverId=? AND orderStatus=\"finished\"",tableName);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		
		try {
			ps.setInt(1, driverId);
			rs = ps.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				
				historyList.add(temp);
			}
			return historyList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
