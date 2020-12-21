package core.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class MainView extends JFrame {

	public MainView() {
		// TODO Auto-generated constructor stub
		initialize();
		addComponent();
		addListener();
	}
	
	public void showForm(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setSize(screenSize.width, screenSize.height);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(true);
	}
	
	public abstract void initialize();
	public abstract void addComponent();
	public abstract void addListener();
}
