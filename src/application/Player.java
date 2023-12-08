package application;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class Player{
	private ImageView player;
	private double x;
	private double y;
	private int lives;
	private int scoreCounter;
		
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
    public void move(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                System.out.println("Moved up");
                	player.setY(y -= 5);
                break;
            case DOWN:
                System.out.println("Moved down");
                player.setY(y += 5);
                break;
            case LEFT:
                System.out.println("Moved left");
                player.setX(x -= 5);
                break;
            case RIGHT:
                System.out.println("Moved right");
                player.setX(x += 5);
                break;
            default:
                break;
        }
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
