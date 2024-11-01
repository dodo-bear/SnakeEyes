package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

public class MainClass {

	private static Skimmer sk;
	private static GUIFrame frame;
	private static Thread t;
	private static BufferedImage display;
	
	private static int headx, heady, dir, destx, desty;
	
	public static void main(String[] args) {
		
		frame = new GUIFrame("SnakeEyes Controls");
	}
	
	public static void continueSkimmer() {
		//Prepare the grid
		//frame.initGrid(sk);
		display = null;
		t = new Thread();
		try {
			display = sk.captureToGrid();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.refreshImage(display);
		
		frame.setBounds(frame.getWidth(), frame.getHeight() + frame.getBImage().getHeight());
				
		//Main loop
		while(true) {
			try {
				//Refresh all graphics within window
				frame.refreshMouseCoords();
				display = sk.captureToGrid();
				frame.refreshImage(display);
				//frame.refreshGrid(sk);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			//If bot is running, tell it to make decisions and update values accordingly, otherwise prepare for bot activation.
			if(frame.isRunning()) {				
				botLogic(sk.getGrid());
				frame.refreshHeadCoords(headx, heady);
			} else {
				dir = frame.getDirection();
				headx = frame.getHeadX();
				heady = frame.getHeadY();
				destx = headx;
				desty = heady;
			}
		}
	}
	
	//Predict next space and check whether it is safe
	private static boolean canMoveForward() {
        int nextX = headx, nextY = heady;
        if (dir == 0) nextX--; // Up
        else if (dir == 1) nextY++; // Right
        else if (dir == 2) nextX++; // Down
        else if (dir == 3) nextY--; // Left
        
        return isValidMove(nextX, nextY);
    }
	
	//Convenience method to make code more legible
	private static void turnLeft() {
		FakeKeyboard.turn(false, dir);
	}
	
	//See above
	private static void turnRight() {
		FakeKeyboard.turn(true, dir);
	}
	
	public static GUIFrame getFrame() {
		return frame;
	}
	
	//Decision making/collision avoidance
	private static void botLogic(char[][] grid) {
		if(sk.getGrid()[destx][desty] == 'S') {//If reached chosen destination
			headx = destx;
			heady = desty;
			System.out.println("Reached " + headx + ", " + heady);
			//Rejoice, we're here!
			if (canMoveForward()) {//If waiting is safe, wait.
				move();
            
			} else {
				// Try to turn left
				dir = (dir + 3)%4;
				if (canMoveForward()) {
					// If it works, great. Go ahead.
					dir = (dir + 1)%4;
					turnLeft();
					dir = (dir + 3)%4;
					System.out.println("Turning left, direction: " + dir);
					move();
				} else {
					//Otherwise go right, change direction twice to correct for left turn.
					dir = (dir + 1)%4;
					turnRight();
					dir = (dir + 1)%4;
					System.out.println("Turning right, direction: " + dir);
					move();
				}
			}
		}
	}
	
	private static void move() {
		int nextX = headx, nextY = heady;
        if (dir == 0) nextX--; // Up
        else if (dir == 1) nextY++; // Right
        else if (dir == 2) nextX++; // Down
        else if (dir == 3) nextY--; // Left
        //Wait and see if we get there
		destx = nextX;
		desty = nextY;
		System.out.println("Moving further to " + destx + ", " + desty);
	}
	
	//Is this space a wall or body part?
	private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < sk.getGrid().length && y >= 0 && y < sk.getGrid()[0].length && sk.getGrid()[x][y] != 'S';
    }
	
	public static void setSkimmer(Skimmer s) {
		sk = s;
	}
	
	public static int getHeadX() {
		return headx;
	}
	
	public static int getHeadY() {
		return heady;
	}
}