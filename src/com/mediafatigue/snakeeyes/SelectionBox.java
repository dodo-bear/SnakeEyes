package com.mediafatigue.snakeeyes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SelectionBox {

	CapturePane pane;
	JFrame frame;

    public SelectionBox(){
                frame = new JFrame("");
                frame.setUndecorated(true);
                frame.setBackground(new Color(0, 0, 0, 0));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                pane = (CapturePane)frame.add(new CapturePane());
                
                Rectangle bounds = getScreenBounds();
                frame.setLocation(bounds.getLocation());
                frame.setSize(bounds.getSize());
                frame.setAlwaysOnTop(true);
                frame.setVisible(true);
    }
    
    public static Rectangle select() {
    	SelectionBox box = new SelectionBox();
        Object previousFrame = null;

        while (true) {
            if (true/*box.frame == null && previousFrame != null*/) {
                return box.pane.selectionBounds;
            }
            previousFrame = box.frame;

            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }*/
        }
        //return null;
    }

    public class CapturePane extends JPanel {

        private Rectangle selectionBounds;
        private Point clickPoint;

        public CapturePane() {
            setOpaque(false);
           
            MouseAdapter mouseHandler = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                        frame.dispose();
                        frame = null;
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    clickPoint = e.getPoint();
                    selectionBounds = null;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    clickPoint = null;
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    Point dragPoint = e.getPoint();
                    int x = Math.min(clickPoint.x, dragPoint.x);
                    int y = Math.min(clickPoint.y, dragPoint.y);
                    int width = Math.max(clickPoint.x - dragPoint.x, dragPoint.x - clickPoint.x);
                    int height = Math.max(clickPoint.y - dragPoint.y, dragPoint.y - clickPoint.y);
                    selectionBounds = new Rectangle(x, y, width, height);
                    repaint();
                }
            };

            addMouseListener(mouseHandler);
            addMouseMotionListener(mouseHandler);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 200, 100, 64));

            Area fill = new Area(new Rectangle(new Point(0, 0), getSize()));
            if (selectionBounds != null) {
                fill.subtract(new Area(selectionBounds));
            }
            g2d.fill(fill);
            if (selectionBounds != null) {
                g2d.setColor(Color.GREEN);
                g2d.draw(selectionBounds);
            }
            g2d.dispose();
        }
    }

    public static Rectangle getScreenBounds() {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice lstGDs[] = ge.getScreenDevices();
        for (GraphicsDevice gd : lstGDs) {
            bounds.add(gd.getDefaultConfiguration().getBounds());
        }
        return bounds;
    }
}