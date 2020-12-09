package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connect.Connect;

public class UserModel {

	protected String tableName;
	protected Connect con = Connect.getConnection();
	
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public UserModel getOne(Integer userId) {
		String query = String.format("SELECT * from %s where userId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setInt(1, userId);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()) {
				Integer userIdTemp = rs.getInt("userId");
				String name = rs.getString("userName");
				String address = rs.getString("userAddress");
				String phoneNumber = rs.getString("userPhone");
				
				UserModel user = new UserModel();
				user.setUserId(userIdTemp);
				user.setName(name);
				user.setAddress(address);
				user.setPhoneNumber(phoneNumber);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
