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
import javafx.util.Duration;

public class Bomb extends Sprite{

	AnchorPane scene;
    private Timeline removalTimeline;

    private static final double BOMB_DURATION = 1.5;

    // Bomb Constructor
    public Bomb(ImageView bombView, double x, double y, AnchorPane scene) {
    		super(x,y,bombView);
    		this.scene = scene;
    		initializeRemovalTimeline();

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
     * - if the bomb is set as visible, starts the removal timeline
     * - else, stops the timeline
     * 
     */
    public void setBombVisible(boolean value) {

        if (value) {

            removalTimeline.playFromStart();
        } else {
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
