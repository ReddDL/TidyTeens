package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bomb {

    private ImageView bombImageView;
    private Timeline animationTimeline;
    private Image[] bombFrames;
    private int currentFrameIndex;
    
    private double x;
    private double y;
    
    private boolean bombVisible = false;
    
    public Bomb(double x, double y) {
    		this.x = x;
    		this.y = y;
    		this.bombImageView = new ImageView();
    		this.bombImageView.setLayoutX(x);
    		this.bombImageView.setLayoutY(y);
    }
    /*
     * Animates sprite
     * 
     * 
     * 
     */
    
    
    public void createAnimationTimeline() {
        int numFrames = 12; // Assuming you have 12 frames (000.png to 011.png)
        bombFrames = new Image[numFrames];

        for (int i = 0; i < numFrames; i++) {
            String frameNumber = String.format("%03d", i); // Formats the number with leading zeros
            String fileName = "/images/" + frameNumber + ".png";
            bombFrames[i] = new Image(fileName);
        }

        animationTimeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> updateSpriteFrame())
        );
        animationTimeline.setCycleCount(Animation.INDEFINITE);
        animationTimeline.play(); // Start the animation
    }
    
    /*
     * 
     * 
     * 
     */

    public void updateSpriteFrame() {
        // Switch to the next frame
        if (bombVisible) {
            currentFrameIndex = (currentFrameIndex + 1) % bombFrames.length;
            bombImageView.setImage(bombFrames[currentFrameIndex]);
        }
    }
    
    public void setBombVisible(boolean value) {
    		this.bombVisible = value;
    		
    		if (!value) {
    			animationTimeline.stop();
    		}
    }
    
    public boolean getBombVisible() {
    		return this.bombVisible;
    }
    
    public ImageView getImageView() {
    		return this.bombImageView;
    }
}
