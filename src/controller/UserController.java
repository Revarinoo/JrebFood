package controller;

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
	
//	public View view() {
//		return new UserController();
//	}
	
	public UserModel getOne(Integer userId) {
		UserModel user = new UserModel();
		return user.getOne(userId);
	}
}
