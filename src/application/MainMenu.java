/*
 * This is a class that holds the main menu. The default launch 
 * stage
 * 
 */

package application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	
	private MediaPlayer titleSound;
    private Timeline titleSoundLoop;

	
	VariableCreation variableCreation = new VariableCreation();
	
	public MainMenu(Stage stage) {
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		this.stage = stage;
		this.stage.setResizable(false);
		this.stage.setTitle("Tidy Teens - Main Menu");
		
		// Plays the title sound
	    Media startSoundMedia = new Media(getClass().getResource("titleSound.mp3").toString());
        titleSound = new MediaPlayer(startSoundMedia);
        if (!titleSound.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            titleSound.play();
        }
        
        // Loops the title sound indefinitely
        titleSoundLoop = new Timeline(new KeyFrame(Duration.seconds(164), event -> {
            if (titleSound.getStatus() != MediaPlayer.Status.PLAYING) {
                titleSound.play();
            }
        }));
        titleSoundLoop.setCycleCount(Timeline.INDEFINITE);
        titleSoundLoop.play();


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
            titleSound.stop();
            titleSoundLoop.stop();
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
