package application;

import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayGame{
	public static final int SCREEN_HEIGHT = 800;
	public static final int SCREEN_WIDTH = 1200;
	
	Image levelStage;
	ImageView levelStageView;
	
	private Stage stage;
	private Group root;
	@FXML private AnchorPane scene;
	private GraphicsContext gc;
	
	private PlayerController PlayerController;

	public void setStage(Stage stage){
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Level1.fxml"));
		try {
			Parent root = loader.load();
			PlayerController = loader.getController();
			
			Scene scene = new Scene(root);
			scene.getRoot().requestFocus();			
			
			this.stage = stage;
			this.stage.setTitle("Tidy Teens - Play Game");
			this.stage.setScene(scene);
			this.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
