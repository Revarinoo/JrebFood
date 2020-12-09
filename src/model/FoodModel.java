package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;


public class FoodModel {
	
	protected String tableName;
	protected Connect con = Connect.getConnection();
	
	private Integer foodId;
	private String name;
	private Integer price;
	private String description;
	private String status;
	
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public FoodModel() {
		// TODO Auto-generated constructor stub
		this.tableName = "foods";
	}
	
	public boolean addFood(String name, String description, Integer price) {
		String query = String.format("INSERT INTO %s VALUES (null, ?, ?, ?, ?)", tableName) ;

		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setString(3,description);
			ps.setString(4, "Available");
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		return true;

	}

	public boolean changeStatus(Integer id, String status) {
//		String status = String.format("SELECT foodStatus FROM %s WHERE foodId = %d", tableName, id);
		
		if (status == null) {
			return false;
		}
		
		String query = String.format("UPDATE %s SET foodStatus='%s' WHERE foodId=%d", tableName, status, id);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
//			ps.setString(1, status);
//			ps.setInt(2, foodId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}

	public boolean deleteFood(Integer id) {
		String query = String.format("DELETE FROM %s WHERE foodId=%s", tableName, id);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
//			ps.setInt(1, foodId);
		
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
	}

	public Vector<FoodModel> getAll() {
		// TODO Auto-generated method stub
		Vector<FoodModel> data = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.executeQuery(query);
		
	
		
		try {
			while(rs.next()) {
				Integer id = rs.getInt("foodId");
				String name = rs.getString("foodName");
				String desc = rs.getString("foodDescription");
				Integer price = rs.getInt("foodPrice");
				String availability = rs.getString("foodStatus");
				
				FoodModel food = new FoodModel();
				food.setFoodId(id);
				food.setName(name);
				food.setDescription(desc);
				food.setPrice(price);
				food.setStatus(availability);
				
				data.add(food);
				
			}
			return data;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		return null;
	}
	
	
	

}
