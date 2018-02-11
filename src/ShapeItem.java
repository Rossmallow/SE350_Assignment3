import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public interface ShapeItem {
	
	/**
	 * Moves the shape
	 * @param dX - How much to change the x position
	 * @param dY - How much to change the y position
	 */
	public void move(double dX, double dY);
	
	/**
	 * Returns the ShapeItem's Shape
	 * @return - the shape
	 */
	public Shape getShape();
	
	/**
	 * Checks to see if a point is within the ShapeItem
	 * @param point - the point to test
	 * @return - true if the point is inside the ShapeItem, false if it is not.
	 */
	public boolean surrounds(Point2D point);
	
	/**
	 * Sets the ShapeItem's "moveable" value
	 * @param b - the value to be set
	 */
	public void setMoveable(boolean b);
	
	/**
	 * Returns the ShapeItem's "moveable" value
	 * @return - the ShapeItem's "moveable" value
	 */
	public boolean getMoveable();
	
	/**
	 * Returns the ShapeItem's position
	 * @return - the ShapeItem's position
	 */
	public Point2D getPosition();
}