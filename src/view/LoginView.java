package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import core.view.View;

public class LoginView extends View{
	
	JPanel top, mid, bottom;
	JLabel titleLbl, emailLbl, passwordLbl;
	JTextField emailTxt, passwordTxt;
	JButton login;

	public LoginView() {
		super("Authentication");
		
		this.width = 400;
		this.height = 300;
		super.showForm();
	}

	@Override
	public void init() {
		top = new JPanel();
		
		GridLayout gl = new GridLayout(3,2);
		gl.setVgap(5);
		mid = new JPanel(gl);
		bottom = new JPanel();
		
		titleLbl = new JLabel("Login");
		emailLbl = new JLabel("Email");
		passwordLbl = new JLabel("Password");		
		emailTxt = new JTextField();
		passwordTxt = new JPasswordField();
		
		login = new JButton("Login");
		
	}

	@Override
	public void addComponent() {
		
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), titleLbl.getFont().getStyle(), 20));
		emailLbl.setFont(new Font(emailLbl.getFont().getName(), emailLbl.getFont().getStyle(), 20));
		passwordLbl.setFont(new Font(passwordLbl.getFont().getName(), passwordLbl.getFont().getStyle(), 20));
		login.setFont(new Font(login.getFont().getName(), login.getFont().getStyle(), 20));

		
		emailTxt.setBounds(70,30,150,10);
		passwordTxt.setBounds(70,65,150,10);
		login.setBounds(110,100,80,20);
		
		Border border = titleLbl.getBorder();
		Border margin = new EmptyBorder(10,10,20,10);
		titleLbl.setBorder(new CompoundBorder(border, margin));
		
		top.add(titleLbl);
		
		mid.add(emailLbl);
		mid.add(emailTxt);
		mid.add(passwordLbl);
		mid.add(passwordTxt);
		
		bottom.add(login);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}

}
