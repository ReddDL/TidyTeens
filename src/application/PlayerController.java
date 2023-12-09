package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class PlayerController implements Initializable{
	
	@FXML
	private ImageView player;
	private Player playerComponent;
	
	@FXML private AnchorPane scene;
	
	
	private double x = 0;
	private double y = 0;
	private int scoreCounter = 0;
	
	@FXML private int lives;
	@FXML private Text score;
	
	@FXML private Rectangle Bed;
	@FXML private Rectangle Plant;
	@FXML private Rectangle Shelf1;
	@FXML private Rectangle Shelf2;
	@FXML private Rectangle Box1;
	@FXML private Rectangle Box2;
	@FXML private Rectangle Box3;
	@FXML private Rectangle Box4;
	@FXML private Rectangle Cabinet1;
	@FXML private Rectangle TVset;
	@FXML private Rectangle chair1;
	@FXML private Rectangle chair2;
	@FXML private Rectangle Table1;
	@FXML private Rectangle Desk1;
	@FXML private Rectangle Desk2;
	@FXML private Rectangle Box5;
	@FXML private Rectangle Box6;
	@FXML private Rectangle Cabinet2;
	@FXML private Rectangle RightWall;
	@FXML private Rectangle TopWall;
	@FXML private Rectangle LeftWall;
	@FXML private Rectangle BottomWall;
	
	@FXML private ImageView breakable1;
	@FXML private ImageView breakable2;
	@FXML private ImageView breakable3;
	@FXML private ImageView breakable4;
	@FXML private ImageView breakable5;
	@FXML private ImageView breakable6;
	@FXML private ImageView breakable7;
	@FXML private ImageView breakable8;
	@FXML private ImageView breakable9;
	@FXML private ImageView breakable10;
	@FXML private ImageView breakable11;
	@FXML private ImageView breakable12;
	@FXML private ImageView breakable13;
	@FXML private ImageView breakable14;
	@FXML private ImageView breakable15;
	@FXML private ImageView breakable16;
	@FXML private ImageView breakable17;
	@FXML private ImageView breakable18;
	@FXML private ImageView breakable19;
	@FXML private ImageView breakable20;
	@FXML private ImageView breakable21;
	@FXML private ImageView breakable22;
	@FXML private ImageView breakable23;
	@FXML private ImageView breakable24;

	private ArrayList<Rectangle> unbreakableObjects = new ArrayList();
	
	private ArrayList<ImageView> breakableObjects = new ArrayList();
	
	Unbreakable unbreakable = new Unbreakable();
	
	CollisionHandler collisionHandler = new CollisionHandler();
	
	AnimationTimer gameLoop;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerComponent = new Player(player, x, y, lives, scoreCounter, this);
		
		gameLoop = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				double currentX = player.getX();
				double currentY = player.getY();
				
				playerComponent.makeMovable(player, scene, unbreakableObjects);
			}	
		};
		
		gameLoop.start();
		
		unbreakableObjects.add(Bed);
		unbreakableObjects.add(Cabinet1);
		unbreakableObjects.add(Shelf1);
		unbreakableObjects.add(Shelf2);
		unbreakableObjects.add(Box1);
		unbreakableObjects.add(Box2);
		unbreakableObjects.add(Box3);
		unbreakableObjects.add(Box4);
		unbreakableObjects.add(Plant);
		unbreakableObjects.add(TVset);
		unbreakableObjects.add(chair1);
		unbreakableObjects.add(chair2);
		unbreakableObjects.add(Table1);
		unbreakableObjects.add(Desk1);
		unbreakableObjects.add(Desk2);
		unbreakableObjects.add(Box5);
		unbreakableObjects.add(Box6);
		unbreakableObjects.add(Cabinet2);
		unbreakableObjects.add(RightWall);
		unbreakableObjects.add(TopWall);
		unbreakableObjects.add(LeftWall);
		unbreakableObjects.add(BottomWall);
		
//		breakableObjects = new ArrayList<>();

		breakableObjects.add(breakable1);
		breakableObjects.add(breakable2);
		breakableObjects.add(breakable3);
		breakableObjects.add(breakable4);
		breakableObjects.add(breakable5);
		breakableObjects.add(breakable6);
		breakableObjects.add(breakable7);
		breakableObjects.add(breakable8);
		breakableObjects.add(breakable9);
		breakableObjects.add(breakable10);
		breakableObjects.add(breakable11);
		breakableObjects.add(breakable12);
		breakableObjects.add(breakable13);
		breakableObjects.add(breakable14);
		breakableObjects.add(breakable15);
		breakableObjects.add(breakable16);
		breakableObjects.add(breakable17);
		breakableObjects.add(breakable18);
		breakableObjects.add(breakable19);
		breakableObjects.add(breakable20);
		breakableObjects.add(breakable21);
		breakableObjects.add(breakable22);
		breakableObjects.add(breakable23);
		breakableObjects.add(breakable24);
	}
	
	public void checkCollision(double currentX, double currentY) {
		if (collisionHandler.checkCollisionUnbreakables(player, unbreakableObjects)) {
			player.setLayoutX(currentX);
			player.setLayoutY(currentY);
		}
		
		if (collisionHandler.checkCollisionBreakables(player, breakableObjects) ) {
			player.setLayoutX(currentX);
			player.setLayoutY(currentY);
			pointChecker();
		}
		
	}
	
	/*
	 * move 
	 * checks for keypresses in the scene
	 * stores the current x and y and if there is a collision, returns the player 
	 * to their previous position
	 */
	
	private void pointChecker() {
		playerComponent.incrementScore();		score.setText(String.valueOf(playerComponent.getScore()));
		System.out.println("PLAYER SCORE: " + playerComponent.getScore());
		
	}
	
	
	
}
