package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.OrderController;
import core.view.View;
import model.EmployeeModel;
import model.OrderModel;

public class FilterByDriverView extends View{

	JPanel topPanel, midPanel, midDetail, bottomPanel, formPanel;
	JLabel titleLbl, chooseDriverLbl;
	JButton filterBtn;
	Vector<Vector<String>> dataOrder, dataDriver;
	Vector<String> detailOrder, detailDriver, headerOrder, headerDriver;
	DefaultTableModel dtmOrder, dtmDriver;
	JTable tableOrder, tableDriver;
	JScrollPane scrollPane1, scrollPane2;
	JTextField driverTxt;
	JDesktopPane desktop;
	
	private Integer driverId;
	
	public FilterByDriverView(JDesktopPane desktop) {
		super("Filter Order By Driver");
		this.desktop = desktop;
		this.width = 600;
		this.height = 600;
		super.showForm();
	}

	@Override
	public void init() {
		topPanel = new JPanel();
		midPanel = new JPanel(new GridLayout(2,1));
		midDetail = new JPanel(new GridLayout(2,1));
		bottomPanel = new JPanel();
		GridLayout gl = new GridLayout(1,1);
		gl.setHgap(10);
		formPanel = new JPanel(gl);
		titleLbl = new JLabel("List Finished Order");
		driverTxt = new JTextField(SwingConstants.CENTER);
		tableOrder = new JTable();
		tableDriver = new JTable();
		scrollPane1 = new JScrollPane(tableOrder);
		scrollPane2 = new JScrollPane(tableDriver);
		chooseDriverLbl = new JLabel("Choose Driver", SwingConstants.CENTER);
		filterBtn = new JButton("Filter");
	}

	@Override
	public void addComponent() {
		Border border = titleLbl.getBorder();
		titleLbl.setBorder(new CompoundBorder(border, new EmptyBorder(10,10,20,10)));
		
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 20));
		chooseDriverLbl.setFont(new Font(chooseDriverLbl.getFont().getName(), chooseDriverLbl.getFont().getStyle(), 15));
		driverTxt.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 15));
		topPanel.add(titleLbl);
		formPanel.add(scrollPane2);
		formPanel.add(driverTxt);
		midDetail.add(chooseDriverLbl);
		midDetail.add(formPanel);
		midPanel.add(scrollPane1);
		midPanel.add(midDetail);
		bottomPanel.add(filterBtn);
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		loadDriver();
	}

	@Override
	public void addListener() {
		tableDriver.addMouseListener(new MouseListener() {
			
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
				int row = tableDriver.getSelectedRow();
				driverTxt.setText(tableDriver.getValueAt(row, 0).toString());
				
				driverId = Integer.parseInt(driverTxt.getText());
			}
		});
		
		filterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				viewAllHistory(driverId);
			}
		});
		
		
	}
	
	private void loadDriver() {
		dataDriver = new Vector<>();
		headerDriver = new Vector<String>();
		headerDriver.add("ID");
		headerDriver.add("Name");
		
		Vector<EmployeeModel> listDriver = EmployeeController.getInstance().listDriver();
		for(EmployeeModel d : listDriver) {
			detailDriver = new Vector<>();
			detailDriver.add(d.getId().toString());
			detailDriver.add(d.getName());
			
			dataDriver.add(detailDriver);
		}
		dtmDriver = new DefaultTableModel(dataDriver,headerDriver) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableDriver.setModel(dtmDriver);
	}
	
	private void viewAllHistory(Integer id) {
		dataOrder = new Vector<>();
		headerOrder = new Vector<String>();
		headerOrder.add("Order ID");
		headerOrder.add("Adress");
		headerOrder.add("Order Date");
		
		Vector<OrderModel> listOrderHistory = OrderController.getInstance().viewAllHistoryForDriver(id);
		for (OrderModel order : listOrderHistory) {
			detailOrder = new Vector<>();
			detailOrder.add(order.getOrderId().toString());
			detailOrder.add(order.getAddress());
			detailOrder.add(order.getDate().toString());
			
			dataOrder.add(detailOrder);
		}
		
		dtmOrder = new DefaultTableModel(dataOrder,headerOrder) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableOrder.setModel(dtmOrder);
	}

}
