package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderDetailModel;
import model.OrderModel;

public class FinancialSummaryView extends View{
	JPanel top, midPanel, bottom, midDetail;
	JLabel titleLbl, grandTotalLbl , grandTotalValue;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	JButton filterBtn;
	JDesktopPane desktop;
	
	public FinancialSummaryView(JDesktopPane desktop) {
		super("Financial");
		this.width = 600;
		this.height = 600;
		this.desktop = desktop;
		super.showForm();
	}

	@Override
	public void init() {
		
		top = new JPanel();
		midDetail = new JPanel(new GridLayout(1,2));
		midPanel = new JPanel(new GridLayout(2,1));
		bottom = new JPanel();
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
		titleLbl = new JLabel("Financial Summary");
		grandTotalLbl = new JLabel("Grand Total : ", SwingConstants.CENTER);
		grandTotalValue = new JLabel("0",SwingConstants.LEFT);
		filterBtn = new JButton("Filter By Driver");
		
	}

	@Override
	public void addComponent() {
		Border border = titleLbl.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		titleLbl.setBorder(new CompoundBorder(border, margin));
		
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 20));
		grandTotalLbl.setFont(new Font(grandTotalLbl.getFont().getName(), grandTotalLbl.getFont().getStyle(), 20));
		grandTotalValue.setFont(new Font(grandTotalValue.getFont().getName(), grandTotalValue.getFont().getStyle(), 20));
		
		
		top.add(titleLbl);
		midDetail.add(grandTotalLbl);
		midDetail.add(grandTotalValue);
		midPanel.add(scrollPane);
		midPanel.add(midDetail);
		bottom.add(filterBtn);
		add(top, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		loadData();
	}

	@Override
	public void addListener() {
		filterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				desktop.add(new FilterByDriverView(desktop));
				dispose();
			}
		});
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
		grandTotalValue.setText(OrderController.getInstance().totalFinishedTransaction().toString());
		dtm = new DefaultTableModel(data,header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}

}
