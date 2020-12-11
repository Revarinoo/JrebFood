package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderModel;

public class ChefOrderListView extends View{
	
	JPanel topPanel, midPanel, midDetail, botPanel;
	JLabel titleLbl, orderIdLbl, driverIdLbl;
	JTextField orderIdTxt, driverIdTxt;
	JButton changeStatusBtn;
	
	DefaultTableModel dtm;
	JTable tableOrder;
	JScrollPane scrollPane;
	
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer orderId, driverId;

	public ChefOrderListView() {
		super("Order List");
		this.width=600;
		this.height=600;
		
		super.showForm();
	}

	@Override
	public void init() {
		topPanel = new JPanel(new FlowLayout());
		
		GridLayout gridBig = new GridLayout(2, 1);
		gridBig.setVgap(0);
		
		GridLayout gridMid = new GridLayout (2, 2);

		
		midPanel = new JPanel(gridBig);
		midDetail = new JPanel(gridMid);
		
		
		botPanel = new JPanel(new FlowLayout());
		
		
		titleLbl = new JLabel("Order List");
		
		tableOrder = new JTable();
		scrollPane = new JScrollPane(tableOrder);
		
		 orderIdLbl = new JLabel("Order ID");
		 orderIdTxt = new JTextField();
		 orderIdTxt.setEditable(false);
		 
		 driverIdLbl = new JLabel("Driver ID");
		 driverIdTxt = new JTextField();
		 driverIdTxt.setEditable(false);
		 
		 
		 changeStatusBtn = new JButton("Cook Order");
		
	}

	@Override
	public void addComponent() {
		topPanel.add(titleLbl);
		
		
		midDetail.add(orderIdLbl);
		midDetail.add(orderIdTxt);
		
		
		midDetail.add(driverIdLbl);
		midDetail.add(driverIdTxt);
		
		
		midPanel.add(scrollPane);
		midPanel.add(midDetail);

		
		botPanel.add(changeStatusBtn);
		
		loadData();
		 
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
		
	}



	@Override
	public void addListener() {
		tableOrder.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				orderIdTxt.setText(tableOrder.getValueAt(tableOrder.getSelectedRow(), 0).toString());
				driverIdTxt.setText(tableOrder.getValueAt(tableOrder.getSelectedRow(), 1).toString());
				
				orderId = Integer.parseInt(orderIdTxt.getText());
				driverId = Integer.parseInt(driverIdTxt.getText());

				
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
				
			}
		});
		
		changeStatusBtn.addActionListener(new ActionListener() {
			
			// Testing doang. Nanti tunggu kepastian aslab ganti nya jadi apa
			String statusFinished = "cooked";
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderController.getInstance().updateStatus(orderId, statusFinished);
				
				JOptionPane.showMessageDialog(ChefOrderListView.this,
						"Order ID : "+orderId+
						"\nDriver ID : "+driverId+
						"\n\nUpdate Status Complete"
					
				);
				
				loadData();
			}

		});
		
	}
	
	private void loadData() {
		
		data = new Vector<>();
		header = new Vector<>();
		header.add("Order Id");
		header.add("Driver Id");
		header.add("Status");
		
		
		Vector<OrderModel> listorder = OrderController.getInstance().getOrderForChef();
		
		for ( OrderModel model : listorder) {
			OrderModel order = (OrderModel )model;
			
			detail = new Vector<>();
			detail.add(order.getOrderId().toString());
			detail.add(order.getDriverId().toString());
			detail.add(order.getStatus().toString());
			
			data.add(detail);
		}
		dtm = new DefaultTableModel(data, header);
		tableOrder.setModel(dtm);
		
	}

}
