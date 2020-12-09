package controller;

import java.util.Vector;

import model.OrderModel;

public class OrderController {

	private static OrderController instance;
	
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
		// TODO Auto-generated constructor stub
	}
	
	public Vector<OrderModel> getAll() {
		OrderModel order = new OrderModel();
		return order.getAll();
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
	
	public Vector<OrderModel> viewAllHistoryForUser(Integer userId){
		OrderModel order = new OrderModel();
		return order.viewAllHistoryForUser(userId);
	}
	
	public Vector<OrderModel> viewAllHistoryForDriver(Integer driverId){
		OrderModel order = new OrderModel();
		return order.viewAllHistoryForDriver(driverId);
	}
	
	public boolean validateStatus(OrderModel order, String status) {
		if(status.equals("finished")) {
			if(order.getStatus().equals("ready")) {
				return true;
			}
			return false;
		}else if(status.equals("ordered")){
			return true;
		}else {
			return false;
		}
	}
}
