package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class losingPage {
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	private Stage stage;
	private Group root;
	private Scene scene;
	
	Image background;
	ImageView backgroundView;
	
	Button previousButton;
	
	private MainMenu mainMenu;
	
	variableCreation variableCreation = new variableCreation();
	
	public losingPage(){
//		this.mainMenu = mainMenu;
		// STAGE INITIALIZATIONS
		// TODO: Simplify this
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		background = new Image("images/losingScreen.png");
		backgroundView = new ImageView(this.background);
		
		addComponents();
	}
	
	public void addComponents() {
		this.root.getChildren().add(backgroundView);
	}
	
	public void setStage(Stage stage) {
		
		// STAGE INITIALIZATIONS
		// TODO: Simplify this 
		this.stage = stage;
		this.stage.setTitle("Tidy Teens - Losing stage");
		this.stage.setScene(scene);
		this.stage.show();
		
	}
}
