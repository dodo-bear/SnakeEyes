package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


public class Skimmer {

	private char[][] grid;
	
	private int ySize;
	private int xSize;
	
	private Color snakeColor;
	private Color fruitColor;
	
	public Skimmer(int x1, int y1, int x2, int y2, int xSize, int ySize, Color snakeColor, Color fruitColor) {
		int xIncrement = (x2-x1)/xSize;
		int yIncrement = (y2-y1)/ySize;
		grid = new char[xSize][ySize];
		this.ySize = ySize;
		this.xSize = xSize;
		this.snakeColor = snakeColor;
		this.fruitColor = fruitColor;
	}
	
	public void captureToGrid() throws AWTException {
		
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle area = new Rectangle(0, 0, screenSize.width, screenSize.height);
		BufferedImage screenshot = robot.createScreenCapture(area);
		
		for(int currentY = 0; currentY < ySize; currentY++) {
			for(int currentX = 0; currentX < xSize; currentX++) {
				Color c = new Color(screenshot.getRGB(currentX, currentY));
				
				if(c == snakeColor) {
					grid[currentX][currentY] = 'S';
				} else if (c ==fruitColor) {
					grid[currentX][currentY] = 'F';
				} else {
					grid[currentX][currentY] = 'B';
				}
			}
		}
	}
	
	public char[][] getGrid(){
		return grid;
	}
	
}
