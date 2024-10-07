package com.mediafatigue.snakeeyes;

import java.awt.Color;
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
	public boolean donePointing, startedPointing = false;
	
	public GUIFrame(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		
		mainpanel = new JPanel();
		mainpanel.setBackground(new Color(200, 200, 200));
		add(mainpanel);
		
		JPanel coordsPanel = new JPanel();
		coordsPanel.setLayout(new BoxLayout(coordsPanel, BoxLayout.Y_AXIS));
		JLabel instructions = new JLabel("Please select two sets of pixel coordinates:");
		JLabel coordsLabel1 = new JLabel("Point 1: ");
		JLabel coordsLabel2 = new JLabel("Point 2: ");
		
		JPanel coordsPanel1 = new JPanel();
		JTextField coords1x = new JTextField(6);
		JTextField coords1y = new JTextField(6);
		
		JPanel coordsPanel2 = new JPanel();
		JTextField coords2x = new JTextField(6);
		JTextField coords2y = new JTextField(6);
		
		IndicatorFrame ind1 = new IndicatorFrame();
		IndicatorFrame ind2 = new IndicatorFrame();
		
		coordsPanel1.add(coordsLabel1);
		coordsPanel1.add(coords1x);
		coordsPanel1.add(coords1y);
		
		coordsPanel2.add(coordsLabel2);
		coordsPanel2.add(coords2x);
		coordsPanel2.add(coords2y);
		
		coordsPanel.add(instructions);
		coordsPanel.add(coordsPanel1);
		coordsPanel.add(coordsPanel2);
		
		JButton coordSet = new JButton("Set Coordinates");
		
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
		
		coordsPanel.add(coordSet);
		mainpanel.add(coordsPanel);
		coordsPanel.setBackground(null);
		instructions.setForeground(Color.black);
		coordsLabel1.setForeground(Color.black);
		coordsLabel2.setForeground(Color.black);
		setVisible(true);
		
	}
}