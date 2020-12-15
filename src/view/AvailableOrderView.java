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
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import core.view.View;
import model.OrderModel;

public class AvailableOrderView extends View {
	/*
	 *  Class ini merupakan class untuk menampilkan list order yang dapat di ambil oleh driver
	 *  data order yang ditampilkan merupakan order yang memiliki status not accepted.
	 *  Pada halaman ini driver dapat memilih salah satu dari order yang tersedia dan 
	 *  memiliki validasi agar driver tidak bisa memasukan id secara manual namun harus 
	 *  memilih transaksi yang ada di tabel, serta akan ada error yang muncul ketika driver 
	 *  belum memilih transaksi kemudian akan ada confirmation dialog yang meminta konfirmasi dari driver. 
	 *  Setelah driver mengkonfirmasi maka status order akan terupdate menjadi accepted, dan halaman akan 
	 *  berpindah ke halaman user information untuk melihat detail data user. Setelah itu driver dapat menekan 
	 *  tombol proceed dan berpindah ke halaman order detail untuk melihat detail dari orderan yang ia ambil. 
	 */
	
	JPanel main,top,center,bottom,bottom1,bottom2,choosePanel;
	JLabel titleLabel,chooseLabel;
	JButton btnTake;
	JTable tableOrder;
	JScrollPane sp;
	JTextField chooseTxt;
	Vector<Vector<String>> dataOrder;
	Vector<String> header,detailOrder;
	JDesktopPane desktop;
	Vector<OrderModel> listOrder;
	Integer driverId;
	
	public AvailableOrderView(JDesktopPane desktop, Integer driverId) {
		super("Available Order");
		// TODO Auto-generated constructor stub
		this.width=600;
		this.height=600; 
		this.desktop = desktop;
		this.driverId = driverId;
		loadData();
		super.showForm();
	}

	private void loadData() {
		// Function untuk meload data untuk ditampilkan
		header.add("Order ID");
		header.add("Order Date");
		header.add("Address");
		listOrder = OrderController.getInstance().getAll();
		
		for (OrderModel model : listOrder) {
			OrderModel order = (OrderModel) model;
			detailOrder = new Vector<>();
			detailOrder.add(order.getOrderId().toString());
			detailOrder.add(order.getDate().toString());
			detailOrder.add(order.getAddress().toString());
			dataOrder.add(detailOrder);
		}
		DefaultTableModel dtm = new DefaultTableModel(dataOrder, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableOrder.setModel(dtm);
		if(listOrder.size() == 0) {
			bottom.setVisible(false);
		}
	}

	@Override
	public void init() {
		// Function untuk menginisialisasi component yang dibutuhkan
		main = new JPanel(new BorderLayout());
		top = new JPanel();
		bottom = new JPanel(new GridLayout(2,1));
		
		//Top
		titleLabel = new JLabel("Available Order");
		
		//Center
		center = new JPanel();
		tableOrder = new JTable();
		sp = new JScrollPane(tableOrder);
		header = new Vector<>();
		
		dataOrder = new Vector<>();
		header = new Vector<>();
		
		//Bottom
		//Bottom1
		chooseTxt = new JTextField();
		chooseTxt.setEditable(false);
		bottom1 = new JPanel();
		choosePanel= new JPanel(new GridLayout(1,2));
		chooseLabel = new JLabel("Choosen Order");
		
		//Bottom2
		bottom2 = new JPanel();
		btnTake = new JButton("Take Order");
	}

	@Override
	public void addComponent() {
		// Function untuk memasukan component yang dibutuhkan
		//Top
		top.add(titleLabel);
		main.add(top,BorderLayout.NORTH);
		
		//Center
		center.add(sp);
		main.add(center,BorderLayout.CENTER);
		
		//Bottom
		//Bottom1
		choosePanel.add(chooseLabel);
		choosePanel.add(chooseTxt);
		bottom1.add(choosePanel);
		
		//Bottom2
		bottom2.add(btnTake);
		bottom.add(bottom1);
		bottom.add(bottom2);
		
		
		main.add(bottom,BorderLayout.SOUTH);
		add(main);
	}

	@Override
	public void addListener() {
		// Function untuk menambahkan listener untuk table order dan button Take Order
		tableOrder.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				int row = tableOrder.getSelectedRow();
				chooseTxt.setText(tableOrder.getValueAt(row, 0).toString());
			}
		});
		
		btnTake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chooseTxt.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please choose Order!","Error Message", JOptionPane.ERROR_MESSAGE);
				}else {					
					int confirm = JOptionPane.showConfirmDialog(null,"Do you want to proceed order?", "Confirmation",JOptionPane.YES_NO_OPTION);
					if(confirm == 0) { // yes
						JOptionPane.showMessageDialog(null,"Take Order Success");
						Integer orderId = Integer.parseInt(chooseTxt.getText());
						
						OrderController temp = OrderController.getInstance();
						temp.takeOrder(orderId, driverId);
						
						OrderModel orderTemp = temp.getOne(orderId);
						
						desktop.add(new UserInformationView(desktop,orderTemp.getUserId(),orderId));
						dispose();
					}
				}
			}
		});
	}

}
