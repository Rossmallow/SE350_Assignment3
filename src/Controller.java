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
	
	private void drawAll() {
		for (ShapeItem s : this.shapeItems) {
			drawItem(s);
		}
	}
	
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
//						for(ShapeItem s : shapeItems) {
//							if(s.surrounds(clickPoint)) {
//								s.setMoveable(true);
//								selected = true;
//							}
//							else {
//								s.setMoveable(false);
//							}
//						}
						break;
					}
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
				
//				/**
//				 * When the mouse is pressed, check if it is inside a ShapeItem.
//				 * If yes, set that ShapeItem to "moveable."
//				 * If no, set all ShapeItems to not "moveable."
//				 */
//				case ("MOUSE_PRESSED"):
//					if (lastPosition != null) {
//						for(ShapeItem s : shapeItems) {
//							if(s.surrounds(clickPoint)) {
//								s.setMoveable(true);
//							}
//							else
//								s.setMoveable(false);
//						}
//					}
//				case ("MOUSE_RELEASED"):
//					if (lastPosition != null) {
//						for(ShapeItem b : shapeItems) { 
//							if (b.getClass() == new Box(1, 1).getClass()) {
//								for (ShapeItem d : shapeItems) {
//									if (d.getClass() == new Dot(1, 1).getClass()) { 
//										if (b.surrounds(d.getPosition())) {
//											((Box) b).add((Dot) d);
//										}
//										else {
//											((Box) b).remove((Dot) d);
//										}
//									}
//								} 
//							}
//						}
//					}
//				/**
//				 * When the mouse is dragged, update the positions of each ShapeItem.
//				 */
//				case ("MOUSE_DRAGGED"):
//					if (lastPosition != null) {
//						double deltaX = clickPoint.getX() - lastPosition.getX();
//						double deltaY = clickPoint.getY() - lastPosition.getY();
//						for(ShapeItem s : shapeItems) {
//							if(s.getMoveable()) {
//								s.move(deltaX, deltaY);
//							}
//						}
//					}
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