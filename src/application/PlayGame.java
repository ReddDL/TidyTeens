/*
 * PlayGame
 * - loads the FXML for the stage and launches the main game
 * 
 */

package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlayGame{
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	Image levelStage;
	ImageView levelStageView;
	
	private Stage stage;
	@FXML private AnchorPane scene;
	
	private PlayerController PlayerController;

	public void setStage(Stage stage){
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Level1.fxml"));
		try {
			Parent root = loader.load();
			PlayerController = loader.getController();
			
			PlayerController.setStage(stage);
			Scene scene = new Scene(root);
			scene.getRoot().requestFocus();		
			
			this.stage = stage;
			this.stage.setTitle("Tidy Teens - Play Game");
			this.stage.setScene(scene);
			this.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
