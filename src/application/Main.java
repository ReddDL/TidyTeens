/*
 * TidyTeens
 * - This is a game built using Java and JavaFX using concepts of Object-Oriented Programming
 * - Created as a course requirement in CMSC 22
 * 
 * Creators:
 * - CAPULE, Beatrice Elaine
 * - DE LEON, Richard Emmanuel
 * - ROBLES, Erjoy Constantine
 */


package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) {
		try {
//			Group root = new Group();
			MainMenu mainMenu = new MainMenu(stage);
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