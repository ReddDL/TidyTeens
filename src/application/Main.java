package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;
import javafx.scene.Group; // Import Group


public class Main extends Application{
	@Override
	public void start(Stage stage) {
		try {
			Group root = new Group();
			MainMenu mainMenu = new MainMenu();
			mainMenu.setStage(stage);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}