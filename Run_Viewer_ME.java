package live_CircleMaker;


//File: CircleViewer
//Name: Harsh Rana


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Run_Viewer_ME {
	public static void main(String[] args) {

		CircleComponent component = new CircleComponent();
		
		class MousePressListener implements MouseListener, MouseMotionListener {
			
			// To keep count of number of clicks
			int count = 0;
			
			/**
			 * Sets center, temporary point, and final point by looking at the number of clicks.
			 */
			public void mouseClicked(MouseEvent event) {
				count++;

				int x = event.getX();
				int y = event.getY();

				if (count % 2 == 0) {
					component.setFinal(x, y);
				} 
				else {
					component.setCenter(x, y);
					//component.setTemp(x, y);
				}
			}
			
			/**
			 * Keeps track when and where the mouse is moved.
			 * Makes sure track is kept only when first click has been done.
			 */
			public void mouseMoved(MouseEvent event) {
				
				boolean firstClick = component.checkFirstClick();
				if (firstClick == false) {

					int x = event.getX();
					int y = event.getY();
					if (count % 2 != 0 && count > 0) {
						component.setTemp(x, y);
					}
				}
			}
			// Do-nothing methods
			public void mousePressed(MouseEvent event) {}
			public void mouseReleased(MouseEvent event) {}
			public void mouseEntered(MouseEvent event) {}
			public void mouseExited(MouseEvent event) {}
			public void mouseDragged(MouseEvent event) {}
			
		}
		
		JFrame frame = new JFrame();

		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 500;

		frame.add(component);
		MousePressListener listener = new MousePressListener();
		component.addMouseListener(listener);
		component.addMouseMotionListener(listener);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Circle Maker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}