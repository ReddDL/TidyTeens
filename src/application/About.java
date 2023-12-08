package application;

//import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class About implements EventHandler<ActionEvent>{
	
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
	
	public About(MainMenu mainMenu) {
		
		this.mainMenu = mainMenu;
		// STAGE INITIALIZATIONS
		// TODO: Simplify this
		this.root = new Group();
		this.scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		
		this.background = new Image("images/SplashScreen-blurred.png");
		this.backgroundView = new ImageView(this.background);
		
		this.previousButton = variableCreation.createButton("Back");
		this.previousButton.setOnAction(this);
		
		addComponents();
	}
	
	public void addComponents() {
		this.root.getChildren().add(this.backgroundView);
		this.root.getChildren().add(this.previousButton);
	}
	
	public void setStage(Stage stage) {
		// STAGE INITIALIZATIONS
		// TODO: Simplify this 
		this.stage = stage;
		this.stage.setTitle("Tidy Teens - About");
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == previousButton) {
			System.out.println("Previous button clicked!");
            stage.setScene(mainMenu.getScene());
		}
	}


}
