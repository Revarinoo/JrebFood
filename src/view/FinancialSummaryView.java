package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderDetailModel;
import model.OrderModel;

public class FinancialSummaryView extends View{
	JPanel top, mid, bottom;
	JLabel titleLbl, grandTotalLbl;
	JTextField grandTotalTxt;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	Vector<Vector<String>> data;
	Vector<String> detail, header;

	public FinancialSummaryView() {
		super("Financial");
		this.width = 600;
		this.height = 600;
		super.showForm();
	}

	@Override
	public void init() {
		
		top = new JPanel();
		mid = new JPanel();
		bottom = new JPanel(new GridLayout(1,2));
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
		titleLbl = new JLabel("Financial Summary");
		grandTotalLbl = new JLabel("Grand Total");
		grandTotalTxt = new JTextField();
		grandTotalTxt.setEditable(false);
		
	}

	@Override
	public void addComponent() {
		Border border = titleLbl.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		titleLbl.setBorder(new CompoundBorder(border, margin));
		
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 20));
		grandTotalLbl.setFont(new Font(grandTotalLbl.getFont().getName(), grandTotalLbl.getFont().getStyle(), 20));
		grandTotalTxt.setFont(new Font(grandTotalTxt.getFont().getName(), grandTotalTxt.getFont().getStyle(), 20));
		
		
		top.add(titleLbl);
		mid.add(scrollPane);
		bottom.add(grandTotalLbl);
		bottom.add(grandTotalTxt);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		loadData();
	}

	@Override
	public void addListener() {
		
	}
	
	private void loadData() {
		data = new Vector<>();
		header = new Vector<String>();
		header.add("Order ID");
		header.add("Driver ID");
		header.add("Adress");
		header.add("Order Date");
		
		Vector<OrderModel> listOrder = OrderController.getInstance().getAllFinishedOrder(); 

		for(OrderModel model : listOrder) {
			detail = new Vector<>();
			detail.add(model.getOrderId().toString());
			detail.add(model.getDriverId().toString());
			detail.add(model.getAddress());
			detail.add(model.getDate().toString());
			
			data.add(detail);
		}
		grandTotalTxt.setText(OrderController.getInstance().totalFinishedTransaction().toString());
		dtm = new DefaultTableModel(data,header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}

}
