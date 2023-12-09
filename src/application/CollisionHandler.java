package application;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class CollisionHandler {
	
	
	
	
	/*
	 * checkCollision
	 * returns true if the player collides with an unbreakable
	 * returns false if the player does not collide with unbreakable
	 */
	public boolean checkCollisionUnbreakables(Player player, ArrayList<Rectangle> unbreakableObjects) {
        Bounds playerBounds = player.getPlayerBounds();
        
        for (Rectangle rectangle : unbreakableObjects) {
        		
            Bounds rectangleBounds = rectangle.getBoundsInParent();

            if (playerBounds.intersects(rectangleBounds)) {
//            		System.out.println("There is a collision with " + rectangle.getId());		
            	System.out.println("You can't go there!");
            	return true;
            } 
        }
        return false;
    }
	
	/*
	 * checkCollisionBreakables
	 * returns true if the player collides with a breakable
	 * returns false if the player collides with a breakable that has already 
	 * been destroyed
	 * 
	 * 
	 * TODO: Can be used for bomb logic
	 */
	public boolean checkCollisionBreakables(Player player, ArrayList<ImageView> breakableObjects) {
		Bounds playerBounds = player.getPlayerBounds();
		
		for (ImageView breakable : breakableObjects) {
			Bounds breakableBounds = breakable.getBoundsInParent();
			if (playerBounds.intersects(breakableBounds)) {
				if (breakable.getImage() == null) {
//					System.out.println("Cleared already");
					// Cleared already
					return false;
				} else {

					System.out.println("Picked up trash");
				breakable.setImage(null);
				return true;
				}
			}
		}
		return false;
	}
}
