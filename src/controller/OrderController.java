package controller;

import java.util.Vector;


import core.view.View;
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
	
	public void updateStatus(Integer orderId, String status) {
		OrderModel order = new OrderModel();
		order.setOrderId(orderId);
		order.setStatus(status);
		order.updateStatus(orderId, status);
	}
	
	public Vector<OrderModel> getOrderForChef() {
		
		OrderModel order = new OrderModel();
		return order.getOrderForChef();
	}
	
	public View viewOrderQueue(View target) {
		// TODO Auto-generated method stub
		return target;
	}
	
	
//	public View view() {
//		return new OrderController();
//	}
}
