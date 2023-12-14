/*
 * CollisionHandler
 * - Contains different methods for handling collisions between different entities
 */


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
	public boolean checkCollisionUnbreakables(ImageView player, ArrayList<Rectangle> unbreakableObjects) {
        Bounds playerBounds = player.getBoundsInParent();
        
        for (Rectangle rectangle : unbreakableObjects) {
        		
            Bounds rectangleBounds = rectangle.getBoundsInParent();

            if (playerBounds.intersects(rectangleBounds)) {
            		return true;
            } 
        }
        return false;
    }
	
	/*
	 * checkCollisionBreakables
	 * returns true if the player collides with a breakable
	 * returns false if the player collides with a breakable that has already been destroyed
	 * 
	 */
	public boolean checkCollisionBreakables(ImageView player, ArrayList<ImageView> breakableObjects) {
		Bounds playerBounds = player.getBoundsInParent();
		
		for (ImageView breakable : breakableObjects) {
			Bounds breakableBounds = breakable.getBoundsInParent();
			if (playerBounds.intersects(breakableBounds)) {
				if (breakable.getImage() == null) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * enemyCollision()
	 * - Iterates through an array of enemies
	 * - Returns true if any of the enemies has collided with the player
	 * - Returns false if not
	 */
	public boolean enemyCollision(Player player, ArrayList<Enemy> enemies) {
		for(Enemy e: enemies) {
			Bounds enemyBounds = e.getBounds();
			if(player.getBounds().intersects(enemyBounds)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * bombBreakableCollision()
	 * - Iterates through the breakable objects
	 * - Returns true if the bomb has collided with a breakable object and sets the breakable as null
	 * - Returns false if not
	 */
	public boolean bombBreakableCollision(ImageView bomb, ArrayList<ImageView> breakableObjects) {
		Bounds bombBounds = bomb.getBoundsInParent();
		
		for (ImageView breakable : breakableObjects) {
			Bounds breakableBounds = breakable.getBoundsInParent();
			if (bombBounds.intersects(breakableBounds)) {
				if (breakable.getImage() != null) {
					breakable.setImage(null);
					return true;
				}
			}
		}
		return false;
	}
}
