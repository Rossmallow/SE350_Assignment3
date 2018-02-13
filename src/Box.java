import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Box implements ShapeItem{

	private final int X_SIZE = 250;
	private final int Y_SIZE = 250;
	
	private List<Dot> contents = new ArrayList<Dot>();
	private Point2D position;
	private Color color;
	private Rectangle rect;
	private boolean moveable;
	
	private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BROWN, Color.PURPLE, Color.ORANGE, Color.PINK};
	
	/**
	 * Constructor
	 * @param x - the initial x-position of the box
	 * @param y - the initial y-position of the box
	 */
	public Box(double x, double y) {
		position = new Point2D(x, y);
		color = colors[new Random().nextInt(colors.length)];
		this.rect = new Rectangle(position.getX(), position.getY(), X_SIZE, Y_SIZE);
		this.rect.setStroke(color);
		this.rect.setFill(Color.rgb(225, 225, 225, 0));
	}
	
	/**
	 * Adds a dot to the list of contents
	 * @param d - the dot to be added
	 */
	public void add(Dot d) {
		if (!contents.contains(d)) {
			contents.add(d);
			d.changeColor(color);
		}
	}
	
	/**
	 * Removes a dot from the list of contents
	 * @param d - the dot to remove
	 */
	public void remove(Dot d) {
		if (contents.contains(d) ) {
			contents.remove(d);
			d.changeColor(Color.BLACK);
		}
	}
	
	/**
	 * Returns the list of contents
	 * @return - the list of contents
	 */
	public List<Dot> getContents() {
		return contents;
	}
	
	/**
	 * An implementation of "move" from the ShapeItem interface
	 */
	public void move(double dX, double dY) {
		if (moveable) {
			position = new Point2D(position.getX() + dX, position.getY() + dY);
			this.rect.setX(position.getX());
			this.rect.setY(position.getY());
			for (Dot d : contents) {
				if (!d.getMoveable()) {
					d.setMoveable(moveable);
					d.move(dX, dY);
				}
			}
		}
	}

	/**
	 * An implementation of "getShape" from the ShapeItem interface
	 */
	public Shape getShape() {
		return this.rect;
	}

	/**
	 * An implementation of "surrounds" from the ShapeItem interface
	 */
	public boolean surrounds(Point2D point) {
		boolean insideX = (point.getX() > rect.getX()) && (point.getX() < rect.getX() + X_SIZE);
		boolean insideY = (point.getY() > rect.getY()) && (point.getY() < rect.getY() + Y_SIZE);
		return insideX && insideY;
	}
	
	/**
	 * An implementation of "setMoveable" from the ShapeItem interface
	 */
	public void setMoveable(boolean b) {
		moveable = b;
	}
	
	/**
	 * An implementation of "getMoveable" from the ShapeItem interface
	 */
	public boolean getMoveable() {
		return moveable;
	}

	/**
	 * An implementation of "getPosition" from the ShapeItem interface
	 */
	public Point2D getPosition() {
		return position;
	}
}