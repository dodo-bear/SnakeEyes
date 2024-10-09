package com.mediafatigue.snakeeyes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FakeKeyboard {
	
	//Press and release the mouse at these coordinates in screen space
	public static void clickHere(int x, int y) {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.mouseMove(x, y);
		rb.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		rb.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	//Converts to character-relative movement based on the facing input, which is the current direction.
	public static void turn(boolean dir, int facing) {
		int direction = facing;
		switch(direction) {
		case 0:
			if(dir) {
				rightArrow();
			} else {
				leftArrow();
			}
			break;
			
		case 1:
			if(dir) {
				downArrow();
			} else {
				upArrow();
			}
			break;
			
		case 2:
			if(dir) {
				leftArrow();
			} else {
				rightArrow();
			}
			break;
		
		case 3:
			if(dir) {
				upArrow();
			} else {
				downArrow();
			}
		}
	}
	private static void leftArrow() {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.keyPress(KeyEvent.VK_LEFT);
		rb.keyRelease(KeyEvent.VK_LEFT);
		System.out.println("Left Arrow Pressed");
	}
	
	//Public because it is tapped once to start the game
	public static void rightArrow() {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.keyPress(KeyEvent.VK_RIGHT);
		rb.keyRelease(KeyEvent.VK_RIGHT);
		System.out.println("Right Arrow Pressed");
	}
	
	private static void upArrow() {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.keyPress(KeyEvent.VK_UP);
		rb.keyRelease(KeyEvent.VK_UP);
		System.out.println("Up Arrow Pressed");
	}
	
	private static void downArrow() {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rb.keyPress(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_DOWN);
		System.out.println("Down Arrow Pressed");
	}
	
}