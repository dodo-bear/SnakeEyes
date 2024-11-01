package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


public class Skimmer {

	//Grids for storing graphics values
	private char[][] grid;
	private Color[][] coGrid;
	
	private int columns, rows, corner1Height, corner1Distance, corner2Distance, corner2Height;
	
	private Color snakeColor;
	private Color fruitColor;
	
	private int rowHeight, colWidth;
	
	public Skimmer(int corner1Distance, int corner1Height, int corner2Distance, int corner2Height, int rows, int columns, Color snakeColor, Color fruitColor) {
		//Establish intervals between the centers of grid cells
		rowHeight = (int)((double)(corner2Height-corner1Height)/(double)rows);
		colWidth = (int)((double)(corner2Distance-corner1Distance)/(double)columns);
		
		grid = new char[rows][columns];
		coGrid = new Color[rows][columns];
		
		this.columns = columns;
		this.rows = rows;
		this.corner1Height = corner1Height;
		this.corner1Distance = corner1Distance;
		this.corner2Height = corner2Height;
		this.corner2Distance = corner2Distance;
		this.snakeColor = snakeColor;
		this.fruitColor = fruitColor;
	}
	
	//Turn screen data into useful array
	public BufferedImage captureToGrid() throws AWTException {
		//Create new buffered image
		Robot robot = new Robot();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle area = new Rectangle(0, 0, screenSize.width, screenSize.height);
		BufferedImage screenshot = robot.createScreenCapture(area);
		
		for(int curRow = 0; curRow < rows; curRow++) {
			for(int curCol = 0; curCol < columns; curCol++) {
				
				//Convert existing values to pixel coordinates for the cell's center
				int out = corner1Distance + (colWidth/2) + (curCol * colWidth);
				int down = corner1Height + (rowHeight/2) + (curRow * rowHeight);
				
				Color c = getAverageColor(screenshot, out, down);
				coGrid[curRow][curCol] = c;
				
				//Run comparisons and fill in cell in char grid, proceed to next
				if(fuzzyCompareColor(c, snakeColor, 0.55)) {
					grid[curRow][curCol] = 'S';
				} else if (fuzzyCompareColor(c, fruitColor, 0.65)) {
					grid[curRow][curCol] = 'F';
				} else {
					grid[curRow][curCol] = 'B';
				}
				
			}
		}
		return screenshot.getSubimage(corner1Distance, corner1Height, corner2Distance - corner1Distance, corner2Height - corner1Height);
	}
	
	//Condense cell into single color value
	public Color getAverageColor(BufferedImage image, int x, int y) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        int totalRed = 0;
        int totalGreen = 0;
        int totalBlue = 0;
        int count = 0;

        // Loop through the entire cell around the given coordinates (1x1 worked somewhat, but testing proved it caused strange edge cases)
        for (int i = -(colWidth/2)+1; i <= (colWidth/2)-1; i++) {
            for (int j = -(rowHeight/2)+1; j <= (rowHeight/2)-1; j++) {
                int newX = x + i;
                int newY = y + j;

                // Check if the new coordinates are within the bounds of the image (account for snake boards anywhere, even on the edges of the screen)
                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    Color color = new Color(image.getRGB(newX, newY));
                    totalRed += color.getRed();
                    totalGreen += color.getGreen();
                    totalBlue += color.getBlue();
                    count++;
                }
            }
        }

        // Calculate the average color
        if (count > 0) {
            return new Color(totalRed / count, totalGreen / count, totalBlue / count);
        } else {
            return Color.BLACK; // Return black if no pixels were counted
        }
    }
	
	public char[][] getGrid(){
		return grid;
	}
	
	public Color[][] getCoGrid(){
		return coGrid;
	}
	
	//System I designed for motion vectors in a physics engine, retooled to compare colors.
	public boolean fuzzyCompareColor(Color a, Color b, double range) {
		
        int rDiff = a.getRed() - b.getRed();
        int gDiff = a.getGreen() - b.getGreen();
        int bDiff = a.getBlue() - b.getBlue();
        
        //Analyse values as if Red Green Blue Alpha were X Y Z Magnitude, impose cap to account for color space
        double distance = Math.sqrt(rDiff * rDiff + gDiff * gDiff + bDiff * bDiff);
        double maxDistance = Math.sqrt(3 * 255 * 255);
        double similarity = 1 - (distance / maxDistance);

        return similarity >= range;
	}
	
	public String toString() {
	    return "Skimmer{" +
	            "columns=" + columns +
	            ", rows=" + rows +
	            ", snakeColor=" + snakeColor +
	            ", fruitColor=" + fruitColor +
	            ", rowHeight=" + rowHeight +
	            ", colWidth=" + colWidth +
	            '}';
	}
}
