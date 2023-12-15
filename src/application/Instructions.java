/*
 * This is a class that holds the instruction stage
 * 
 * 
 */


package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Instructions extends Application implements EventHandler<ActionEvent>{
	
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	private Stage stage;
	private Group root;
	private Scene scene;
	
	Button previousButton;
	
	Image background;
	ImageView backgroundView;
	
	private MainMenu mainMenu;
	
	VariableCreation variableCreation = new VariableCreation();
	
	public Instructions(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		// STAGE INITIALIZATIONS
		// TODO: Simplify this
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		this.background = new Image("images/Instructions.png");
		backgroundView = new ImageView(this.background);
		
		previousButton = variableCreation.createButton("Back");
		previousButton.setOnAction(this);
		
		addComponents();

	}
	
	private void addComponents() {
		this.root.getChildren().add(backgroundView);
		this.root.getChildren().add(this.previousButton);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Tidy Teens");
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	@Override
	public void handle(ActionEvent event) {

		if (event.getSource() == previousButton) {
			System.out.println("Previous button clicked!");
            stage.setScene(mainMenu.getScene());
		}
		
	}

	@Override
	public void start(Stage instructionsStage) throws Exception {
		// TODO Auto-generated method stub
        setStage(instructionsStage);

	}
	


}
