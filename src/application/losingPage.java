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

public class losingPage implements EventHandler<ActionEvent>{
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
	
	variableCreation variableCreation = new variableCreation();
	
	public losingPage(int scoreCounter){
		this.scoreCounter = scoreCounter;
//		this.mainMenu = mainMenu;
		// STAGE INITIALIZATIONS
		// TODO: Simplify this
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		background = new Image("images/losingScreen.png");
		backgroundView = new ImageView(this.background);
		
		scoreText = variableCreation.createText(Integer.toString(scoreCounter));
//		scoreText.setText(Integer.toString(scoreCounter));
        scoreText.setStyle("-fx-font-size: 120; -fx-fill: black;");

		scoreText.setLayoutX(565);
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
		
		// STAGE INITIALIZATIONS
		// TODO: Simplify this 
		this.stage = stage;
		this.stage.setTitle("Tidy Teens - Losing stage");
		this.stage.setScene(scene);
		this.stage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == mainMenuButton) {
			System.out.println("Main menu button clicked!");
            mainMenu = new MainMenu();
            mainMenu.setStage(stage);
		}
		
		if (event.getSource() == playAgainButton) {
			System.out.println("Play again button clicked!");
			playGame = new PlayGame();
			playGame.setStage(stage);
		}
		
	}

}
