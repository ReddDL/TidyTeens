package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
	@FXML private ImageView player;
	@FXML AnchorPane scene;
	private double x;
	private double y;
	private int lives;
	private int scoreCounter;
	
	private boolean canBeDamaged = true;
	
	private BooleanProperty upPressed = new SimpleBooleanProperty();
	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanProperty downPressed = new SimpleBooleanProperty();
	
	private BooleanBinding keyPressed = upPressed.or(leftPressed).or(rightPressed).or(downPressed);
		
	private double movementVariable = 2;
	
	CollisionHandler collisionHandler = new CollisionHandler();
	PlayerController playerController;

    public Player(ImageView player, double x, double y, int lives, int scoreCounter, PlayerController playerController) {
        super(x,y,player);
        this.lives = lives;
        this.scoreCounter = scoreCounter;
        this.playerController = playerController;
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
			
			playerController.checkCollision(currentX, currentY);
			
		}
	};
	
	private void movementSetup(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.W) {
				upPressed.set(true);
			}
			if(e.getCode() == KeyCode.A) {
				leftPressed.set(true);
			}
			if(e.getCode() == KeyCode.S) {
				downPressed.set(true);
			}
			if(e.getCode() == KeyCode.D) {
				rightPressed.set(true);
			}
		});
		scene.setOnKeyReleased(e ->{
			if(e.getCode() == KeyCode.W) {
				upPressed.set(false);
			}
			if(e.getCode() == KeyCode.A) {
				leftPressed.set(false);
			}
			if(e.getCode() == KeyCode.S) {
				downPressed.set(false);
			}
			if(e.getCode() == KeyCode.D) {
				rightPressed.set(false);
			}
		});
	}
	
	public void damagePlayer() {
		this.lives--;
		System.out.println("PLAYER LIVES: " + this.lives);
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
    

}
