package model;

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
		this.tableName = "foods";
	}
	
	public FoodModel(Integer foodId, String name, Integer price, String description,String status) {
		super();
		this.tableName = "foods";
		this.foodId = foodId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.status=status;
	}
	
	public Vector<FoodModel> viewAll() {
		String query = "SELECT * FROM "+ this.tableName;
		ResultSet rs=con.executeQuery(query);
		
		Vector<FoodModel> foods = new Vector<>();
		try {
			while(rs.next()) {
				FoodModel food = mapModel(rs);
				foods.add(food);
			}
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public FoodModel mapModel(ResultSet rs) {
		try {
			foodId = rs.getInt("foodId");
			name = rs.getString("foodName");
			price = rs.getInt("foodPrice");
			description = rs.getString("foodDescription");
			status=rs.getString("foodStatus");
			return new FoodModel(foodId,name,price,description,status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Vector<FoodModel> viewAllForUser() {
		String query = "SELECT * FROM "+ this.tableName + " WHERE foodStatus='available'";
		ResultSet rs=con.executeQuery(query);
		
		Vector<FoodModel> foods = new Vector<>();
		try {
			while(rs.next()) {
				FoodModel food = mapModel(rs);
				foods.add(food);
			}
			return foods;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	

}
