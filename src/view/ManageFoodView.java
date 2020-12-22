package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.FoodController;
import core.view.View;
import model.FoodModel;

public class ManageFoodView extends View{
	
	JPanel topPanel, midPanel, midDetail, botPanel, radioPanel;
	JLabel titleLbl, nameLbl, descLbl, priceLbl, statusLbl;
	JTextField nameTxt, descTxt, priceTxt;
	JRadioButton availableRad, notRad;
	ButtonGroup classGroup;
	JButton addBtn, updateStatusBtn, deleteBtn;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane sp;
	
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer id, price;
	String name, description, status;

	public ManageFoodView() {
		super("Food List");
		this.width=600;
		this.height=600;
		
		super.showForm();
	}

	@Override
	public void init() {
		GridLayout gridBig = new GridLayout(2, 1);
		gridBig.setVgap(0);
		
		GridLayout gridMid = new GridLayout (4, 2);
		GridLayout gridBot = new GridLayout (1, 3);
		
		topPanel = new JPanel(new FlowLayout());
		midPanel = new JPanel(gridBig);
		midDetail = new JPanel(gridMid);
		botPanel = new JPanel(gridBot);
		radioPanel = new JPanel();
		
		table = new JTable();
		sp = new JScrollPane(table);
		
		
		titleLbl = new JLabel("Food Menu List");
		
		
		 nameLbl = new JLabel("Name");
		 nameTxt = new JTextField();
		 
		 descLbl = new JLabel("Description");
		 descTxt = new JTextField();
			 
		 priceLbl = new JLabel("Price");
		 priceTxt = new JTextField("0");
		 

		 
		 statusLbl = new JLabel("Status");
		 statusLbl.setVisible(false);
		 availableRad = new JRadioButton("Available");
		 availableRad.setVisible(false);
		 notRad = new JRadioButton("Unavailable");
		 notRad.setVisible(false);
		 radioPanel.add(availableRad);
		 radioPanel.add(notRad);
		 classGroup = new ButtonGroup();
		 classGroup.add(availableRad);
		 classGroup.add(notRad);
		 
		 
		 updateStatusBtn = new JButton("Update Availability");
		 addBtn = new JButton("Add Food");
		 deleteBtn = new JButton("Delete Food");
		
	}

	@Override
	public void addComponent() {
		topPanel.add(titleLbl);
		
		midDetail.add(nameLbl);
		midDetail.add(nameTxt);
		
		midDetail.add(priceLbl);
		midDetail.add(priceTxt);
		
		midDetail.add(descLbl);
		midDetail.add(descTxt);
		
		midDetail.add(statusLbl);
		midDetail.add(radioPanel);
		
		midPanel.add(sp);
		midPanel.add(midDetail);

		
		botPanel.add(updateStatusBtn);
		botPanel.add(addBtn);
		botPanel.add(deleteBtn);
		
		
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
		
		loadData();
		
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
				
				nameTxt.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				descTxt.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				priceTxt.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				
				statusLbl.setVisible(true);
				notRad.setVisible(true);
				availableRad.setVisible(true);
				
				id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				description = descTxt.getText();
				name = nameTxt.getText();
				price = Integer.parseInt(priceTxt.getText());
				
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
		
		availableRad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				status = "available";
				
			}
		});
		
		notRad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				status = "unavailable";
				
			}
		});
				
		updateStatusBtn.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(nameTxt.getText().equals("") || descTxt.getText().equals("") || priceTxt.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "Please choose food to update!","Error Message", JOptionPane.ERROR_MESSAGE);
				}
				else if (status == null) {
					JOptionPane.showMessageDialog(null, 
							"Cannot change this food availability",
							"Error Message", 
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					int confirm = JOptionPane.showConfirmDialog(ManageFoodView.this,
									"Name : "+name+
									"\nPrice : "+price+
									"\nDesc : "+description+"\n"
									, "Are you sure to change availability of this food?\n\n"
									, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
					if (confirm == JOptionPane.YES_OPTION) {
						
						if (!(FoodController.getInstance().changeStatus(id, status))) {
							JOptionPane.showMessageDialog(null, 
									"Cannot change this food availability",
									"Error Message", 
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(ManageFoodView.this,
									"Change Availability Complete"						
								);
							loadData();
						}
					}
				}


				
			}
		});		
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 name = nameTxt.getText();
				 description = descTxt.getText();
			
				 price = Integer.parseInt(priceTxt.getText());
				 status = "available";
				 
				 if (!FoodController.getInstance().addFood(name, price, description)) {
						JOptionPane.showMessageDialog(null, 
								"Wrong Insert Data",
								"Error Message", 
								JOptionPane.ERROR_MESSAGE);
				 }
				 else {
						JOptionPane.showMessageDialog(ManageFoodView.this,
								
								"Name : "+name+
								"\nPrice : "+price+
								"\nDesc : "+description+
								
								"\nAvailability : "+status+
								"\n\nAdd Food Complete"	
						);
						
						loadData();
				 }
				
				

				
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				if(nameTxt.getText().equals("") || descTxt.getText().equals("") || priceTxt.getText().equals("0")) {
					JOptionPane.showMessageDialog(null, "Please choose food to delete!","Error Message", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int confirm = JOptionPane.showConfirmDialog(new ManageEmployeeView(), 					
							
							"Name : "+name+
							"\nPrice : "+price+
							"\nDesc : "+description+
							"\n", "Are you sure to remove this food?"
					, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);	
					
					if (confirm == JOptionPane.YES_OPTION) {
						
						if (FoodController.getInstance().deleteFood(id) == false) {
							JOptionPane.showMessageDialog(ManageFoodView.this,
								"Cannot delete. The Food has been ordered by someone"

							);
						}
						else {
							JOptionPane.showMessageDialog(ManageFoodView.this,
								"Delete Complete"
							);
						}
						
						loadData();
					}
				}

			}
		});	
		
	}
	
	private void loadData() {
		data = new Vector<>();
		header = new Vector<>();
		header.add("ID");
		header.add("Name");
		header.add("Description");
		header.add("Price");
		header.add("Availability");
		
		Vector<FoodModel> listfood = FoodController.getInstance().viewAll();
		
		for (FoodModel model : listfood) {
			FoodModel food = (FoodModel) model;
			
			detail = new Vector<>();
			detail.add(food.getFoodId().toString());
			detail.add(food.getName());
			detail.add(food.getDescription());
			detail.add(food.getPrice().toString());
			detail.add(food.getStatus());
			
			data.add(detail);
		}
		
			
		dtm = new DefaultTableModel(data, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table.setModel(dtm);
		
	}

}
