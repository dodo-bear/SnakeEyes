package com.mediafatigue.snakeeyes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3562206526467369967L;
	private JPanel mainpanel;

	private int x1, y1, x2, y2, xSize, ySize, headx, heady, direction;
	private Color snakeColor;
	private Color fruitColor;
	private JPanel grid;
	private JLabel mouseCoords;
	
	private JTextField coordsHeadx, coordsHeady;
	
	private boolean amIRunning;
	
	private JLabel[][] labelGrid;
	
	public GUIFrame(String title) {
		amIRunning = false;
		labelGrid = new JLabel[0][0];
		grid = new JPanel();

		//JFrame initialization
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 650);
		
		//Root panel
		mainpanel = new JPanel();
		mainpanel.setBackground(new Color(200, 200, 200));
		add(mainpanel);
		
		mouseCoords = new JLabel("Mouse Coordinates: 0, 0");
		
		JPanel coordsPanel = new JPanel();
		coordsPanel.setLayout(new BoxLayout(coordsPanel, BoxLayout.Y_AXIS));
		
		JLabel coordsLabel4 = new JLabel("Grid Size (Rows, Columns): ");
		
		//Spawn and configure all the draggable frames
		ColorPickerFrame cf = new ColorPickerFrame();
		cf.goTo(50, 50);
		
		JPanel skButtonPanel = new JPanel();
		JButton spawnSkimmer = new JButton("Start Reading Data");
		skButtonPanel.add(spawnSkimmer);

		//Panel & labels for color display
		JPanel colorPanel = new JPanel();
		JLabel colabel1 = new JLabel("Snake Color: ");
		JLabel colabel2 = new JLabel("Fruit Color: ");
		JPanel color1 = new JPanel();
		JPanel color2 = new JPanel();
		color1.setPreferredSize(new Dimension(80, 20));
		color2.setPreferredSize(new Dimension(80, 20));
		color1.setBackground(Color.BLACK);
		color2.setBackground(Color.BLACK);
		colorPanel.add(colabel1);
		colorPanel.add(color1);
		colorPanel.add(colabel2);
		colorPanel.add(color2);
		
		//Corner coordinate panels/labels
		JLabel instructions = new JLabel("Please select two sets of pixel coordinates:");
		JLabel coordsLabel1 = new JLabel("Point 1: 0, 0");
		JLabel coordsLabel2 = new JLabel("Point 2: 0, 0");
		JPanel coordsPanel1 = new JPanel();
		coordsPanel1.add(coordsLabel1);
		JPanel coordsPanel2 = new JPanel();
		coordsPanel2.add(coordsLabel2);
		
		//Panel for grid size in cells
		JPanel coordsPanel4 = new JPanel();
		JTextField coords4x = new JTextField("15", 6);
		JTextField coords4y = new JTextField("17", 6);
		coordsPanel4.add(coordsLabel4);
		coordsPanel4.add(coords4x);
		coordsPanel4.add(coords4y);
		
		//Panel for the snake's head location at start
		JPanel headCoordsPanel = new JPanel();
		coordsHeadx = new JTextField("7", 6);
		coordsHeady = new JTextField("4", 6);
		JLabel headCoordsLabel = new JLabel("Head Coordinates: ");
		headCoordsPanel.add(headCoordsLabel);
		headCoordsPanel.add(coordsHeadx);
		headCoordsPanel.add(coordsHeady);
		
		//Snake starting direction inputs
		JPanel headDirPanel = new JPanel();
		JTextField headDir = new JTextField("1", 6);
		JLabel headDirLabel = new JLabel("Head Direction: (0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT)");
		headDirPanel.add(headDirLabel);
		headDirPanel.add(headDir);
		
		//Begin stacking into the root panel
		coordsPanel.add(mouseCoords);
		coordsPanel.add(instructions);
		coordsPanel.add(coordsPanel1);
		coordsPanel.add(coordsPanel2);
		
		//Set corner coordinates button/actionlistener
		JButton coordSet = new JButton("Set Area Coordinates");
		JPanel coordSetButtonPanel = new JPanel();
		coordSetButtonPanel.add(coordSet);
		coordSet.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				Rectangle r = SelectionBox.select();
				
				x1 = r.x;
				x2 = r.x + r.width;
				y1 = r.y;
				y2 = r.y + r.height;
				coordsLabel1.setText("Point 1: " + x1 + ", " + y1);
	            coordsLabel2.setText("Point 2: " + x2 + ", " + y2);
				
			           /* x1 = ind1.getLocation().x;  
			            x2 = Integer.max(ind2.getLocation().x, x1);  
			            y1 = ind1.getLocation().y;  
			            y2 = Integer.max(ind2.getLocation().y, y1); 
			            ind1.goTo(x1, y1);
			            ind2.goTo(x2, y2);
			            
			            coordsLabel1.setText("Point 1: " + x1 + ", " + y1);
			            coordsLabel2.setText("Point 2: " + x2 + ", " + y2);*/
			        }  
			    });  
		
		//Set color buttons
		JButton pickColor = new JButton("Set Snake Color");
		JButton pickColor2 = new JButton("Set Fruit Color");
		
		pickColor.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            cf.repaint();
			            snakeColor = cf.getBackground();
			            color1.setBackground(snakeColor);
			        }  
			    });
		
		pickColor2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            cf.repaint();
			            fruitColor = cf.getBackground();
			            color2.setBackground(fruitColor);
			        }  
			    });
		
		//ActionListener that starts the Skimmer and initializes the grid
		spawnSkimmer.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
						xSize = Integer.parseInt(coords4x.getText());
						ySize = Integer.parseInt(coords4y.getText());
			            MainClass.setSkimmer(new Skimmer(x1, y1, x2, y2, xSize, ySize, color1.getBackground(), color2.getBackground()));
			            direction = Integer.parseInt(headDir.getText());
			            headx = Integer.parseInt(coordsHeadx.getText());
			            heady = Integer.parseInt(coordsHeady.getText());
			        }  
			    });
		
		JPanel runButtonPanel = new JPanel();
		JButton runButton = new JButton("Start SnakeEyes");
		runButtonPanel.add(runButton);
		
		//Bot on/off button
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(amIRunning) {
				 amIRunning = false;
				} else {
				FakeKeyboard.clickHere((x2-x1)/2 + x1, (y2-y1)/2 + y1);
				FakeKeyboard.rightArrow();
				amIRunning = true;
				}
			}
		});
		
		//Finish stacking all UI elements
		coordsPanel.add(coordSetButtonPanel);
		coordsPanel.add(coordsPanel4);
		coordsPanel.add(colorPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(pickColor);
		buttonPanel.add(pickColor2);
		coordsPanel.add(buttonPanel);
		coordsPanel.add(headCoordsPanel);
		coordsPanel.add(headDirPanel);
		coordsPanel.add(skButtonPanel);
		coordsPanel.add(runButtonPanel);
		coordsPanel.add(grid);
		mainpanel.add(coordsPanel);
		coordsPanel.setBackground(null);
		instructions.setForeground(Color.black);
		coordsLabel1.setForeground(Color.black);
		coordsLabel2.setForeground(Color.black);
		setVisible(true);
		
	}
	
	//Generate and store all JLabels in the debug view and flow them into the GridLayout
	public void initGrid(Skimmer sk) {
		int x = sk.getGrid().length;
		int y = sk.getGrid()[0].length;
		labelGrid = new JLabel[x][y];
        grid.setLayout(new GridLayout(x, y));
        for (int i = 0; i < x; i++) {
            for (int n = 0; n < y; n++) {
            	labelGrid[i][n] = new JLabel("HI");
            	grid.add(labelGrid[i][n]);
            }
        }
	}
	
	//Update all debug view JLabels
	public void refreshGrid(Skimmer sk) {
		//Row
		for (int n = 0; n < sk.getGrid().length; n++) {
			//Column
            for (int i = 0; i < sk.getGrid()[0].length; i++) {
                labelGrid[n][i].setText("" + sk.getGrid()[n][i]);
                labelGrid[n][i].setForeground(sk.getCoGrid()[n][i]);
            }
        }
	}
	
	//Update displayed mouse coordinates
	public void refreshMouseCoords() {
		mouseCoords.setText("Mouse Coordinates: " + MouseInfo.getPointerInfo().getLocation().getX()+", "+MouseInfo.getPointerInfo().getLocation().getY());
	}
	//Update dynamic head coordinate boxes
	public void refreshHeadCoords(int x, int y) {
		coordsHeadx.setText(""+x);
		coordsHeady.setText(""+y);
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHeadX() {
		return headx;
	}
	
	public int getHeadY() {
		return heady;
	}
	
	public boolean isRunning() {
		return amIRunning;
	}
	
	public String toString() {
	    return "GUIFrame{" +
	            ", x1=" + x1 +
	            ", y1=" + y1 +
	            ", x2=" + x2 +
	            ", y2=" + y2 +
	            ", xSize=" + xSize +
	            ", ySize=" + ySize +
	            ", headx=" + headx +
	            ", heady=" + heady +
	            ", direction=" + direction +
	            ", snakeColor=" + snakeColor +
	            ", fruitColor=" + fruitColor +
	            ", amIRunning=" + amIRunning +
	            '}';
	}
}