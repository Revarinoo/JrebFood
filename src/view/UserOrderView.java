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
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.FoodController;
import controller.OrderController;
import core.view.View;
import model.FoodModel;
import model.OrderModel;

public class UserOrderView extends View{
	
	JPanel topPanel, midPanel, midLabelPanel, botPanel;
	JTable table;
	JScrollPane sp;
	DefaultTableModel dtm;
	JLabel orderChoosenLbl;
	JTextField orderChoosenTxt;
	JButton activeBtn, historyBtn, cancelOrderBtn, detailOrderBtn;
	
	
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer orderId, id;
	
	JDesktopPane desktop = new JDesktopPane();
	
	HistoryOrderView historyFrame;
	


	public UserOrderView(JDesktopPane desktop, Integer userId) {
		super("User Active Order List");
		this.width=600;
		this.height=600;
		this.desktop = desktop;
		this.id = userId;
		loadActiveOrder();
		super.showForm();
	}

	@Override
	public void init() {
		GridLayout topGrid = new GridLayout(1, 2);
		GridLayout midGrid = new GridLayout(2, 1);
		GridLayout midLabelGrid = new GridLayout(1, 2);
		midLabelGrid.setVgap(200);
		
		topPanel = new JPanel(topGrid);
		midPanel = new JPanel(midGrid);
		midLabelPanel = new JPanel(midLabelGrid);
		botPanel = new JPanel(new FlowLayout());
		
		table = new JTable();
		sp = new JScrollPane(table);
		
		orderChoosenLbl = new JLabel("Choose Order ID");
		orderChoosenTxt = new JTextField();
		
		
		
		historyBtn = new JButton("Click to view order history");
		cancelOrderBtn = new JButton("Cancel Order");
		detailOrderBtn = new JButton("Detail Order");
		
		
		
	}

	@Override
	public void addComponent() {
		topPanel.add(historyBtn);
		
		midLabelPanel.add(orderChoosenLbl);
		midLabelPanel.add(orderChoosenTxt);
		
		midPanel.add(sp);
		midPanel.add(midLabelPanel);
		
		botPanel.add(detailOrderBtn);
		botPanel.add(cancelOrderBtn);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				orderId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				orderChoosenTxt.setText(orderId.toString());
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
		
		
		historyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				desktop.add(new HistoryOrderView(desktop,id,null));
				dispose();
				
			}


		});

		
		detailOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = Integer.parseInt(orderChoosenTxt.getText());
				desktop.add(new DetailsOrderView(desktop, row));
				dispose();
			
			}
			
		});
		
		cancelOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = Integer.parseInt(orderChoosenTxt.getText());
				
				if (!OrderController.getInstance().removeOrder(row)) {
					JOptionPane.showMessageDialog(null, 
							"Cannot cancel this order",
							"Error Message", 
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(UserOrderView.this,
						"Cancel Complete"
					);
				}
				
				loadActiveOrder();
				
			}
		});
		
	}
	
	private void loadActiveOrder() {
		data = new Vector<>();
		header = new Vector<>();
		header.add("ID");
		header.add("Destination Address");
		header.add("Order Date");
		header.add("Order Status");
		
		Vector<OrderModel> listAllOrder = OrderController.getInstance().viewOrderList(id, "finished");
		
		for (OrderModel orderModel : listAllOrder) {
			
			OrderModel order = orderModel;
			
			detail = new Vector<>();
			detail.add(order.getOrderId().toString());
			detail.add(order.getAddress());
			detail.add(order.getDate().toString());
			detail.add(order.getStatus());
			
			data.add(detail);
			
		}
		
			
		dtm = new DefaultTableModel(data, header);
		
		table.setModel(dtm);
		
	}
	
}
