/*
 * WinningPage
 * - Sets the stage as the winning page if win conditions are met:
 * 		- Player has cleared the map of trash
 */


package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Winning implements EventHandler<ActionEvent>{
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	private Stage stage;
	private Group root;
	private Scene scene;
	int scoreCounter;
	Image background;
	ImageView backgroundView;
	
	Button mainMenuButton;
	Button playAgainButton;
	Text scoreText;
	private MainMenu mainMenu;
	private PlayGame playGame;
	
	VariableCreation variableCreation = new VariableCreation();
	
	public Winning(int scoreCounter){
		this.scoreCounter = scoreCounter;
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		background = new Image("images/winningScreen.png");
		backgroundView = new ImageView(this.background);
		
		scoreText = variableCreation.createText(Integer.toString(scoreCounter));
        scoreText.setStyle("-fx-font-size: 120; -fx-fill: black;");

		scoreText.setLayoutX(500);
		scoreText.setLayoutY(475);
		
		mainMenuButton = variableCreation.createButton("MAIN MENU");
		mainMenuButton.setLayoutX(280);
		mainMenuButton.setLayoutY(550);
		mainMenuButton.setOnAction(this);

		
		playAgainButton = variableCreation.createButton("PLAY AGAIN");
		playAgainButton.setLayoutX(675);
		playAgainButton.setLayoutY(550);
		playAgainButton.setOnAction(this);
		
		addComponents();
	}
	
	public void addComponents() {
		this.root.getChildren().add(backgroundView);
		this.root.getChildren().add(scoreText);
		this.root.getChildren().add(mainMenuButton);
		this.root.getChildren().add(playAgainButton);
	}
	
	public void setStage(Stage stage) {

		this.stage = stage;
		this.stage.setTitle("Tidy Teens");
		this.stage.setScene(scene);
		this.stage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == mainMenuButton) {
			System.out.println("Main menu button clicked!");
            mainMenu = new MainMenu(stage);
            mainMenu.setStage(stage);
		}
		
		if (event.getSource() == playAgainButton) {
			System.out.println("Play again button clicked!");
			playGame = new PlayGame();
			playGame.setStage(stage);
		}
		
	}

}
