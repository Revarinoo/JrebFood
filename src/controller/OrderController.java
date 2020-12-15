package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

import core.view.View;
import model.CartModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.UserModel;
import view.CartView;
import view.FinancialSummaryView;
import view.UserOrderView;

public class OrderController {

	private static OrderController instance;
	private String errorMsg;
	
	public static OrderController getInstance() {
		if(instance == null) {
			synchronized (OrderController.class) {
				if(instance == null) {
					instance = new OrderController();
				}
			}
		}
		return instance;
	}
	
	private OrderController() {
		this.errorMsg="";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
//	public void updateStatus(Integer orderId, String status) {
//		OrderModel order = new OrderModel();
//		order.setOrderId(orderId);
//		order.setStatus(status);
//		order.updateStatus(orderId, status);
//	}
	
	public Vector<OrderModel> getOrderForChef(String orderStatus) {
		
		OrderModel order = new OrderModel();
		order.setStatus(orderStatus);
		return order.getOrderForChef();
	}
	
	public View viewOrders(JDesktopPane desktop, Integer userId) {
		return new UserOrderView(desktop, userId);
	}
	
	public Vector<OrderModel> viewOrderList(Integer userId, String status){
		OrderModel order = new OrderModel();
		order.setUserId(userId);
		order.setStatus(status);
		return order.getActiveOrder();
	}
	
	public View viewOrderQueue(View target) {
		return target;
	}

	public View viewProfit(View target) {
		return target;
	}

	public Vector<OrderModel> getAll() {
		OrderModel order = new OrderModel();
		return order.getAll();
	}
	
	public Vector<OrderModel> getAllFinishedOrder(){
		OrderModel order = new OrderModel();
		return order.getAllFinishedOrder();
	}
	
	public boolean takeOrder(Integer orderId, Integer driverId) {
		OrderModel order = new OrderModel();
		return order.takeOrder(orderId, driverId);
	}
	
	public boolean updateStatus(Integer orderId, String status) {
		OrderModel order = getOne(orderId);
		boolean flag = validateStatus(order, status);
		if(flag ==true) {
			order.updateStatus(orderId, status);
			return true;
		}
		return false;
	}
	
	public OrderModel getOne(Integer orderId) {
		OrderModel order = new OrderModel();
		return order.getOne(orderId);
	}
	
	// Untuk melihat order berdasarkan user id
	public Vector<OrderModel> viewAllHistoryForUser(Integer userId){
		OrderModel order = new OrderModel();
		return order.viewAllHistoryForUser(userId);
	}
	
	// Untuk melihat order berdasarkan driver id
	public Vector<OrderModel> viewAllHistoryForDriver(Integer driverId){
		OrderModel order = new OrderModel();
		return order.viewAllHistoryForDriver(driverId);
	}
	
	public boolean validateStatus(OrderModel order, String status) {
		
		/*
		 * Function ini berfungsi untuk memvalidasi status sebelum status di update
		 * terdapat beberapa kondisi validasi, yaitu:
		 */
		
		if (status.equals("cooked")) { // jika ingin mengubah status menjadi cooked, harus berstatus ordered terlebih dahulu
			if(order.getStatus().equals("ordered")) {
				return true;
			}
			return false;
		}
		else if(status.equals("finished")) { // jika ingin mengubah status menjadi finished, harus berstatus cooked terlebih dahulu
			if(order.getStatus().equals("cooked")) {
				return true;
			}
			return false;
		}else if(status.equals("ordered")){ // jika ingin mengubah status menjadi ordered, harus berstatus accepted terlebih dahulu
			if(order.getStatus().equals("accepted")) {
				return true;
			}
			return false;
		}
			return false;
	}
	
	public Vector<OrderModel> viewTakenOrder(Integer driverId){
		OrderModel order = new OrderModel();
		return order.viewTakenOrder(driverId);
	}
	
	public Vector<OrderDetailModel> viewDetailById(Integer orderId){
		OrderDetailModel detail = new OrderDetailModel();
		return detail.viewDetailById(orderId);
	}

	// untuk mendapatkan nilai total transaksi dari semua driver
	public Integer totalFinishedTransaction() {
		OrderDetailModel od = new OrderDetailModel();
		return od.totalFinishedTransaction();
	}

	public boolean removeOrder(Integer orderId) {
		
		OrderModel temp = getOne(orderId);
		String status = temp.getStatus();
	
		
		boolean flag = validateOrderedStatus(status);
		
		if (flag == false) return false;

		
		OrderDetailModel orderDetail = new OrderDetailModel();
		orderDetail.setOrderId(orderId);
		orderDetail.deleteDetailByOrder(orderId);
		
		OrderModel order = new OrderModel();
		order.setOrderId(orderId);
		order.deleteOrder(orderId);
		
		if (order.deleteOrder(orderId) == false) {
			return false;
		}
		
		return true;
	}

	private boolean validateOrderedStatus(String status) {
		if (status.equals("accepted") || status.equals("ordered") || status.equals("cooked")) {
			
			return false;
		}
		return true;
	}
	
	public boolean addOrder(UserModel user,Date date) {
		Vector<CartModel> carts = CartController.getInstance().viewAll(user.getUserId());
		OrderModel order = new OrderModel();
		
		if(carts.size()==0) {
			errorMsg="Cart is Empty, please add some food";
			return false;
		}
		boolean orderStatus = order.addOrder(user, date);
		if(!orderStatus) {
			errorMsg="Order Checkout Failed";
			return false;
		}
		return orderStatus;
	}
	
	public OrderModel getNewInsertedOrder(Integer userId) {
		OrderModel temp = new OrderModel();
		Vector<OrderModel> orders =  temp.getUserActiveOrder(userId);

		OrderModel newOrder = new OrderModel();
	
		for(int x=0;x<orders.size();x++) {
			if(x==(orders.size()-1)) {
				newOrder = orders.get(x);
			}
		}	
		return newOrder;
	}
	
	public void addOrderDetail(Integer orderId,Integer foodId,Integer qty) {
		OrderDetailModel orderDetail = new OrderDetailModel();
		orderDetail.addOrderDetail(orderId,foodId, qty);
	}
}
