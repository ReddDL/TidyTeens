/*
 * PlayerController
 * 
 * - Handles the game logic
 * 
 */


package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameTimer implements Initializable{
	
	@FXML
	private ImageView player;
	private Player playerComponent;
	
	@FXML private AnchorPane scene;
	private Stage stage;
	
	// Player initialization
	private double x = 0;
	private double y = 0;
	private int scoreCounter = 0;
	
	@FXML private ImageView heart1;
	@FXML private ImageView heart2;
	@FXML private ImageView heart3;
	// Bomb images
	private Image waterSplash = new Image("images/waterSplash.gif", 100,100, true, true);
	private ImageView waterSplashView = new ImageView(waterSplash);
	
	// Splash indicator
	@FXML private ImageView splashReadyView;
	
	// Score text
	@FXML private Text score;
	
	// Unbreakable images
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
	
	// Breakable images
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
	
	// Enemy images
	@FXML private ImageView enemy1;
	@FXML private ImageView enemy2;
	@FXML private ImageView enemy3;
	
	private ArrayList<Rectangle> unbreakableObjects;	
	private ArrayList<ImageView> breakableObjects;
	private ArrayList<Enemy> initializedEnemies;
	
	@FXML ProgressBar timeBar;

	CollisionHandler collisionHandler = new CollisionHandler();
	
	AnimationTimer gameLoop;
	
	private Losing losingStage;
	boolean didWin = false;
	boolean playerDamaged = false;
    private Timeline damageCooldown;
    private int breakableNum = 24;

    private static final long TOTAL_DURATION = 180 * 1000;
    
    private MediaPlayer backgroundMusic;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerComponent = new Player(player, x, y, 3, scoreCounter, waterSplashView, this);
		
		 damageCooldown = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
	            playerComponent.setCanBeDamaged(true);
	        }));
	        damageCooldown.setCycleCount(1);
	        
	    timeBar.setStyle("-fx-accent: green;"); 	
	    
	    // initializes the background music
	    Media startSoundMedia = new Media(getClass().getResource("gameBackground.mp3").toString());
        backgroundMusic = new MediaPlayer(startSoundMedia);

	    
		// add the unbreakable objects
		unbreakableObjects = new ArrayList<Rectangle>() {{
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
		
		Enemy enemy1Component = new Enemy(enemy1, x, y);
		Enemy enemy2Component = new Enemy(enemy2, x, y);
		Enemy enemy3Component = new Enemy(enemy3, x, y);
		
		initializedEnemies = new ArrayList<Enemy>() {{
			add(enemy1Component); add(enemy2Component); add(enemy3Component);
		}};

		gameLoop = new AnimationTimer() {
			long startTime = System.currentTimeMillis();
			long lastPrintTime = startTime;
			
			@Override
			public void handle(long arg0) {
				// plays the background music if its not playing
                if (!backgroundMusic.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                    backgroundMusic.play();
                }
				
				// lazy timer
				long elapsedMilliseconds = TOTAL_DURATION - (System.currentTimeMillis() - startTime);
				double elapsedSeconds = (double) elapsedMilliseconds / 1000;
		        if (System.currentTimeMillis() - lastPrintTime >= 30 * 1000) {
		            System.out.println("Remaining time: " + (int) elapsedSeconds);
		            lastPrintTime = System.currentTimeMillis();
		        }
		        
		        // computes the progress and updates the timer bar
		        double progress = elapsedSeconds / (TOTAL_DURATION / 1000);		        
				timeBar.setProgress(progress);

				// losing conditions
				if (elapsedSeconds <= 0|| playerComponent.getLives() == 0) {
                    backgroundMusic.stop();

					System.out.println("GAME OVER");
					didWin = false;
					gameOver(didWin);
					gameLoop.stop();
			
				}
				
				// win condition
				if (breakableNum == 0) {
					System.out.println("YOU WON THE GAME");
					didWin = true;
					gameOver(didWin);
					gameLoop.stop();
				}
				
				// moves player and enemmies
				playerComponent.makeMovable(player, scene);
				enemy1Component.moveEnemy(playerComponent, unbreakableObjects, breakableObjects);
				enemy2Component.moveEnemy(playerComponent, unbreakableObjects, breakableObjects);
				enemy3Component.moveEnemy(playerComponent, unbreakableObjects, breakableObjects);
				
				// if the player cant drop a bomb, it means that the player has recently dropped a bomb, checks if it collides with any breakable objects
				if(!playerComponent.getCanDropBomb()) {
					splashReadyView.setVisible(false);
					if(collisionHandler.bombBreakableCollision(playerComponent.bombImage, breakableObjects)) {
						pointChecker(TOTAL_DURATION, elapsedSeconds);
						breakableNum--;
					}
				} else {
					splashReadyView.setVisible(true);
				}
				
				// if enemies hit player
				if (collisionHandler.enemyCollision(playerComponent, initializedEnemies) && playerComponent.getCanBeDamaged()) {
					System.out.println("PLAYER DAMAGED");
                    playerComponent.damagePlayer();
                    playerComponent.setCanBeDamaged(false);
                    damageCooldown.playFromStart();
                    
                    if (playerComponent.getLives() == 2) {
                    	heart3.setImage(null);
                    } else if(playerComponent.getLives() == 1) {
                    	heart2.setImage(null);
                    } else if (playerComponent.getLives() == 0) {
                    	heart1.setImage(null);
                    }
                    
                }
				
			}	
		};
		
		gameLoop.start();
	}
	
	/*
	 * checkCollision()
	 * - handles the player collisions and returns the player to the previous position if there was a collision
	 * 
	 */
	public void checkCollision(double currentX, double currentY) {
		if (collisionHandler.checkCollisionUnbreakables(player, unbreakableObjects)) {
			player.setLayoutX(currentX);
			player.setLayoutY(currentY);
		}
		
		if (collisionHandler.checkCollisionBreakables(player, breakableObjects) ) {
			player.setLayoutX(currentX);
			player.setLayoutY(currentY);
		}
		
	}
	
			
			
	/*
	 * pointChecker()
	 * - computes the points obtained depending on the time
	 * - increments the score of the player
	 * - updates the text on the scene
	 */
	private void pointChecker(double totalTime, double currentTime) {
		int scoreComputation = (int) ((TOTAL_DURATION - currentTime) * 2 ) / 1500;
		playerComponent.incrementScore(scoreComputation);		
		score.setText(String.valueOf(playerComponent.getScore()));
		System.out.println("PLAYER SCORE: " + playerComponent.getScore());
		
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	/*
	 * gameOver()
	 * - sets the scene depending if the player won or loss the game
	 * 
	 */
	private void gameOver(boolean didWin) {
		if (didWin) {
			Winning winningStage = new Winning(playerComponent.getScore());
			winningStage.setStage(stage);
		} else {
			Losing losingStage = new Losing(playerComponent.getScore());
			losingStage.setStage(stage);
		}
	}
	
}
