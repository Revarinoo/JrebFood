package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import core.view.View;

public class CartView extends View{

	JPanel mainPanel,northPanel,centerPanel,southPanel,deleteFromCartPanel;
	JLabel cartFoodIdLbl,cartTitleLbl;
	JTextField cartFoodIdTf;
	JButton deleteFromCartBtn,checkoutBtn;
	JTable cartTableData;
	DefaultTableModel cartDtm;
	JScrollPane cartTableScrollPane;
	
	public CartView() {
		super("Cart");
		this.width=600;
		this.height=600; 
		
		super.showForm();
	}

	@Override
	public void init() {
		mainPanel= new JPanel(new BorderLayout());
		northPanel = new JPanel(); 
		centerPanel = new JPanel(new GridLayout(2,1));
		southPanel = new JPanel(); 
		deleteFromCartPanel = new JPanel(new GridLayout(1,2));
		
		cartTitleLbl = new JLabel("Cart");
		
		// data template untuk test
		Object[] column = {"Food Id","Name","Price","Quantity"};
		
		Object[][] data = {
				{"1","Nasi Goreng",14000,1},
				{"4","Ayam Goreng",15000,2},
				{"5","Ayam Bakar",15000,1}
		};
		
		cartDtm = new DefaultTableModel(data,column);
		cartTableData = new JTable(cartDtm);
		cartTableScrollPane = new JScrollPane(cartTableData);
		
		cartFoodIdLbl=new JLabel("Food Id");
		cartFoodIdTf=new JTextField();
		cartFoodIdTf.setBackground(Color.white);
		cartFoodIdTf.setEditable(false); 
		
		deleteFromCartBtn = new JButton("Delete");
		checkoutBtn = new JButton("Checkout");
	}

	@Override
	public void addComponent() {
		northPanel.add(cartTitleLbl);
		
		centerPanel.add(cartTableScrollPane);
		centerPanel.add(deleteFromCartPanel);
		
		deleteFromCartPanel.add(cartFoodIdLbl);
		deleteFromCartPanel.add(cartFoodIdTf);
		
		southPanel.add(deleteFromCartBtn);
		southPanel.add(checkoutBtn);
		
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		add(mainPanel);
	}

	@Override
	public void addListener() {
		
		cartTableData.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				cartFoodIdTf.setText(cartTableData.getValueAt(cartTableData.getSelectedRow(),0).toString());
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
		
		deleteFromCartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// delete logic
				JOptionPane.showMessageDialog(CartView.this, "Delete from cart","Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		checkoutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//checkout logic
				// redirect to order page
				JOptionPane.showMessageDialog(CartView.this, "Checkout","Success", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
	}

}
