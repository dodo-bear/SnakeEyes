package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		this.setBackground(new Color(255, 255, 255, 255));
		this.setForeground(Color.RED);
		this.setOpacity(1);
		this.setSize(30, 30);
		
		//this.setContentPane(new DrawPane());
		
		this.setVisible(true);
		
		//Make it draggable
		FrameDragListener frameDragListener = new FrameDragListener(this);
	    this.addMouseListener(frameDragListener);
	    this.addMouseMotionListener(frameDragListener);
	}
	
	public void goTo(int x, int y) {
		this.setLocation(x + 2, y + 2);
		this.repaint();
	}
	
	public void repaint() {
		//Eyedropper functionality, uses the same method as the Skimmer but without averaging any colors.
		super.repaint();
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
		this.setBackground(new Color(screenshot.getRGB(this.getLocation().x-2, this.getLocation().y-2)));
		this.setForeground(getBackground());
		this.getContentPane().setBackground(getBackground());
		this.getContentPane().setForeground(getForeground());
	}
    
	public String toString() {
	    return "ColorPickerFrame{" +
	            "location=" + getLocation() +
	            ", size=" + getSize() +
	            ", color=" + getBackground() +
	            '}';
	}

}

//Bootleg dragging functionality
class FrameDragListener extends MouseAdapter {

    private final JFrame frame;
    private Point mouseDownCompCoords = null;

    public FrameDragListener(JFrame frame) {
        this.frame = frame;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDownCompCoords = null;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownCompCoords = e.getPoint();
    }

    public void mouseDragged(MouseEvent e) {
        Point currCoords = e.getLocationOnScreen();
        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
    }
}
