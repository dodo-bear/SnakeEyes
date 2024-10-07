package com.mediafatigue.snakeeyes;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class GUIFrame extends JFrame implements KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3562206526467369967L;
	private JPanel mainpanel;

	public int x1, y1, x2, y2;
	public boolean donePointing, startedPointing = false;
	
	public GUIFrame(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		
		mainpanel = new JPanel();
		mainpanel.setBackground(new Color(200, 200, 200));
		add(mainpanel);
		
		JPanel coordsPanel = new JPanel();
		coordsPanel.setLayout(new BoxLayout(coordsPanel, BoxLayout.Y_AXIS));
		JLabel instructions = new JLabel("Please select two points with your mouse:");
		JLabel coordsLabel1 = new JLabel("Point 1: 0, 0");
		JLabel coordsLabel2 = new JLabel("Point 2: 0, 0");
		coordsPanel.add(instructions);
		coordsPanel.add(coordsLabel1);
		coordsPanel.add(coordsLabel2);
		mainpanel.add(coordsPanel);
		coordsPanel.setBackground(null);
		instructions.setForeground(Color.black);
		coordsLabel1.setForeground(Color.black);
		coordsLabel2.setForeground(Color.black);
		mainpanel.addKeyListener(this);
		System.out.println("Hi from before click");
		setVisible(true);
		while(!startedPointing){} //Silly use of a while loop to wait for the MyListener's response
		coordsLabel1.setText("Point 1: " + x1 + ", " + y1);
		while(!donePointing){} //Silly use of a while loop to wait for the MyListener's response
		coordsLabel2.setText("Point 2: " + x2 + ", " + y2);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Point arg0 = MouseInfo.getPointerInfo().getLocation();
		
		System.out.println("click");
        int x = (int) arg0.getX();
        int y = (int) arg0.getY();
        if(!startedPointing) {
        	x1 = x;
        	y1 = y;
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Point arg0 = MouseInfo.getPointerInfo().getLocation();
		
		System.out.println("click");
        int x = (int) arg0.getX();
        int y = (int) arg0.getY();
        if(!donePointing) {
        	x2 = x;
        	y2 = y;
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}