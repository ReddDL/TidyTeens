package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PlayerController implements Initializable{
	
	@FXML
	private ImageView player;
	private Player playerComponent;
	
	@FXML private AnchorPane scene;
	private Stage stage;
	
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

//	Unbreakable unbreakable = new Unbreakable();
	private ArrayList<Rectangle> unbreakableObjects;
	
	private ArrayList<ImageView> breakableObjects;
	
	///////////////////////////////////
	@FXML private ImageView enemy1;
	@FXML private ImageView enemy2;
	@FXML private ImageView enemy3;
	private ArrayList<ImageView> enemyImages;
	private ArrayList<Enemy> initializedEnemies;
	private Random random;
	///////////////////////////////////

	CollisionHandler collisionHandler = new CollisionHandler();
	
	AnimationTimer gameLoop;
	
	private losingPage losingStage;
	boolean didWin = false;
		
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerComponent = new Player(player, x, y, lives, scoreCounter, this);
		
		// add the unbreakable objects
		unbreakableObjects = new ArrayList<Rectangle>(){{
			add(Bed); add(Cabinet1); add(Shelf1); add(Shelf2);
			add(Box1); add(Box2); add(Box3); add(Box4);
			add(Plant); add(TVset); add(chair1); add(chair2);
			add(Table1); add(Desk1); add(Desk2); add(Box5);
			add(Box6); add(Cabinet2); add(RightWall); add(TopWall);
			add(LeftWall); add(BottomWall);
		}};
		
		// add the breakable objects
		breakableObjects = new ArrayList<ImageView>() {{
			add(breakable1); add(breakable2); add(breakable3);
			add(breakable4); add(breakable5); add(breakable6);
			add(breakable7); add(breakable8); add(breakable9);
			add(breakable10); add(breakable11); add(breakable12);
			add(breakable13); add(breakable14); add(breakable15);
			add(breakable16); add(breakable17); add(breakable18);
			add(breakable19); add(breakable20); add(breakable21);
			add(breakable22); add(breakable23); add(breakable24);
		}};

		//////////////////////////////////
		enemyImages = new ArrayList<ImageView>() {{
			add(enemy1); add(enemy2); add(enemy3);
		}};

		initializedEnemies = Enemy.generateEnemies(enemyImages, playerComponent);

		for(Enemy enemy: initializedEnemies) {
			System.out.println(enemy.getImageView().getId());
		}
		random = new Random();
		GameTimer gameTimer = new GameTimer();
		Thread timerThread = new Thread(gameTimer);
		///////////////////////////////////
		gameLoop = new AnimationTimer() {
			long startTime = System.currentTimeMillis();
			@Override
			public void handle(long arg0) {
				
				playerComponent.makeMovable(player, scene, unbreakableObjects);
				Enemy.makeEnemiesMove(initializedEnemies, unbreakableObjects);
				
				long elapsedMilliseconds = System.currentTimeMillis() - startTime;
				double elapsedSeconds = (double) elapsedMilliseconds / 1000;
				System.out.println("ELAPSED TIME: " + elapsedSeconds);
				
				if (elapsedSeconds >= 5) {
					didWin = false;
					gameOver(didWin);
					gameLoop.stop();
						
				}
			}	
		};
		
		gameLoop.start();
//		 try {
//     		timerThread.join();
//
//		 } catch (InterruptedException e) {
//			 Thread.currentThread().interrupt();
//		 }
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
		
//		collisionHandler.enemyCollixsion(player, initializedEnemies);
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
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private void gameOver(boolean didWin) {
		if (didWin) {
			System.out.println("You won");
		} else {
			losingPage losingStage = new losingPage(playerComponent.getScore());
			losingStage.setStage(stage);
		}
	}
	
}
