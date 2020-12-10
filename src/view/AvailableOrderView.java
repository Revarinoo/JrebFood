package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderModel;

public class AvailableOrderView extends View {
	
	JPanel main,top,center,bottom,bottom1,bottom2,choosePanel;
	JLabel transLabel,transIdLabel;
	JButton btnTake;
	JTable tableOrder;
	JScrollPane sp;
	JTextField chooseTxt;
	Vector<Vector<String>> dataOrder;
	Vector<String> header,detailOrder;
	JDesktopPane desktop;
	
	public AvailableOrderView(JDesktopPane desktop) {
		super("Available Order");
		// TODO Auto-generated constructor stub
		this.width=600;
		this.height=600; 
		this.desktop = desktop;
		super.showForm();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		main = new JPanel(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel(new GridLayout(2,1));
		
		//Top
		transLabel = new JLabel("Available Order");
		
		//Center
		center = new JPanel();
		tableOrder = new JTable();
		sp = new JScrollPane(tableOrder);
		header = new Vector<>();
		
		dataOrder = new Vector<>();
		header = new Vector<>();
		header.add("Order ID");
		header.add("Order Date");
		header.add("Address");
		Vector<OrderModel> listHistory = OrderController.getInstance().getAll();
		for (OrderModel model : listHistory) {
			OrderModel order = (OrderModel) model;
			detailOrder = new Vector<>();
			detailOrder.add(order.getOrderId().toString());
			detailOrder.add(order.getDate().toString());
			detailOrder.add(order.getAddress().toString());
			dataOrder.add(detailOrder);
		}
		DefaultTableModel dtm = new DefaultTableModel(dataOrder, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableOrder.setModel(dtm);
		
		//Bottom
		//Bottom1
		chooseTxt = new JTextField();
		bottom1 = new JPanel();
		choosePanel= new JPanel(new GridLayout(1,2));
		transIdLabel = new JLabel("Choosen Order");
		
		//Bottom2
		bottom2 = new JPanel();
		btnTake = new JButton("Take Order");
	}

	@Override
	public void addComponent() {
		// TODO Auto-generated method stub
		//Top
		top.add(transLabel);
		main.add(top,BorderLayout.NORTH);
		
		//Center
		center.add(sp);
		main.add(center,BorderLayout.CENTER);
		
		//Bottom
		//Bottom1
		choosePanel.add(transIdLabel);
		choosePanel.add(chooseTxt);
		bottom1.add(choosePanel);
		
		//Bottom2
		bottom2.add(btnTake);
		bottom.add(bottom1);
		bottom.add(bottom2);
		
		main.add(bottom,BorderLayout.SOUTH);
		add(main);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
