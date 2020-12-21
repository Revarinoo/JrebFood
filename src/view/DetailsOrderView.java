package view;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderDetailModel;

public class DetailsOrderView extends View{
	
	JPanel main,top,center;
	JLabel transLabel;
	JTable tableOrder;
	JScrollPane sp;
	Vector<Vector<String>> dataOrder;
	Vector<String> header,detailOrder;
	JDesktopPane desktop;
	Integer orderId;
	public DetailsOrderView(JDesktopPane desktop, Integer orderId) {
		super("Details Order");
		
		this.height=600;
		this.width=600;
		this.desktop = desktop;
		this.orderId = orderId;
		loadData();
		super.showForm();
	}

	private void loadData() {
		
		header.add("Order ID");
		header.add("Food ID");
		header.add("Quantity");
		Vector<OrderDetailModel> listDetail = OrderController.getInstance().viewDetailById(orderId);
		for (OrderDetailModel model : listDetail) {
			OrderDetailModel detail = (OrderDetailModel) model;
			detailOrder = new Vector<>();
			detailOrder.add(detail.getOrderId().toString());
			detailOrder.add(detail.getFoodId().toString());
			detailOrder.add(detail.getQty().toString());
			dataOrder.add(detailOrder);
		}
		DefaultTableModel dtm = new DefaultTableModel(dataOrder, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableOrder.setModel(dtm);
	}

	@Override
	public void init() {
		main = new JPanel(new BorderLayout());
		top = new JPanel();
		
		//Top
		transLabel = new JLabel("Order Details");
		
		//Center
		center = new JPanel();
		tableOrder = new JTable();
		sp = new JScrollPane(tableOrder);
		header = new Vector<>();
		
		dataOrder = new Vector<>();
		header = new Vector<>();
		
	}

	@Override
	public void addComponent() {
		//Top
		top.add(transLabel);
		main.add(top,BorderLayout.NORTH);
		
		//Center
		center.add(sp);
		main.add(center,BorderLayout.CENTER);
		
		add(main);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
	}


}
