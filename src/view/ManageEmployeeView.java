package view;

import java.awt.BorderLayout;
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
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import controller.UserController;
import core.view.View;
import model.EmployeeModel;
import model.UserModel;

public class ManageEmployeeView extends View{

	JPanel topPanel, midDetail, midPanel , bottomPanel;
	JLabel titleLbl, nameLbl, idLbl , roleLbl;
	JTextField idTxt, nameTxt, roleTxt;
	JButton deleteBtn, hireBtn;
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scrollPane;
	
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
		GridLayout glMid = new GridLayout(3,2);
		midDetail = new JPanel(glMid);
		bottomPanel = new JPanel();
		table = new JTable();
		scrollPane = new JScrollPane(table);
		titleLbl = new JLabel("Employee List");
		idLbl = new JLabel("Employee ID");
		idTxt = new JTextField();
		
		
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
		topPanel.add(titleLbl);
		
		midDetail.add(idLbl);
		midDetail.add(idTxt);
		midDetail.add(roleLbl);
		midDetail.add(roleTxt);
		midDetail.add(nameLbl);
		midDetail.add(nameTxt);
		
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
				idTxt.setText(table.getValueAt(row, 0).toString());
				roleTxt.setText(table.getValueAt(row, 1).toString());
				nameTxt.setText(table.getValueAt(row, 2).toString());
				
				id = Integer.parseInt(idTxt.getText());
				role = roleTxt.getText();
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
