/*
 * Player
 * - Handles all of the player logic (movements and abilities)
 * 
 */


package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Player extends Sprite{
	@FXML private ImageView player;
	@FXML AnchorPane scene;
	
	// Attributes
	private int lives;
	private int scoreCounter;
	private boolean canBeDamaged = true;
	private boolean canDropBomb = true;
    private Timeline bombCooldownTimeline;

    // final variables
    private static final double MOVEMENT_SPEED = 1.5;
    private static final double BOMB_COOLDOWN = 3;
	
	// different images for keypress
	private Image playerUp = new Image("images/PlayerUp.png");
	private Image playerLeft = new Image("images/PlayerLeft.png");
	private Image playerRight = new Image("images/PlayerRight.png");
	private Image playerDown = new Image("images/StandingPlayer.png");
	
	// if a key is pressed or not
	private BooleanProperty upPressed = new SimpleBooleanProperty();
	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanProperty downPressed = new SimpleBooleanProperty();
	private BooleanProperty spacePressed = new SimpleBooleanProperty();
	
	private BooleanBinding keyPressed = upPressed.or(leftPressed).or(rightPressed).or(downPressed).or(spacePressed);
	
	
	CollisionHandler collisionHandler = new CollisionHandler();
	PlayerController playerController;
	public ImageView bombImage;

    // Player constructor
    public Player(ImageView player, double x, double y, int lives, int scoreCounter, ImageView bombImage, PlayerController playerController) {
        super(x,y,player);
        this.lives = lives;
        this.scoreCounter = scoreCounter;
        this.playerController = playerController;
        this.bombImage = bombImage;
        initializeBombCooldownTimeline();

    }
    
    /*
     * dropBomb()
     * - initializes a bomb 
     * - gets the location of the player and drops a bomb at that location
     * - starts a timeline that starts the cooldown of the bomb
     * - sets the player canSpawnBomb as false
     */
    public void dropBomb() {
        if (canDropBomb) {
        		System.out.println("You have dropped a bomb");
            Bomb bomb = new Bomb(bombImage, this.getImageView().getLayoutX(), this.getImageView().getLayoutY(), scene);
            this.setCanDropBomb(false);

            bomb.getImageView().setLayoutX(this.getImageView().getLayoutX() - 20);
            bomb.getImageView().setLayoutY(this.getImageView().getLayoutY() - 5);
            scene.getChildren().add(bomb.getImageView());

            bombCooldownTimeline.playFromStart();
            
            bomb.setBombVisible(true);

            this.canDropBomb = false;
        } else {
            System.out.println("Bomb on cooldown");
        }
    }
    
    /*
     * initializeBombCooldownTimeline()
     * - Initializes the bomb cooldown timeline
     * - sets the canDropBomb as true if the cooldown finishes
     * 
     */
    private void initializeBombCooldownTimeline() {

        bombCooldownTimeline = new Timeline(new KeyFrame(
                Duration.seconds(BOMB_COOLDOWN),
                event -> {
                	System.out.println("You can drop a bomb again");
                    this.setCanDropBomb(true);
                }
        ));
    }

    /* makeMovable()
     * - moves the player in the direction of the key press
     * - starts the timer on key press
     * - stops the timer on key release
     */
	public void makeMovable(ImageView player, AnchorPane scene) {
		this.player = player;
		this.scene = scene;
		
		keyHandler();
		
		keyPressed.addListener(((observableValue, aBoolean, t1) -> {
			if (!aBoolean) {
				timer.start();
			} else {
				timer.stop();
			}
		}));
	}
	
	/*
	 * An animation timer that runs the moving animation
	 * 
	 * 
	 */
	AnimationTimer timer = new AnimationTimer() {		
		@Override
		public void handle(long timestamp) {
			
			double currentX = player.getLayoutX();
			double currentY = player.getLayoutY();
			
			if(upPressed.get()) {
				player.setLayoutY(player.getLayoutY() - MOVEMENT_SPEED);
			}
			if(downPressed.get()){
				player.setLayoutY(player.getLayoutY() + MOVEMENT_SPEED);
			}
			if(leftPressed.get()){
				player.setLayoutX(player.getLayoutX() - MOVEMENT_SPEED);
			}
			if(rightPressed.get()){
				player.setLayoutX(player.getLayoutX() + MOVEMENT_SPEED);
		    }
			
			// checks collision after movement
			playerController.checkCollision(currentX, currentY);
			
		}
	};
	
	/*
	 * keyHandler()
	 * - handles key presses and key releases
	 * 
	 */
	private void keyHandler(){
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
			if (e.getCode() == KeyCode.SPACE) {
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
	
	// If the player is damaged, decrement the lives
	public void damagePlayer() {
		this.lives--;
		System.out.println("PLAYER LIVES: " + this.lives);
	}
	
	public void incrementScore(int score) {
		this.scoreCounter += score;
	}
	
	// GETTERS
	
	public int getLives() {
		return this.lives;
	}
    public int getScore() {
    		return this.scoreCounter;
    }
    
    public boolean getCanBeDamaged() {
    	return this.canBeDamaged;
    }
    
    public boolean getCanDropBomb() {
    	return this.canDropBomb;
    }
    
    // SETTERS
    
    public void setCanBeDamaged(boolean value) {
    		this.canBeDamaged = value;
    }
    
    public void setCanDropBomb(boolean value) {
    		this.canDropBomb = value;
    }
    

}
