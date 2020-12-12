package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;

import controller.EmployeeController;
import controller.FoodController;
import controller.OrderController;
import controller.UserController;
import core.view.MainView;

public class MainFormView extends MainView{

	JMenuBar mainMenuBar;

	public static JMenu userMenu,transactionMenu,shopMenu, chefMenu, managerMenu, driverMenu;
	JSeparator menuSeparator;
	public static JMenuItem loginMI, registerMI, logoutMI,exitMI,orderMI,historyMI,foodMI,cartMI, chefAddFoodMI, chefFoodListMI, chefOrderListMI, manageEmployeeMI, financialMI, historyOrderMI, takenOrderMi,availableOrderMI;

	JDesktopPane desktop = new JDesktopPane();

	RegistrationView registrationFrame;
	ManageEmployeeView manageEmployeeFrame;
	FoodMenuView foodMenuIF;
	FinancialSummaryView financialFrame;
	CartView cartIF;
	
	public static Integer roleId = 0;
	public static boolean loginState = false;
	public static Integer userID = null; // id user atau employee yg login 
	private boolean logoutState = false;
	
	UserOrderView userOrderFrame;
	ChefAddFoodView chefAddFoodFrame;
	ChefFoodListView chefFoodListFrame;
	ChefOrderListView orderListChefFrame;
	HistoryOrderView historyFrame;
	TakenOrderView takenOrderFrame;
	AvailableOrderView availableOrderFrame;
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
		
		driverMenu = new JMenu("Order For Driver");
		historyOrderMI = new JMenuItem("History Order");
		takenOrderMi = new JMenuItem("Taken Order List");
		availableOrderMI = new JMenuItem("Available Order");
		updateMenuBar();
	}

	@Override
	public void addComponent() {
		mainMenuBar.add(userMenu);
		userMenu.add(registerMI);
		userMenu.add(loginMI);
		userMenu.add(logoutMI);
		userMenu.add(menuSeparator);
		userMenu.add(exitMI);
		logoutMI.setVisible(false);
		
		mainMenuBar.add(transactionMenu);
		transactionMenu.add(orderMI);
		transactionMenu.setVisible(false);
		
		mainMenuBar.add(shopMenu);
		shopMenu.add(foodMI);
		shopMenu.add(cartMI);
		shopMenu.setVisible(false);

		mainMenuBar.add(chefMenu);
		chefMenu.add(chefAddFoodMI);
		chefMenu.add(chefFoodListMI);
		chefMenu.add(chefOrderListMI);
		chefMenu.setVisible(false);
	
		mainMenuBar.add(managerMenu);
		managerMenu.add(manageEmployeeMI);
		managerMenu.add(financialMI);
		managerMenu.setVisible(false);
		
		mainMenuBar.add(driverMenu);
		driverMenu.add(historyOrderMI);
		driverMenu.add(takenOrderMi);
		driverMenu.add(availableOrderMI);
		driverMenu.setVisible(false);
		
		add(mainMenuBar,BorderLayout.NORTH);
		
	}
	
	public static void updateMenuBar() {		
		
		if(loginState==true) {
			registerMI.setVisible(false);
			loginMI.setVisible(false);
			switch (roleId) {
			case 1:	managerMenu.setVisible(true); break;

			case 2: chefMenu.setVisible(true); break;
			case 3: driverMenu.setVisible(true); break;
			case 4: transactionMenu.setVisible(true);
					shopMenu.setVisible(true);
					break;
			}
			logoutMI.setVisible(true);
		}
//		else if(){
//			
//		}
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		loginMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop, BorderLayout.CENTER);
				desktop.removeAll();
				desktop.add(UserController.getInstance().viewLogin());
			}
		});
		
		logoutMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginState = false;
				logoutState = true;
				desktop.removeAll();
				updateMenuBar();
			}
		});
//		cartMI.addActionListener();
		
		registerMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop, BorderLayout.CENTER);
				desktop.removeAll();
				desktop.add(UserController.getInstance().viewRegistrationForm());
			}
		});
		
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		orderMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				desktop.removeAll();
				desktop.add(OrderController.getInstance().viewOrders(desktop, 1));
				
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
				EmployeeController.getInstance().viewManageEmployeeForm();
				desktop.removeAll();
				desktop.add(new ManageEmployeeView());
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
		if(roleId == 1) driverMenu.setVisible(false);
		historyOrderMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				historyFrame = new HistoryOrderView(desktop,5,3);
				desktop.removeAll();
				desktop.add(historyFrame);
			}
		});
		
		takenOrderMi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				takenOrderFrame = new TakenOrderView(desktop, 5);
				desktop.removeAll();
				desktop.add(takenOrderFrame);
			}
		});
		
		availableOrderMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				availableOrderFrame = new AvailableOrderView(desktop);
				desktop.removeAll();
				desktop.add(availableOrderFrame);
			}
		});
		
		financialMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				desktop.removeAll();
				desktop.add(OrderController.getInstance().viewProfit(new FinancialSummaryView(desktop)));
			}
		});
	}
	
	

}
