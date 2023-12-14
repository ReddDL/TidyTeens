package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bomb extends Sprite{

//    private ImageView bombImageView;
    private Timeline animationTimeline;
    private Image[] bombFrames;
    private int currentFrameIndex;
    
    private double x;
    private double y;
    
    private boolean bombVisible = false;
    private Timeline removalTimeline;
    AnchorPane scene;

    
    public Bomb(ImageView bombView, double x, double y, AnchorPane scene) {
    		super(x,y,bombView);
    		this.scene = scene;
    		initializeRemovalTimeline();

    }
    
    private void initializeRemovalTimeline() {
        removalTimeline = new Timeline(new KeyFrame(
                Duration.seconds(1.5), // Adjust this duration as needed
                event -> removeBombFromScene()
        ));
    }
    
    public void setBombVisible(boolean value) {
        this.bombVisible = value;

        if (value) {
            removalTimeline.playFromStart();
        } else {
            removalTimeline.stop();
        }
    }
    
    public boolean getBombVisible() {
    		return this.bombVisible;
    }
    
    private void removeBombFromScene() {
//        getImageView().setVisible(false);
        // Remove the bomb's ImageView from the scene
        scene.getChildren().remove(getImageView());
    }
}
