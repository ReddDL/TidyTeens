/*
 * This is a class that holds the main menu. The default launch 
 * stage
 * 
 */

package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Group; // Import Group
import javafx.scene.paint.Color;

public class MainMenu implements EventHandler<ActionEvent>{
	
	// The screen size
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	// The midpoint to align all the buttons
	public static final int MID_BUTTON = 485;
	// Image initializations
	Image splashScreen;
	ImageView splashScreenView;
	Image selector;
	ImageView selectorView;
	
	// Stage redirections
	private Instructions instructionsStage;
	private About aboutStage;
	private PlayGame playGameStage;
	
	private Stage stage;
	private Group root;
	private Scene scene;
	
	// Button initializations
	Button playGameButton;
	Button aboutButton;
	Button instructionsButton;
	
	variableCreation variableCreation = new variableCreation();
	
	public MainMenu() {
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		// Add the splashscreen image
		this.splashScreen = new Image("images/SplashScreen.gif");
		splashScreenView = new ImageView(this.splashScreen);

		// CREATE BUTTONS
		
		// PLAY GAME BUTTON
		playGameButton = variableCreation.createButton("PLAY GAME");
		playGameButton.setOnAction(this);
		
		playGameButton.setLayoutX(MID_BUTTON); // X-coordinate
        playGameButton.setLayoutY(400); // Y-coordinate

        
        // INSTRUCTIONS BUTTON
        instructionsButton = variableCreation.createButton("INSTRUCTIONS");
        instructionsButton.setOnAction(this);
        
        instructionsButton.setLayoutX(MID_BUTTON);
        instructionsButton.setLayoutY(475);
        
        // ABOUT BUTTON
        aboutButton = variableCreation.createButton("ABOUT");
        aboutButton.setOnAction(this);
        
        aboutButton.setLayoutX(MID_BUTTON);
        aboutButton.setLayoutY(550);

		
	}
	
	// This method adds all the components to the stage
	private void addComponents() {
		this.root.getChildren().add(this.splashScreenView);
		this.root.getChildren().add(this.playGameButton);
		this.root.getChildren().add(this.instructionsButton);
		this.root.getChildren().add(this.aboutButton);
		
	}
	
	public void setStage(Stage stage) {
		this.addComponents();
		this.stage = stage;
		this.stage.setTitle("Tidy Teens");
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	Scene getScene() {
		return this.scene;
	}

	@Override
	public void handle(ActionEvent event) {
		// When the play game button is clicked
		if (event.getSource() == playGameButton) {
            System.out.println("Play Game button clicked!");
            playGameStage = new PlayGame();
            playGameStage.setStage(stage);
		}
		// When the instructions button is clicked
		if (event.getSource() == instructionsButton) {
            System.out.println("Instructions button clicked!");
            instructionsStage = new Instructions(this);
            instructionsStage.setStage(stage);
		}
		// When the about button is clicked
		if (event.getSource() == aboutButton) {
            System.out.println("About button clicked!");
            aboutStage = new About(this);
            aboutStage.setStage(stage);
		}
		
	}
	
}
