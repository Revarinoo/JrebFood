package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
	
	public CartModel() {
		this.tableName = "carts";
	}
	
	public Vector<CartModel> viewAll(Integer userId) {
		Vector<CartModel> carts = new Vector<>();
		String query = String.format("SELECT * FROM %s WHERE userId=%s", tableName,userId.toString());
		ResultSet rs = con.executeQuery(query);
			
		try {
			while(rs.next()) {
				Integer userid = rs.getInt("userId");
				Integer foodId = rs.getInt("foodId");
				Integer quantity = rs.getInt("quantity");
				
				CartModel cart = new CartModel();
				cart.setUserId(userid);
				cart.setFoodId(foodId);
				cart.setQty(quantity);
				
				carts.add(cart);		
			}
			return carts;
		} catch (SQLException e) {
			
		}	
		return null;
	}
	
	public boolean addToCart(Integer userId,Integer foodId,Integer qty) {
		String query = String.format("INSERT INTO %s VALUES (?, ?, ?)", this.tableName) ;
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, foodId);
			ps.setInt(3, qty);		
			return ps.executeUpdate()==1;		
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	public boolean updateQty(Integer userId,Integer foodId,Integer qty) {
		String query = String.format("UPDATE %s SET quantity = ? WHERE userId = ? AND foodId = ?", this.tableName) ;
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, qty);
			ps.setInt(2, userId);
			ps.setInt(3, foodId);		
			return ps.executeUpdate()==1;		
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	public boolean removeFromCart(Integer userId,Integer foodId) {
		String query = String.format("DELETE FROM %s WHERE userId = ? AND foodId = ?", this.tableName) ;
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, foodId);		
			return ps.executeUpdate()==1;		
		} catch (SQLException e) {
			
		}
		return false;
	}
	
	public void removeAll(Integer userId) {
		String query = String.format("DELETE FROM %s WHERE userId = ?", this.tableName) ;
		PreparedStatement ps = con.prepareStatement(query);		
		
		try {
			ps.setInt(1, userId);		
			ps.executeUpdate();		
		} catch (SQLException e) {
			
		}
	}
}
