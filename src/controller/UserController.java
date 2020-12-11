package controller;

import core.view.View;
import model.UserModel;
import view.LoginView;
import view.MainFormView;
import view.RegistrationView;

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
	
	public View viewRegistrationForm() {
		return new RegistrationView();
	}
	
	public View viewLogin() {
		return new LoginView();
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
	
	public boolean validateLogin(String email, String password) {
		UserModel user = new UserModel();
		user.setEmail(email);
		user.setPassword(password);
		Integer roleId = user.validateLogin();
		if(roleId != 0) {
			MainFormView.roleId = roleId;
			MainFormView.loginState = true;
			return true;
		}
		return false;
	}
}
