package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import controller.EmployeeController;
import controller.FoodController;
import controller.OrderController;
import controller.UserController;
import core.view.MainView;

public class MainFormView extends MainView{

	JMenuBar mainMenuBar;

	JMenu userMenu,transactionMenu,shopMenu, chefMenu, managerMenu;
	JSeparator menuSeparator;
    JMenuItem loginMI, registerMI, logoutMI,exitMI,orderMI,historyMI,foodMI,cartMI, chefAddFoodMI, chefFoodListMI, chefOrderListMI, manageEmployeeMI, financialMI;

	JDesktopPane desktop = new JDesktopPane();

	RegistrationView registrationFrame;
	ManageEmployeeView manageEmployeeFrame;
	FoodMenuView foodMenuIF;
	CartView cartIF;
	public static boolean loginState = false;
//	public static boolean logoutState = true;
	
	ChefAddFoodView chefAddFoodFrame;
	ChefFoodListView chefFoodListFrame;
	ChefOrderListView orderListChefFrame;
	
	
	public MainFormView() {
		super();
	}

	@Override
	public void initialize() {
		mainMenuBar = new JMenuBar();
		userMenu = new JMenu("User");
		registerMI = new JMenuItem("Create Account");
		loginMI = new JMenuItem("Login");
		logoutMI = new JMenuItem("Logout");
		exitMI = new JMenuItem("Exit");
		menuSeparator = new JSeparator();
		
		transactionMenu = new JMenu("Transaction");
		orderMI = new JMenuItem("Order");
		historyMI = new JMenuItem("History");
		
		shopMenu = new JMenu("Shop");
		foodMI = new JMenuItem("Food Menu");
		cartMI = new JMenuItem("Cart");
		

		chefMenu = new JMenu("Chef Menu");
		chefAddFoodMI = new JMenuItem("Add Food");
		chefFoodListMI = new JMenuItem("Food List");
		chefOrderListMI = new JMenuItem("Order List");

		managerMenu = new JMenu("Manager");
		manageEmployeeMI = new JMenuItem("Manage Employee");
		financialMI = new JMenuItem("Financial Summary");

	}

	@Override
	public void addComponent() {
		mainMenuBar.add(userMenu);
		userMenu.add(registerMI);
		userMenu.add(loginMI);
		userMenu.add(logoutMI);
		userMenu.add(menuSeparator);
		userMenu.add(exitMI);
		
		mainMenuBar.add(transactionMenu);
		transactionMenu.add(orderMI);
		transactionMenu.add(historyMI);
		
		mainMenuBar.add(shopMenu);
		shopMenu.add(foodMI);
		shopMenu.add(cartMI);
		

		mainMenuBar.add(chefMenu);
		chefMenu.add(chefAddFoodMI);
		chefMenu.add(chefFoodListMI);
		chefMenu.add(chefOrderListMI);
		
	
		mainMenuBar.add(managerMenu);
		managerMenu.add(manageEmployeeMI);
		managerMenu.add(financialMI);
		
		add(mainMenuBar,BorderLayout.NORTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
//		loginMI.addActionListener();
//		logoutMI.addActionListener();
//		cartMI.addActionListener();
		
		registerMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop, BorderLayout.CENTER);
				registrationFrame = new RegistrationView();
				UserController.getInstance().view(registrationFrame);
				desktop.removeAll();
				desktop.add(registrationFrame);
			}
		});
		
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		foodMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				foodMenuIF = FoodController.getInstance().viewMenu();
				desktop.removeAll();
				desktop.add(foodMenuIF);
			}
		});
		
		cartMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				cartIF = new CartView();
				desktop.removeAll();
				desktop.add(cartIF);
			}
		});
		
		manageEmployeeMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				manageEmployeeFrame = new ManageEmployeeView(desktop);
				EmployeeController.getInstance().view(manageEmployeeFrame);
				desktop.removeAll();
				desktop.add(manageEmployeeFrame);
			}
		});
		
		chefAddFoodMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				chefAddFoodFrame = new ChefAddFoodView();
				FoodController.getInstance().viewManageFoodForm(chefAddFoodFrame);
				desktop.removeAll();
				desktop.add(chefAddFoodFrame);
			}
		});
		
		chefFoodListMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				chefFoodListFrame = new ChefFoodListView();
				FoodController.getInstance().viewManageFoodForm(chefFoodListFrame);
				desktop.removeAll();
				desktop.add(chefFoodListFrame);
			}
		});
		
		chefOrderListMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				orderListChefFrame = new ChefOrderListView();
				OrderController.getInstance().viewOrderQueue(orderListChefFrame);
				desktop.removeAll();
				desktop.add(orderListChefFrame);
			}
		});
	}

}
