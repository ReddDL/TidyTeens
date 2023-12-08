package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

abstract class StageTemplate {
	// The screen size
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	// -- Attributes
	private Stage stage;
	private Scene scene;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	
	StageTemplate() {
		this.root = new Group();
		this.scene = new Scene(this.root, SCREEN_HEIGHT, SCREEN_WIDTH);
		
	}
	
	Scene getScene() {
		return this.scene;
	}
}
