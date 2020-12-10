package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Closeable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import controller.EmployeeController;
import controller.UserController;
import core.view.View;
import model.EmployeeModel;
import model.UserModel;

import java.util.Calendar;

public class ManageEmployeeView extends View{

	JPanel topPanel, midDetail, midPanel , bottomPanel, dobPanel;
	JLabel titleLbl, nameLbl, idLbl , roleLbl, idValue, dobLbl, dateLbl, monthLbl, yearLbl;
	JTextField nameTxt, roleTxt, dateTxt, yearTxt;
	JButton deleteBtn, hireBtn;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	JComboBox<String> roleCB, monthCB;
	String[] listMonth = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	private Integer id;
	private String role, name;
	JDesktopPane desktop;
	
	public ManageEmployeeView(JDesktopPane desktop) {
		super("Manager");
		
		this.width = 600;
		this.height = 600;
		this.desktop = desktop;
		super.showForm();
	}

	@Override
	public void init() {
		topPanel = new JPanel();		
		GridLayout glTable = new GridLayout(2,1);
		glTable.setVgap(0);
		midPanel = new JPanel(glTable);
		GridLayout glMid = new GridLayout(4,2);
		midDetail = new JPanel(glMid);
		bottomPanel = new JPanel();
		table = new JTable();
		scrollPane = new JScrollPane(table);
		titleLbl = new JLabel("Employee List");
		idLbl = new JLabel("Employee ID");
		idValue = new JLabel("-");
		
		dateLbl = new JLabel("Day", SwingConstants.CENTER);
		monthLbl = new JLabel("Month", SwingConstants.CENTER);
		yearLbl = new JLabel("Year", SwingConstants.CENTER);
		dobPanel = new JPanel(new GridLayout(2,3));
		dobLbl = new JLabel("Date Of Birth");
		dateTxt = new JTextField();
		monthCB = new JComboBox<String>();
		yearTxt = new JTextField();
		
		roleCB = new JComboBox<String>();
		roleLbl = new JLabel("Role");
		roleTxt = new JTextField();
		
		nameLbl = new JLabel("Employee Name");
		nameTxt = new JTextField();
		
		deleteBtn = new JButton("Fire Employee");
		hireBtn = new JButton("Hire Employee");
		
	}

	@Override
	public void addComponent() {
		Border border = titleLbl.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		titleLbl.setBorder(new CompoundBorder(border, margin));
		
		monthCB.addItem("Jan");
		monthCB.addItem("Feb");
		monthCB.addItem("Mar");
		monthCB.addItem("Apr");
		monthCB.addItem("May");
		monthCB.addItem("Jun");
		monthCB.addItem("Jul");
		monthCB.addItem("Aug");
		monthCB.addItem("Sep");
		monthCB.addItem("Oct");
		monthCB.addItem("Nov");
		monthCB.addItem("Dec");
		
		roleCB.addItem("Chef");
		roleCB.addItem("Driver");
		dobPanel.add(dateLbl);
		dobPanel.add(monthLbl);
		dobPanel.add(yearLbl);
		dobPanel.add(dateTxt);
		dobPanel.add(monthCB);
		dobPanel.add(yearTxt);
		
		topPanel.add(titleLbl);
		
		midDetail.add(idLbl);
		midDetail.add(idValue);
		midDetail.add(roleLbl);
		midDetail.add(roleCB);
		midDetail.add(nameLbl);
		midDetail.add(nameTxt);
		midDetail.add(dobLbl);
		midDetail.add(dobPanel);
		
		midPanel.add(scrollPane);
		midPanel.add(midDetail);
		
		bottomPanel.add(hireBtn);
		bottomPanel.add(deleteBtn);
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		loadData();
	}

	@Override
	public void addListener() {
		table.addMouseListener(new MouseListener() {
			
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
				int row = table.getSelectedRow();
				idValue.setText(table.getValueAt(row, 0).toString());
				roleCB.setSelectedItem(table.getValueAt(row, 1));
				nameTxt.setText(table.getValueAt(row, 2).toString());
				
				id = Integer.parseInt(idValue.getText());
				role = (String) roleCB.getSelectedItem();
				name = nameTxt.getText();
			}
		});
		
		
	}
	
	public void loadData() {
		data = new Vector<>();
		header = new Vector<String>();
		header.add("Employee ID");
		header.add("Role");
		header.add("Name");
		header.add("DOB");
		header.add("Email");
		header.add("Status");
		
		
		Vector<EmployeeModel> listChef = EmployeeController.getInstance().viewAll(2);
		Vector<EmployeeModel> listDriver = EmployeeController.getInstance().viewAll(3);
		
		for (EmployeeModel u : listChef) {
			detail = new Vector<>();
			detail.add(u.getId().toString());
			detail.add("Chef");
			detail.add(u.getName());
			detail.add(u.getDOB().toString());
			detail.add(u.getEmail());
			detail.add(u.getStatus());
			
			data.add(detail);
		}
		
		for (EmployeeModel u : listDriver) {
			detail = new Vector<>();
			detail.add(u.getId().toString());
			detail.add("Driver");
			detail.add(u.getName());
			detail.add(u.getDOB().toString());
			detail.add(u.getEmail());
			detail.add(u.getStatus());
			
			data.add(detail);
		}
		
		dtm = new DefaultTableModel(data,header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(dtm);
		
	}

}
