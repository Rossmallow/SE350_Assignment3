import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Controller extends Application{

	private int winX = 500, winY = 600; // Window dimensions
	private Pane pane;
	
	private List<ShapeItem> shapeItems = new ArrayList<ShapeItem>();
	private List<Box> boxes = new ArrayList<Box>();
	private List<Dot> dots = new ArrayList <Dot>();
	private Point2D lastPosition;
	
	private int button;
	private boolean selected;

	/**
	 * Initializes a pane and creates the scene for the application.
	 */
	public void start(Stage stage) throws Exception {
		pane = new AnchorPane();
		Scene scene = new Scene(pane, winX, winY);
//		shapeItems.add(new Box(50, 50));
//		shapeItems.add(new Dot(15, 15));
		setMouseHandler(scene);
		stage.setScene(scene);
		stage.setTitle("Dots and Boxes");
		drawAll();
		stage.show();

	}
	
	/**
	 * For each ShapeItem in shapeItems, call drawItem on it.
	 */
	private void drawAll() {
		for (ShapeItem s : this.shapeItems) {
			drawItem(s);
		}
	}
	
	/**
	 * Gets the shape of the ShapeItem, if it's not added to the pane, add it.
	 * @param i - the ShapeItem to be drawn.
	 */
	private void drawItem(ShapeItem i) {
		Shape s = i.getShape();
		if (!pane.getChildren().contains(s)) {
			pane.getChildren().add(s);
		}
	}
	
	/**
	 * Calls launch to start the application
	 * 
	 * @param args - command-line arguments, these get ignored
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Create a custom mouse handler for our scene.
	 * @param scene - the scene to add the mouse handler to.
	 */
	private void setMouseHandler(Scene scene) {
		// Create new mouseHandler
		EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Point2D clickPoint = new Point2D(event.getX(), event.getY());
				String eventName = event.getEventType().getName();
				switch (eventName) {
				/**
				 * Sets the value of button to equal the button that is committing the action.
				 * Initially sets selected to be false.
				 * Iterate through the dots, if any is selected, set that dot's 'movable' to be true, 
				 * 		and set the rest to have a 'moveable' of false.
				 * Do the same for the boxes.
				 */
				case ("MOUSE_PRESSED"):
					if (lastPosition != null) {
						selected = false;
						if (event.isPrimaryButtonDown()) {
							button = 1;
						}
						else if (event.isSecondaryButtonDown()) {
							button = 2;
						}
						else {
							button = 0;
						}
						for (int d = 0; d < dots.size(); d++) {
							if (dots.get(d).surrounds(clickPoint) && !selected) {
								dots.get(d).setMoveable(true);
								selected = true;
							}
							else {
								dots.get(d).setMoveable(false);
							}
						}
						for (int b = 0; b < boxes.size(); b++) {
							if (boxes.get(b).surrounds(clickPoint) && !selected) {
								boxes.get(b).setMoveable(true);
								selected = true;
							}
							else {
								boxes.get(b).setMoveable(false);
							}
						}
						break;
					}
				/**
				 * Checks if any shapeItem has been selected.
				 * If no, check which button was released.
				 * 		If button 1 was pressed, create a new dot and add it to the shapeItems list and the dots list, then draw it.
				 * 		If button 2 was pressed, create a new box and add it to the shapeItems list and the box list, then draw it.
				 * If yes, for each box, iterate through each dot, and remove every dot from it's box.
				 * Then, for each box, iterate through each dot, and if the dot is inside a box, add it to that box.
				 */
				case ("MOUSE_RELEASED"):
					if (lastPosition != null) {
						if (!selected) {
							if (button == 1) {
								Dot d = new Dot(clickPoint.getX(), clickPoint.getY());
								shapeItems.add(d);
								dots.add(d);
								drawAll();
							}
							else if (button == 2) {
								Box b = new Box(clickPoint.getX(), clickPoint.getY());
								shapeItems.add(b);
								boxes.add(b);
								drawAll();
							}
							break;
						} 
						else if (selected){
							for (Box b : boxes) {
								for (Dot d : dots) {
										b.remove(d);
								}
							}
							for (Box b : boxes) {
								for (Dot d : dots) {
									if (b.surrounds(d.getPosition())) {
										b.add(d);
									}
								}
							}
							break;
						}
					}
				/**
				 * Set deltaX and deltaY to be the distance the mouse has moved on the x and y axises.
				 * Then, for each ShapeItem in shapeItems, move the shape by deltaX and deltaY. 
				 */
				case ("MOUSE_DRAGGED"):
					if (lastPosition != null) {
						double deltaX = clickPoint.getX() - lastPosition.getX();
						double deltaY = clickPoint.getY() - lastPosition.getY();
						for(ShapeItem s : shapeItems) {
							if(s.getMoveable()) {
								s.move(deltaX, deltaY);
							}
						}
						break;
					}
				}
				lastPosition = clickPoint;
			}
		}; // End of mouseHandler code

		// Set the handler for EVERYTHING
		scene.setOnMouseClicked(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseEntered(mouseHandler);
		scene.setOnMouseExited(mouseHandler);
		scene.setOnMouseMoved(mouseHandler);
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
	}
}