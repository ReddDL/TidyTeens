/***
 * This is the winning page
 * Call this class upon winning the game in playGame class
 */


package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class winningPage {
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
	
	public winningPage(MainMenu mainMenu){
		this.mainMenu = mainMenu;
		// STAGE INITIALIZATIONS
		// TODO: Simplify this
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		Image background = new Image("images/winningScreen.png");
		ImageView backgroundView = new ImageView(background);
		
		addComponents();
	}
	
	public void addComponents() {
		this.root.getChildren().add(backgroundView);
	}
}
