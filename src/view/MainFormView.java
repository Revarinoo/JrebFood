package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import controller.FoodController;
import controller.OrderController;
import core.view.MainView;

public class MainFormView extends MainView{

	JMenuBar mainMenuBar;
	JMenu userMenu,transactionMenu,shopMenu, chefMenu;
	JSeparator menuSeparator;
    JMenuItem loginMI,logoutMI,exitMI,orderMI,historyMI,foodMI,cartMI, chefAddFoodMI, chefFoodListMI, chefOrderListMI;
	JDesktopPane desktop = new JDesktopPane();
//	LoginInternalFrame loginFrame;
//	OrderInternalFrame orderFrame;
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
	}

	@Override
	public void addComponent() {
		mainMenuBar.add(userMenu);
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
		
	
		add(mainMenuBar,BorderLayout.NORTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
//		loginMI.addActionListener();
//		logoutMI.addActionListener();
//		cartMI.addActionListener();
		
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		foodMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				add(desktop,BorderLayout.CENTER);
//				foodMenuInternalFrame = new FoodMenuView();
//				desktop.add(foodMenuInternalFrame);
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
