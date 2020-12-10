package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.sql.Date;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import controller.EmployeeController;
import core.view.DateFormat;
import core.view.View;
import model.EmployeeModel;


public class ManageEmployeeView extends View{

	JPanel topPanel, midDetail, midPanel , bottomPanel;
	JLabel titleLbl, nameLbl, idLbl , roleLbl, idValue, dobLbl, emailLbl, passwordLbl, statusLbl;
	JTextField nameTxt, roleTxt, emailTxt, passwordTxt;
	JButton deleteBtn, hireBtn;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	JComboBox<String> roleCB, statusCB;
	
	//add external jar di folder External Jar
	SqlDateModel dobModel;
	JDatePanelImpl dataPnl;
	JDatePickerImpl dobPicker;
	
	
	private Integer id, roleId;
	private String role, name, password, status, email;
	private Date DOB;
	JDesktopPane desktop;
	
	public ManageEmployeeView(JDesktopPane desktop) {
		super("Manager");
		
		this.width = 800;
		this.height = 900;
		this.desktop = desktop;
		super.showForm();
	}

	@Override
	public void init() {
		Properties dateToday = new Properties();
		dateToday.put("text.today", "Today");
		dateToday.put("text.month", "Month");
		dateToday.put("text.year", "Year");
		dobModel = new SqlDateModel();
		dataPnl = new JDatePanelImpl(dobModel,dateToday);
		dobPicker = new JDatePickerImpl(dataPnl, new DateFormat());
		
		topPanel = new JPanel();		
		GridLayout glTable = new GridLayout(2,1);
		glTable.setVgap(0);
		midPanel = new JPanel(glTable);
		GridLayout glMid = new GridLayout(7,2);
		glMid.setVgap(7);
		midDetail = new JPanel(glMid);
		bottomPanel = new JPanel();
		table = new JTable();
		scrollPane = new JScrollPane(table);
		titleLbl = new JLabel("Employee List");
		idLbl = new JLabel("Employee ID");
		idValue = new JLabel("-");
		
		dobLbl = new JLabel("Date Of Birth");
		
		
		roleCB = new JComboBox<>();
		roleLbl = new JLabel("Role");
		roleTxt = new JTextField();
		
		nameLbl = new JLabel("Employee Name");
		nameTxt = new JTextField();
		emailLbl = new JLabel("Employee Email");
		emailTxt = new JTextField();
		passwordLbl = new JLabel("Employee Password");
		passwordTxt = new JPasswordField();
		statusLbl = new JLabel("Status");
		statusCB = new JComboBox<>();
		deleteBtn = new JButton("Fire Employee");
		hireBtn = new JButton("Hire Employee");
		
	}

	@Override
	public void addComponent() {
		Border border = titleLbl.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		titleLbl.setBorder(new CompoundBorder(border, margin));
	
		statusCB.addItem("active");
		statusCB.addItem("inactive");
		roleCB.addItem("Chef");
		roleCB.addItem("Driver");
		
		
		topPanel.add(titleLbl);
		
		midDetail.add(idLbl);
		midDetail.add(idValue);
		midDetail.add(roleLbl);
		midDetail.add(roleCB);
		midDetail.add(nameLbl);
		midDetail.add(nameTxt);
		midDetail.add(dobLbl);
		midDetail.add(dobPicker);
		midDetail.add(emailLbl);
		midDetail.add(emailTxt);
		midDetail.add(passwordLbl);
		midDetail.add(passwordTxt);
		midDetail.add(statusLbl);
		midDetail.add(statusCB);
		
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
				
				dobModel.setValue(Date.valueOf(table.getValueAt(row, 3).toString()));
//				dobModel.setDate(year, month, day);
//				dateTxt.setText(table.getValueAt(row, 3).toString().substring(8, 10));
//				monthTxt.setText(table.getValueAt(row, 3).toString().substring(5, 7));
//				yearTxt.setText(table.getValueAt(row, 3).toString().substring(0,4));
				
				emailTxt.setText(table.getValueAt(row, 4).toString());
				statusCB.setSelectedItem(table.getValueAt(row, 5));
			}
		});
		
		
		hireBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				role = (String) roleCB.getSelectedItem();
				if(role.equals("Chef")) roleId = 2;
				else roleId = 3;
				name = nameTxt.getText();
				DOB= (Date) dobPicker.getModel().getValue();
				email = emailTxt.getText();
				password = passwordTxt.getText();
				status = (String) statusCB.getSelectedItem();
				
				if(EmployeeController.getInstance().createEmployee(roleId, name, DOB, email, password, status)) {
					JOptionPane.showMessageDialog(ManageEmployeeView.this, "Success\n"+
							name+"\n"+
							role+ "\n"+
							email + "\n"+
							DOB + "\n"+
							password + "\n"+
							status + "\n"
								);
				}
				else {
					JOptionPane.showMessageDialog(ManageEmployeeView.this, "Failed!");
				}
				loadData();
				
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
