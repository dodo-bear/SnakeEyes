package com.mediafatigue.snakeeyes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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

	public int x1, y1, x2, y2;
	Color snakeColor;
	Color fruitColor;
	JPanel grid;
	
	public GUIFrame(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		grid = new JPanel();
		mainpanel = new JPanel();
		mainpanel.setBackground(new Color(200, 200, 200));
		add(mainpanel);
		
		JPanel coordsPanel = new JPanel();
		coordsPanel.setLayout(new BoxLayout(coordsPanel, BoxLayout.Y_AXIS));
		JLabel instructions = new JLabel("Please select two sets of pixel coordinates:");
		JLabel coordsLabel1 = new JLabel("Point 1: ");
		JLabel coordsLabel2 = new JLabel("Point 2: ");
		JLabel coordsLabel3 = new JLabel("Color Picker Coordinates: ");
		JLabel coordsLabel4 = new JLabel("Grid Size (Rows, Columns): ");
		
		JPanel coordsPanel1 = new JPanel();
		JTextField coords1x = new JTextField(6);
		JTextField coords1y = new JTextField(6);
		
		JPanel coordsPanel2 = new JPanel();
		JTextField coords2x = new JTextField(6);
		JTextField coords2y = new JTextField(6);
		
		JPanel coordsPanel3 = new JPanel();
		JTextField coords3x = new JTextField(6);
		JTextField coords3y = new JTextField(6);
		
		JPanel coordsPanel4 = new JPanel();
		JTextField coords4x = new JTextField(6);
		JTextField coords4y = new JTextField(6);
		
		IndicatorFrame ind1 = new IndicatorFrame();
		IndicatorFrame ind2 = new IndicatorFrame();
		
		ColorPickerFrame cf = new ColorPickerFrame();
		cf.goTo(0, 0);
		JPanel colorPanel = new JPanel();
		JLabel colabel1 = new JLabel("Snake Color: ");
		JLabel colabel2 = new JLabel("Fruit Color: ");
		JPanel color1 = new JPanel();
		JPanel color2 = new JPanel();
		
		JButton spawnSkimmer = new JButton("Start Reading Data");
		
		color1.setPreferredSize(new Dimension(80, 20));
		color2.setPreferredSize(new Dimension(80, 20));
		
		color1.setBackground(Color.BLACK);
		color2.setBackground(Color.BLACK);
		
		colorPanel.add(colabel1);
		colorPanel.add(color1);
		colorPanel.add(colabel2);
		colorPanel.add(color2);
		
		coordsPanel1.add(coordsLabel1);
		coordsPanel1.add(coords1x);
		coordsPanel1.add(coords1y);
		
		coordsPanel2.add(coordsLabel2);
		coordsPanel2.add(coords2x);
		coordsPanel2.add(coords2y);
		
		coordsPanel3.add(coordsLabel3);
		coordsPanel3.add(coords3x);
		coordsPanel3.add(coords3y);

		coordsPanel4.add(coordsLabel4);
		coordsPanel4.add(coords4x);
		coordsPanel4.add(coords4y);
		
		coordsPanel.add(instructions);
		coordsPanel.add(coordsPanel1);
		coordsPanel.add(coordsPanel2);
		
		JButton coordSet = new JButton("Set Area Coordinates");
		
		coordSet.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            x1 = Integer.parseInt(coords1x.getText());  
			            x2 = Integer.max(Integer.parseInt(coords2x.getText()), x1);  
			            y1 = Integer.parseInt(coords1y.getText());  
			            y2 = Integer.max(Integer.parseInt(coords2y.getText()), y1); 
			            ind1.goTo(x1, y1);
			            ind2.goTo(x2, y2);
			            //MainClass.setSkimmer(new Skimmer(x1, y1, x2, y2, ));
			        }  
			    });  
		
		JButton pickColor = new JButton("Set Snake Color");
		JButton pickColor2 = new JButton("Set Fruit Color");
		
		pickColor.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            cf.goTo(Integer.parseInt(coords3x.getText()), Integer.parseInt(coords3y.getText()));
			            snakeColor = cf.getBackground();
			            color1.setBackground(snakeColor);
			        }  
			    });
		
		pickColor2.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            cf.goTo(Integer.parseInt(coords3x.getText()), Integer.parseInt(coords3y.getText()));
			            fruitColor = cf.getBackground();
			            color2.setBackground(fruitColor);
			        }  
			    });
		
		spawnSkimmer.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			            MainClass.setSkimmer(new Skimmer(x1, y1, x2, y2, 5, 5, color1.getBackground(), color2.getBackground()));
			        }  
			    });
		
		coordsPanel.add(coordSet);
		coordsPanel.add(coordsPanel4);
		coordsPanel.add(coordsPanel3);
		coordsPanel.add(colorPanel);
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(pickColor);
		buttonPanel.add(pickColor2);
		coordsPanel.add(buttonPanel);
		coordsPanel.add(grid);
		mainpanel.add(coordsPanel);
		coordsPanel.setBackground(null);
		instructions.setForeground(Color.black);
		coordsLabel1.setForeground(Color.black);
		coordsLabel2.setForeground(Color.black);
		setVisible(true);
		
	}
	
	public void initGrid(int x, int y, Skimmer sk) {
		grid = new JPanel();
        grid.setLayout(new GridLayout(25, 25));
        for (int i = 0; i < 25; i++) {
            for (int n = 0; n < 25; n++) {
                grid.add(new JLabel("" + sk.getGrid()[i][n]));
            }
        }
	}
}