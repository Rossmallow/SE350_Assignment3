import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Dot implements ShapeItem{

	private final int DISPLAY_SIZE = 10;
	private final int RADIUS = DISPLAY_SIZE / 2;
	
	private Point2D position;
	private Color color;
	private Circle circle;
	private boolean moveable;
	
	/**
	 * Constructor
	 * @param x - the initial x-position of the dot
	 * @param y - the initial y-position of the dot
	 */
	public Dot(int x, int y) {
		position = new Point2D(x, y);
		color = Color.BLACK;
		this.circle = new Circle(position.getX(), position.getY(), DISPLAY_SIZE, this.color);
	}
	
	/**
	 * Changes the color of the dot
	 * @param c - the color to change to
	 */
	public void changeColor(Color c) {
		color = c;
		circle.setFill(c);
	}
	
	/**
	 * An implementation of "move" from the ShapeItem interface
	 */
	public void move(double dX, double dY) {
		if (moveable) {
			position = new Point2D(position.getX() + dX, position.getY() + dY);
			circle.setCenterX(position.getX());
			circle.setCenterY(position.getY());
		}
	}

	/**
	 * An implementation of "getShape" from the ShapeItem interface
	 */
	public Shape getShape() {
		return this.circle;
	}
	
	/**
	 * An implementation of "surrounds" from the ShapeItem interface
	 */
	public boolean surrounds(Point2D point) {
		boolean insideX = (point.getX() > position.getX() - RADIUS) && (point.getX() < position.getX() + RADIUS);
		boolean insideY = (point.getY() > position.getY() - RADIUS) && (point.getY() < position.getY() + RADIUS);
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