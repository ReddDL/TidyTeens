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

public class Player{
//	private ImageView player;
	@FXML private ImageView player;
	@FXML AnchorPane scene;
	private double x;
	private double y;
	private int lives;
	private int scoreCounter;
	private BooleanProperty upPressed = new SimpleBooleanProperty();
	private BooleanProperty leftPressed = new SimpleBooleanProperty();
	private BooleanProperty rightPressed = new SimpleBooleanProperty();
	private BooleanProperty downPressed = new SimpleBooleanProperty();
	
	private BooleanBinding keyPressed = upPressed.or(leftPressed).or(rightPressed).or(downPressed);
	
	private int movementVariable = 2;
	
	CollisionHandler collisionHandler = new CollisionHandler();

		
    public Player(ImageView player, double x, double y, int lives, int scoreCounter) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.scoreCounter = scoreCounter;
    }
    
    /* move
     * moves the player in the direction of the 
     * keypress 
     * 
     */
//    public void move(KeyEvent e) {
//        switch (e.getCode()) {
//            case UP:
//                System.out.println("Moved up");
//                	player.setY(y -= 5);
//                break;
//            case DOWN:
//                System.out.println("Moved down");
//                player.setY(y += 5);
//                break;
//            case LEFT:
//                System.out.println("Moved left");
//                player.setX(x -= 5);
//                break;
//            case RIGHT:
//                System.out.println("Moved right");
//                player.setX(x += 5);
//                break;
//            default:
//                break;
//        }
//    }
	public void makeMovable(ImageView player, AnchorPane scene) {
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
			double currrentY = player.getLayoutY();
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
			
//			if (collisionHandler.checkCollisionUnbreakables(player, unbreakableObjects)) {
//				player.setLayoutX(currentX);
//				player.setLayoutY(currrentY);
//			}
		
//			if (collisionHandler.checkCollisionBreakables(player, breakableObjects)) {
//				player.setLayoutX(currentX);
//				player.setLayoutY(currrentY);
////				pointChecker();
//			}
			
		}
	};
	
	public boolean playerCollidesWithUnbreakable(ArrayList <Rectangle> unbreakables) {
		if (collisionHandler.checkCollisionUnbreakables(this, unbreakables)) {
			return true;
		} else {
			return false;
		}
	}
	
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
    public int getScore() {
    		return this.scoreCounter;
    }
    
    public void incrementScore() {
    		this.scoreCounter++;
    }
    
    public double returnX() {
    		return player.getX();
    }
    
    public double returnY() {
    		return player.getY();
    }
    
    public void newX(double x) {
    		player.setX(x);
    }
    
    public void newY(double y) {
    		player.setY(y);
    }
    
    public Bounds getPlayerBounds() {
        return player.getBoundsInParent();
    }

  
    public ImageView getPlayerImageView() {
        return player;
    }

}
