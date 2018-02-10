
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Dot implements ShapeItem{

	private final int DISPLAY_SIZE = 20;
	private final int RADIUS = DISPLAY_SIZE / 2;
	
	private Point2D position;
	private Color color;
	private boolean movable = true;
	private Circle circle;
	
	/**
	 * Constructor
	 * @param x - the initial x-position of the dot
	 * @param y - the initial y-position of the dot
	 */
	public Dot (int x, int y) {
		position = new Point2D(x, y);
		color = Color.BLACK;
		this.circle = new Circle(position.getX(), position.getY(), DISPLAY_SIZE, this.color);
	}
	
	/**
	 * An implementation of "move" from the ShapeItem interface
	 */
	@Override
	public void move(double dX, double dY) {
		position = new Point2D(position.getX() + dX, position.getY() + dY);
		circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());
	}

	/**
	 * An implementation of "getShape" from the ShapeItem interface
	 */
	@Override
	public Shape getShape() {
		return this.circle;
	}
	
	/**
	 * An implementation of "isInside" from the ShapeItem interface
	 */
	@Override
	public boolean isInside(Point2D point) {
		return false;
	}

}
