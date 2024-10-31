package com.mediafatigue.snakeeyes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class fff extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public fff(int x, int y) {
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setBackground(new Color(255, 255, 255, 0));
		this.setForeground(Color.RED);
		this.setSize(40, 40);
		this.setContentPane(new DrawPane());
		this.setVisible(true);
		
		//Undecorated frames aren't supposed to be draggable, so this turns the entire frame into its own header.
		FrameDragListener frameDragListener = new FrameDragListener(this);
	    this.addMouseListener(frameDragListener);
	    this.addMouseMotionListener(frameDragListener);
	}
	
	public void goTo(int x, int y) {
		this.setLocation(x, y);
	}
	
	//Content pane specifically for color manipulation
	class DrawPane extends JPanel {
		private Color c = Color.GREEN;
        public void paintComponent(Graphics g) {
        	g.setColor(c);
            g.fillRect(0, 0, 10, 10); // Draw on g here e.g.
        }
        
        public void setColor(Color c) {
        	this.c = c;
        }
   }

	public String toString() {
	    return "IndicatorFrame{" +
	            "location=" + getLocation() +
	            ", Color=" + getBackground() +
	            '}';
	}
}
