/*
 * Bomb
 * - Handles the bomb logic and attributes
 * 
 */

package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Bomb extends Sprite{

	AnchorPane scene;
    private Timeline removalTimeline;

    private static final double BOMB_DURATION = 1.5;
    private MediaPlayer splashSound;


    // Bomb Constructor
    public Bomb(ImageView bombView, double x, double y, AnchorPane scene) {
    		super(x,y,bombView);
    		this.scene = scene;
    		initializeBombSound();
    		initializeRemovalTimeline();

    }
    
    /*
     * initializeBombSound()
     * - initializes the sound to be used when a bomb is dropped
     */
    private void initializeBombSound() {
	    Media startSoundMedia = new Media(getClass().getResource("splashSound.mp3").toString());
        splashSound = new MediaPlayer(startSoundMedia);
    }
    
    /*
     * initializeRemovalTimeline()
     * - creates a timeline in the duration of the bomb
     * 
     */
    private void initializeRemovalTimeline() {
        removalTimeline = new Timeline(new KeyFrame(
                Duration.seconds(BOMB_DURATION),
                event -> removeBombFromScene()
        ));
    }
    	
    /*
     * setBombVisible()
     * - if the bomb is set as visible, starts the removal timeline, plays the splash sound
     * - else, stops the timeline
     * 
     */
    public void setBombVisible(boolean value) {

        if (value) {
            if (!splashSound.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                splashSound.play();
            }
            removalTimeline.playFromStart();
        } else {
        		splashSound.stop();
            removalTimeline.stop();
        }
    }
    
    /*
     * removeBombFromScene()
     * - removes the bomb from the scene
     * 
     */
    private void removeBombFromScene() {
        scene.getChildren().remove(getImageView());
    }
}
