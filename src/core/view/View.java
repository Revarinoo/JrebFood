package core.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public abstract class View extends JInternalFrame{
	
	protected int width;
	protected int height;

	public View(String inFrameName) {
		super(inFrameName,false,true);
		
		init();
		addComponent();
		addListener();
	}
	
	public void showForm(){		
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
	}
	
	public abstract void init();
	public abstract void addComponent();
	public abstract void addListener();

}
