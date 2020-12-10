package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ChefAddFoodView extends View{
	
	JPanel topPanel, midPanel, midDetail, botPanel, radioPanel;
	JLabel titleLbl, nameLbl, descLbl, priceLbl, statusLbl;
	JTextField nameTxt, descTxt, priceTxt;
	JRadioButton availableRad, notRad;
	ButtonGroup classGroup;
	JButton insertBtn;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane sp;
	
	Vector<Vector<String>> data;
	Vector<String> detail, header;
	Integer id, price;
	String name, description, status;

	public ChefAddFoodView() {
		
		super("Food List");
		this.width=600;
		this.height=600;
		
		super.showForm();
	}

	@Override
	public void init() {
		GridLayout gridBig = new GridLayout(2, 1);
		gridBig.setVgap(0);
		
		GridLayout gridMid = new GridLayout (3, 2);
		GridLayout gridBot = new GridLayout (1, 2);
		
		topPanel = new JPanel(new FlowLayout());
		midPanel = new JPanel(gridBig);
		midDetail = new JPanel(gridMid);
		botPanel = new JPanel(gridBot);

		table = new JTable();
		sp = new JScrollPane(table);
		
		
		titleLbl = new JLabel("Food Menu List");
		
		
		 nameLbl = new JLabel("Name");
		 nameTxt = new JTextField();
		 
		 descLbl = new JLabel("Description");
		 descTxt = new JTextField();
			 
		 priceLbl = new JLabel("Price");
		 priceTxt = new JTextField();
		 
		 
		 insertBtn = new JButton("Insert Food");

		
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
				
		midPanel.add(sp);
		midPanel.add(midDetail);

		
		botPanel.add(insertBtn);

		
		
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
		
		loadData();		
	}


		

	@Override
	public void addListener() {
		
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 name = nameTxt.getText();
				 description = descTxt.getText();
				 price = Integer.parseInt(priceTxt.getText());
				 status = "available";
				 FoodController.getInstance().addFood(name, description, price);
				
				JOptionPane.showMessageDialog(ChefAddFoodView.this,
					
						"Name : "+name+
						"\nPrice : "+price+
						"\nDesc : "+description+
						
						"\nAvailability : "+status+
						"\n\nAdd Food Complete"
						
				);
				
				loadData();
				
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
		
		Vector<FoodModel> listfood = FoodController.getInstance().getAll();
		
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
		
			
		dtm = new DefaultTableModel(data, header);
		
		table.setModel(dtm);
		
	}
	
	

}
