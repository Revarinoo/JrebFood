package controller;

import core.view.View;
import model.UserModel;

public class UserController {

	private static UserController instance;
	
	public static UserController getInstance() {
		if(instance == null) {
			synchronized (UserController.class) {
				if(instance == null) {
					instance = new UserController();
				}
			}
		}
		return instance;
	}
	
	private UserController() {
		// TODO Auto-generated constructor stub
	}
	
	public View view(View target) {
		return target;
	}
	
	public boolean createAccount(String name, String address, String email, String phoneNumber, String password) {
		
		if(name.equals("") ||address.equals("")) return false;
		if(!validateFields(email, phoneNumber)) return false;
		try {
			UserModel user = new UserModel();
			user.setName(name);
			if(!validateUnique(email, phoneNumber)) return false;
			user.setEmail(email);
			if(password.length() < 4) return false;
			user.setPassword(password);
			user.setAddress(address);
			user.setPhoneNumber(phoneNumber);
			if(!user.createAccount()) return false;
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validateUnique(String email, String phoneNumber) {
		if(!email.contains("@") || !email.contains(".")) return false;
		if(phoneNumber.length() < 11 || phoneNumber.length() > 12) return false;
		return true;
	}
	
	public boolean validateFields(String email, String phoneNumber) {
		if(email.equals("") || phoneNumber.equals("")) return false;
		return true;
	}
	public UserModel getOne(Integer userId) {
		UserModel user = new UserModel();
		return user.getOne(userId);
	}
}
