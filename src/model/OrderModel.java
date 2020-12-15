package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		
		String query = String.format("SELECT * FROM %s WHERE NOT(orderStatus='finished')", tableName);
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
	
	
	
	public Vector<OrderModel> getAll() {
		/*
		 * function ini berfungsi untuk model mengambil data order yang belum di ambil oleh driver,
		 * dengan kata lain mengambil order dengan status not accepted untuk ditampilkan pada
		 * halaman available order
		 */
		Vector<OrderModel> availableOrderList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where orderStatus='not accepted'",tableName);

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
	
	public Vector<OrderModel> getActiveOrder() {
		Vector<OrderModel> activerderList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s WHERE userId=? AND NOT(orderStatus=?)",tableName);
		PreparedStatement ps = con.prepareStatement(query);
		
		ResultSet rs = null;
		
		try {
			ps.setInt(1, userId);
			ps.setString(2, status);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				String orderStatus = rs.getString("orderStatus");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				temp.setStatus(orderStatus);
				
				activerderList.add(temp);
			}
			return activerderList;
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
	

	public Vector<OrderModel> getAllFinishedOrder(){
		
		Vector<OrderModel> availableOrderList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where orderStatus='finished'",tableName);

		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				
				Integer orderId = rs.getInt("orderId");
				Integer driverId = rs.getInt("driverId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setDriverId(driverId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				
				availableOrderList.add(temp);
			}
			return availableOrderList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
				Integer userId = rs.getInt("userId");
				String orderStatus = rs.getString("orderStatus");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderIdTemp);
				temp.setAddress(address);
				temp.setDate(orderDate);
				temp.setUserId(userId);
				temp.setStatus(orderStatus);
				
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
		String query = String.format("SELECT * FROM %s where userId=? AND orderStatus='finished'",tableName);
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
		/*
		 * Function ini berfungsi untuk model mengambil data riwayat order seorang driver yang sudah selesai (berstatus finished)
		 * nantinya data akan ditampilkan pada halaman history untuk driver
		 */
		Vector<OrderModel> historyList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where driverId=? AND orderStatus='finished'",tableName);
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
	
	public Vector<OrderModel> viewTakenOrder(Integer driverId){
		Vector<OrderModel> historyList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s where driverId=? AND orderStatus NOT IN('not accepted','finished')",tableName);
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
				String orderStatus = rs.getString("orderStatus");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				temp.setStatus(orderStatus);
				
				historyList.add(temp);
			}
			return historyList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
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
	
	public boolean addOrder(UserModel user,Date date) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");		
		String query = String.format("INSERT INTO %s VALUES (null,?,?,?,null,?)", tableName) ;
		PreparedStatement ps = con.prepareStatement(query);	
		
		try {
			ps.setDate(1, date);
			ps.setString(2, user.getAddress());
			ps.setInt(3, user.getUserId());
			ps.setString(4, "not accepted");
			return ps.executeUpdate()==1;		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Vector<OrderModel> getUserActiveOrder(Integer userId) {
		Vector<OrderModel> activeOrderList = new Vector<OrderModel>();
		String query = String.format("SELECT * FROM %s WHERE userId=%s",tableName,userId.toString());
		ResultSet rs = con.executeQuery(query);
		
		try {
			while(rs.next()) {
				Integer orderId = rs.getInt("orderId");
				String address = rs.getString("address");
				Date orderDate = rs.getDate("orderDate");
				String orderStatus = rs.getString("orderStatus");
				
				OrderModel temp = new OrderModel();
				temp.setOrderId(orderId);
				temp.setAddress(address);
				temp.setDate(orderDate);
				temp.setStatus(orderStatus);
				
				activeOrderList.add(temp);
			}
			return activeOrderList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
