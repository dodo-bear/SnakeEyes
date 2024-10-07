package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ColorPickerFrame() {
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
        	Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    		Rectangle area = new Rectangle(0, 0, screenSize.width, screenSize.height);
    		BufferedImage screenshot = robot.createScreenCapture(area);
        	g.setColor(new Color(screenshot.getRGB(this.getParent().getLocation().x, this.getParent().getLocation().y)));
            g.fillRect(0, 0, 10, 10); // Draw on g here e.g.
        }
   }

}
