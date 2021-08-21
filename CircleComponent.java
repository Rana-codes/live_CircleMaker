package live_CircleMaker;


//File: CircleComponent
//Name: Harsh Rana

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class CircleComponent extends JComponent {
	
	// Instance Variables.
	// They have been declaresd public as the information is not sensitive.
	// They save the use of accessor and modifier methods.
	// Still some modifiers have been used to make the code understandable.
	private Circle myCircle;
	private ArrayList<Circle> list;
	
	// Constructor for component.
	public CircleComponent() {
		// It is important to declare it here, so that when the compiler
		// compiles, it doesn't get a null pointer exception(When it reaches paint component).
		myCircle = new Circle();
		list = new ArrayList<Circle>();
	}
	
	/**
	 * Changes the coordinates of centre of the circle.
	 * @param x - The new x coordinate for the center of the circle
	 * @param y - The new y coordinate for the center of the circle
	 */
	public void setCenter(int x, int y) {
		// my Circle is important here so that it creates a new circle each time
		// and just does not keep editing the old one.
		myCircle = new Circle();
		myCircle.setCenter(x, y);
	}
	
	/**
	 * Changes the point where the radius is drawn from center. This will be the final change made to this circle.
	 * It also adds the circle to the array list.
	 * @param x - The new x coordinate for the end of the radius
	 * @param y - The new y coordinate for the end of the radius
	 */
	public void setFinal(int x, int y) {
		myCircle.setFinal(x, y);
		myCircle.setColor(Color.blue);
		list.add(myCircle);
		repaint();
	}
	
	/**
	 * It sets the end point of the radius temporarily. So that the user can check how the circle looks.
	 * @param x - The new x coordinate for the end of the radius
	 * @param y - The new y coordinate for the end of the radius
	 */
	public void setTemp(int x, int y) {
		myCircle.setFinal(x, y);
		repaint();
	}
	
	/**
	 * It decides what to paint.
	 */
	public void paintComponent(Graphics g) {
		// Recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		// Necessary so that the previous circles can do not disappear.
		for(Circle val:list) {
			val.draw(g2);
		}
		// necessary because a circle will be needed to draw a number of times to check if its ready to be finalised and added to list.
		myCircle.draw(g2);
	}
	
	/**
	 * Checks if the first click has been made.
	 * @return - True if it is the first click, else returns false.
	 */
	public boolean checkFirstClick() {
		// the first click would be made when the initial values of the center were unchanged.
		if(myCircle.getXcen() == -1 && myCircle.getYcen() == -1) {
			return true;
		}
		return false;
	}
}

//************************************************************************************************************************************************************

// This Class Defines a circle and how it is to be drawn.
class Circle {
	
	//Instance variables
	private double xcen, ycen, xfin, yfin;
	// Color code has been used to be sure which ones are temporary and which ones are final.
	private Color col;
	
	
	/**
	 * Constructor
	 * The initial values are out of the frame(at -1,-1) to make sure the first point user types is taken.
	 */
	public Circle() {
		setCenter(-1,-1);
		// Initial color is red as we assume that the circle is temporary unless defined/mentioned otherwise.
		col = Color.red;
	}
	
	/**
	 * Just a method to get distance between 2 points. 
	 * @param x1 - x coordinate of point 1
	 * @param y1 - y coordinate of point 1
	 * @param x2 - x coordinate of point 2
	 * @param y2 - y coordinate of point 2
	 * @return
	 */
	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	/**
	 * @return - The x coordinate of the center of the circle
	 */
	public double getXcen() {
		return xcen;
	}
	
	/**
	 * @return - The y coordinate of the center of the circle
	 */
	public double getYcen() {
		return ycen;
	}
	
	/**
	 * modifies the values for center of the circle.
	 * @param x - The new x coordinate for the center
	 * @param y - The new y coordinate for the center
	 */
	public void setCenter(int x, int y) {
		xcen = x;
		ycen = y;
	}
	
	/**
	 * modifies the values for the final point of the circle's radius.
	 * @param x - The new x coordinate for the final point of the circle's radius.
	 * @param y - The new y coordinate for the final point of the circle's radius.
	 */
	public void setFinal(int x, int y) {
		xfin = x;
		yfin = y;
	}
	
	/**
	 * Modifies the value of color for the circle.
	 * @param color - The new color that is to be provided to the circle.
	 */
	public void setColor(Color color) {
		col = color;
	}
	
	/**
	 * Defines how a circle is to be drawn.
	 * Differentiates between temporary and finalised arrays on the basis of color.
	 * @param graphics
	 */
	public void draw(Graphics2D graphics) {
		if (col == Color.red) {
			graphics.setColor(Color.red);

			double radius = getDistance(xcen, ycen, xfin, yfin);
			Ellipse2D.Double diagram = new Ellipse2D.Double(xcen - radius, ycen - radius, radius * 2, radius * 2);
			graphics.draw(diagram);

			Point2D.Double centre = new Point2D.Double(xcen, ycen);
			Point2D.Double end = new Point2D.Double(xfin, yfin);
			Line2D.Double radialLine = new Line2D.Double(centre, end);

			BasicStroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 6 },0);
			graphics.setStroke(dashed);
			graphics.draw(radialLine);
		} 
		
		else {

			graphics.setColor(Color.blue);
			double radius = getDistance(xcen, ycen, xfin, yfin);

			Ellipse2D.Double diagram = new Ellipse2D.Double(xcen - radius, ycen - radius, radius * 2, radius * 2);
			graphics.draw(diagram);
		}

	}
}
