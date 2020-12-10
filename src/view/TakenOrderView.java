package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class TakenOrderView extends View{
	
	JPanel main,top,center,bottom,bottom1,bottom2,choosePanel;
	JLabel transLabel,transIdLabel;
	JButton btnOrder,btnDeliver;
	JTable tableOrder;
	JScrollPane sp;
	JTextField chooseTxt;
	Vector<Vector<String>> dataOrder;
	Vector<String> header,detailOrder;
	JDesktopPane desktop;
	Integer driverId;
	
	public TakenOrderView(JDesktopPane desktop, Integer driverId) {
		super("Taken Order");
		// TODO Auto-generated constructor stub
		this.height=600;
		this.width=600;
		this.desktop = desktop;
		this.driverId = driverId;
		loadData();
		super.showForm();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		header.add("Order ID");
		header.add("Order Date");
		header.add("Address");
		Vector<OrderModel> listHistory = OrderController.getInstance().viewTakenOrder(driverId);
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
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		main = new JPanel(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel(new GridLayout(2,1));
		
		//Top
		transLabel = new JLabel("Your Taken Order");
		
		//Center
		center = new JPanel();
		tableOrder = new JTable();
		sp = new JScrollPane(tableOrder);
		header = new Vector<>();
		
		dataOrder = new Vector<>();
		header = new Vector<>();
		
		//Bottom
		//Bottom1
		chooseTxt = new JTextField();
		bottom1 = new JPanel();
		choosePanel= new JPanel(new GridLayout(1,2));
		transIdLabel = new JLabel("Choosen Order");
		
		//Bottom2
		bottom2 = new JPanel();
		btnOrder = new JButton("Order");
		btnDeliver = new JButton("Deliver");
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
		bottom2.add(btnOrder);
		bottom2.add(btnDeliver);
		bottom.add(bottom1);
		bottom.add(bottom2);
		
		main.add(bottom,BorderLayout.SOUTH);
		add(main);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		tableOrder.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tableOrder.getSelectedRow();
				chooseTxt.setText(tableOrder.getValueAt(row, 0).toString());
			}
		});
	}


}
