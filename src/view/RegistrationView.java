package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import core.view.View;

public class RegistrationView extends View{

	JPanel top, mid, bottom;
	JLabel titleLbl, nameLbl, passwordLbl, addressLbl, phoneLbl, emailLbl;
	JTextField nameTxt, passwordTxt, phoneTxt, emailTxt,addressTxt;
	JCheckBox aggree;
	JButton register;
	
	public RegistrationView() {
		super("Create an account");
		
		this.width = 600;
		this.height = 600;
		super.showForm();
	}

	@Override
	public void init() {
		top = new JPanel();
		GridLayout gl = new GridLayout(5,2);
		gl.setVgap(45);
		mid = new JPanel(gl);
		bottom = new JPanel();
		
		titleLbl = new JLabel("Registration");
		nameLbl = new JLabel("Name");
		passwordLbl = new JLabel("Password");
		phoneLbl = new JLabel("Phone");
		emailLbl = new JLabel("Email");
		addressLbl = new JLabel("Address");
		
		nameTxt = new JTextField();
		passwordTxt = new JPasswordField();
		phoneTxt = new JTextField();
		emailTxt = new JTextField();
		addressTxt = new JTextField();
		
		register = new JButton("Register");
	}

	@Override
	public void addComponent() {
		
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 20));
		nameLbl.setFont(new Font(nameLbl.getFont().getName(), nameLbl.getFont().getStyle(), 20));
		passwordLbl.setFont(new Font(passwordLbl.getFont().getName(), passwordLbl.getFont().getStyle(), 20));
		phoneLbl.setFont(new Font(phoneLbl.getFont().getName(), phoneLbl.getFont().getStyle(), 20));
		emailLbl.setFont(new Font(emailLbl.getFont().getName(), emailLbl.getFont().getStyle(), 20));
		addressLbl.setFont(new Font(addressLbl.getFont().getName(), addressLbl.getFont().getStyle(), 20));
		register.setFont(new Font(register.getFont().getName(), register.getFont().getStyle(), 20));
		
		top.add(titleLbl);
		
		mid.add(nameLbl);
		mid.add(nameTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		mid.add(phoneLbl);
		mid.add(phoneTxt);
		mid.add(emailLbl);
		mid.add(emailTxt);
		mid.add(addressLbl);
		mid.add(addressTxt);
		mid.setBorder(new EmptyBorder(50, 50, 50,50));
		bottom.add(register);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = nameTxt.getText();
				String userPassword = passwordTxt.getText();
				String userAddress = addressTxt.getText(); 
				String userPhone = phoneTxt.getText();
				String userEmail = emailTxt.getText();
				
				if(UserController.getInstance().createAccount(userName, userAddress, userEmail, userPhone, userPassword)) {
					JOptionPane.showMessageDialog(RegistrationView.this, "Registration Success!");
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(RegistrationView.this, "Registration Failed"+
																			"\nData cannot be empty!"+
																			"\nEmail must contain @ and '.'"+
																			"\nPhone Number length minimum 11 and maximum 12");
				}
				
			}
		});
	}

}
