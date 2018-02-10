import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller extends Application{

	private Pane pane;

	/**
	 * Initializes a pane and creates the scene for the application.
	 */
	public void start(Stage stage) throws Exception {
		pane = new AnchorPane();
		Scene scene = new Scene(pane, 500, 600);
		stage.setScene(scene);
		stage.setTitle("Dots and Boxes");
		stage.show();
	}
	
	/**
	 * Calls launch to start the application
	 */
	public static void main(String[] args) {
		launch();
	}
}
