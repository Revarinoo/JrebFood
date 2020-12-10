package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.UserController;
import core.view.View;
import model.UserModel;

public class UserInformationView extends View{
	
	JPanel main,top,center,centerData,centerButton;
	JLabel activeLabel, useridLabel,userid, userNameLabel,username, phoneLabel, phone, addressLabel, address;
	JButton btnProceed;
	JDesktopPane desktop;
	Integer userId;
	public UserInformationView(JDesktopPane desktop, Integer userId) {
		super("User Information");
		// TODO Auto-generated constructor stub
		this.width=600;
		this.height=600; 
		this.desktop = desktop;
		this.userId = userId;
		loadData();
		super.showForm();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		System.out.println(userId);
		UserModel user = UserController.getInstance().getOne(userId);
		userid.setText(user.getUserId().toString());
		username.setText(user.getName().toString());
		address.setText(user.getAddress().toString());
		phone.setText(user.getPhoneNumber().toString());
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		main = new JPanel(new BorderLayout());
		top = new JPanel();
		center = new JPanel(new GridLayout(2,1));
		
		//Top
		activeLabel = new JLabel("User Information");
		
		//Center

		centerData = new JPanel(new GridLayout(4,2));
		useridLabel = new JLabel("User ID: ");
		userid = new JLabel();
		userNameLabel = new JLabel("User Name: ");
		username = new JLabel();
		addressLabel = new JLabel("User Address: ");
		address = new JLabel();
		phoneLabel = new JLabel("User phone: ");
		phone = new JLabel();
		centerButton = new JPanel();
		btnProceed = new JButton("Proceed");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		//Top
				top.add(activeLabel);
				main.add(top,BorderLayout.NORTH);
				
				//Center
				centerData.add(useridLabel);
				centerData.add(userid);
				centerData.add(userNameLabel);
				centerData.add(username);
				centerData.add(addressLabel);
				centerData.add(address);
				centerData.add(phoneLabel);
				centerData.add(phone);
				center.add(centerData);
				
				centerButton.add(btnProceed);
				center.add(centerButton);
				
				main.add(center,BorderLayout.CENTER);
				add(main);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}


}
