package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player extends Sprite{
	@FXML private ImageView player;
	@FXML AnchorPane scene;
	private double x;
	private double y;
	private int lives;
	private int scoreCounter;
	
	private boolean canBeDamaged = true;
	private boolean canSpawnBomb = true;
	
	// different images
	private Image playerUp = new Image("images/PlayerUp.png");
	private ImageView playerUpView = new ImageView(playerUp);
	private Image playerLeft = new Image("images/PlayerLeft.png");
	private ImageView playerLeftView = new ImageView(playerLeft);
	private Image playerRight = new Image("images/PlayerRight.png");
	private ImageView playerRightView = new ImageView(playerRight);
	private Image playerDown = new Image("images/StandingPlayer.png");
	private ImageView playerDownView = new ImageView(playerDown);
	
	
	private BooleanProperty upPressed = new SimpleBooleanProperty();
	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanProperty downPressed = new SimpleBooleanProperty();
	private BooleanProperty spacePressed = new SimpleBooleanProperty();
	
	private BooleanBinding keyPressed = upPressed.or(leftPressed).or(rightPressed).or(downPressed).or(spacePressed);
		
	private static final double movementVariable = 2;
	
	CollisionHandler collisionHandler = new CollisionHandler();
	PlayerController playerController;
	public ImageView bombImage;
    private Timeline bombCooldownTimeline;


    public Player(ImageView player, double x, double y, int lives, int scoreCounter, ImageView bombImage, PlayerController playerController) {
        super(x,y,player);
        this.lives = lives;
        this.scoreCounter = scoreCounter;
        this.playerController = playerController;
        this.bombImage = bombImage;
        initializeBombCooldownTimeline();

    }
    
    public void dropBomb() {
        if (canSpawnBomb) {
            Bomb bomb = new Bomb(bombImage, this.getImageView().getLayoutX(), this.getImageView().getLayoutY(), scene);
            this.setCanDropBomb(false);

            bomb.getImageView().setLayoutX(this.getImageView().getLayoutX() - 20);
            bomb.getImageView().setLayoutY(this.getImageView().getLayoutY() - 5);
            bomb.setBombVisible(true);
            scene.getChildren().add(bomb.getImageView());

            // Start the cooldown timeline
            bombCooldownTimeline.playFromStart();

            // Set the flag to indicate bomb cooldown
            this.canSpawnBomb = false;
        } else {
            System.out.println("Bomb cooldown in progress, cannot drop bomb.");
        }
    }
    
    private void initializeBombCooldownTimeline() {
//        Duration cooldownDuration = Duration.seconds(3);

        bombCooldownTimeline = new Timeline(new KeyFrame(
                Duration.seconds(3),
                event -> {
                    // Cooldown is over, allow bomb dropping again
                	System.out.println("You can drop a bomb again");
                    this.setCanDropBomb(true);
                }
        ));
    }

    /* move
     * moves the player in the direction of the 
     * keypress    
     * 
     */
	public void makeMovable(ImageView player, AnchorPane scene, ArrayList<Rectangle> unbreakableObjects) {
		this.player = player;
		this.scene = scene;
		
		movementSetup();
		
		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if (!aBoolean) {
				timer.start();
			} else {
				timer.stop();
			}
		}));
	}
	
	AnimationTimer timer = new AnimationTimer() {
//		private Bomb bomb = null;  // Declare a Bomb variable

		
		@Override
		public void handle(long timestamp) {
			
			double currentX = player.getLayoutX();
			double currentY = player.getLayoutY();
			if(upPressed.get()) {
				player.setLayoutY(player.getLayoutY() - movementVariable);
			}
			if(downPressed.get()){
				player.setLayoutY(player.getLayoutY() + movementVariable);
			}
			if(leftPressed.get()){
				player.setLayoutX(player.getLayoutX() - movementVariable);
			}
			if(rightPressed.get()){
				player.setLayoutX(player.getLayoutX() + movementVariable);
		    }
			
//			if(spacePressed.get()) {
//				if (bomb == null || !bomb.getBombVisible()) {
//	                // Create a new bomb only if it's not already visible
//	                System.out.println("Dropped a bomb");
//	                bomb = new Bomb(player.getLayoutX(), player.getLayoutY());
//	                bomb.createAnimationTimeline();
//	                bomb.setBombVisible(true);
//	                scene.getChildren().add(bomb.getImageView());
//	            }
//			}
			playerController.checkCollision(currentX, currentY);
			
		}
	};
	
	private void movementSetup(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.W) {
				upPressed.set(true);
				this.getImageView().setImage(playerUp);
			}
			if(e.getCode() == KeyCode.A) {
				leftPressed.set(true);
				this.getImageView().setImage(playerLeft);

			}
			if(e.getCode() == KeyCode.S) {
				downPressed.set(true);
				this.getImageView().setImage(playerDown);

			}
			if(e.getCode() == KeyCode.D) {
				rightPressed.set(true);
				this.getImageView().setImage(playerRight);
			}
			if (e.getCode() == KeyCode.SPACE && canSpawnBomb) {
				System.out.println("Dropped bomb");
				this.dropBomb();
	            }
		});
		scene.setOnKeyReleased(e ->{
			if(e.getCode() == KeyCode.W) {
				upPressed.set(false);
				this.getImageView().setImage(playerUp);

			}
			if(e.getCode() == KeyCode.A) {
				leftPressed.set(false);
				this.getImageView().setImage(playerLeft);

			}
			if(e.getCode() == KeyCode.S) {
				downPressed.set(false);
				this.getImageView().setImage(playerDown);

			}
			if(e.getCode() == KeyCode.D) {
				rightPressed.set(false);
				this.getImageView().setImage(playerRight);
			}
		});
	}
	
	public void damagePlayer() {
		this.lives--;
		System.out.println("PLAYER LIVES: " + this.lives);
	}
	
	public double getPlayerX() {
		return this.getImageView().getLayoutX();
	}
	
	public double getPlayerY() {
		return this.getImageView().getLayoutY();
	}
	
	public int getLives() {
		return this.lives;
	}
    public int getScore() {
    		return this.scoreCounter;
    }
    
    public void incrementScore() {
    		this.scoreCounter++;
    }
    
    public boolean getCanBeDamaged() {
    		return this.canBeDamaged;
    }
    
    public void setCanBeDamaged(boolean value) {
    		this.canBeDamaged = value;
    }
    
    public boolean getCanDropBomb() {
    		return this.canSpawnBomb;
    }
    
    public void setCanDropBomb(boolean value) {
    		this.canSpawnBomb = value;
    }
    

}
