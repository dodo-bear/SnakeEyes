package com.mediafatigue.snakeeyes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IndicatorFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public IndicatorFrame() {
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setBackground(new Color(255, 255, 255, 0));
		this.setForeground(Color.RED);
		//this.setOpacity(0);
		this.setSize(40, 40);
		
		this.setContentPane(new DrawPane());
		
		this.setVisible(true);
	}
	
	public void goTo(int x, int y) {
		this.setLocation(x, y);
	}
	
	class DrawPane extends JPanel {
        public void paintComponent(Graphics g) {
        	g.setColor(Color.GREEN);
            g.fillRect(0, 0, 10, 10); // Draw on g here e.g.
        }
   }

}
