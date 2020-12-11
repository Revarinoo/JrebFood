package controller;

import java.util.Vector;

import javax.swing.JDesktopPane;

import core.view.View;
import model.OrderDetailModel;
import model.OrderModel;
import view.FinancialSummaryView;
import view.UserOrderView;

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
	

//	public void updateStatus(Integer orderId, String status) {
//		OrderModel order = new OrderModel();
//		order.setOrderId(orderId);
//		order.setStatus(status);
//		order.updateStatus(orderId, status);
//	}
	
	public Vector<OrderModel> getOrderForChef() {
		
		OrderModel order = new OrderModel();
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
		// TODO Auto-generated method stub
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
	
	public Vector<OrderModel> viewTakenOrder(Integer driverId){
		OrderModel order = new OrderModel();
		return order.viewTakenOrder(driverId);
	}
	
	public Vector<OrderDetailModel> viewDetailById(Integer orderId){
		OrderDetailModel detail = new OrderDetailModel();
		return detail.viewDetailById(orderId);
	}

	public Integer totalFinishedTransaction() {
		OrderDetailModel od = new OrderDetailModel();
		return od.totalFinishedTransaction();
	}

	public boolean deleteOrder(Integer orderId) {
		
		OrderDetailModel orderDetail = new OrderDetailModel();
		orderDetail.setOrderId(orderId);
		orderDetail.deleteDetailByOrder(orderId);
		
		OrderModel order = new OrderModel();
		order.setOrderId(orderId);
		order.deleteOrder(orderId);
		
		if (order.deleteOrder(orderId) == false) {
			return false;
		}
		// TODO Auto-generated method stub
		return true;
	}
}
