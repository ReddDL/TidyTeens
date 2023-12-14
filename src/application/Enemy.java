package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Enemy extends Sprite {
	
    private Random random;
    private CollisionHandler ch = new CollisionHandler();
    
    // Enemy Constructor
    public Enemy(ImageView image, double x, double y) {
        super(x, y, image);
        this.random = new Random();
    }


    /*
     * moveEnemy()
     * - moves the enemy depending on where the player is
     */
    public void moveEnemy(Player player, ArrayList<Rectangle> unbreakables, ArrayList<ImageView> breakables) {
        ImageView enemyImage = getImageView();
        double xcoord = enemyImage.getLayoutX();
        double ycoord = enemyImage.getLayoutY();
        double xmod = (player.getImageView().getLayoutX() > xcoord ? 1: -1);
        double ymod = (player.getImageView().getLayoutY() > ycoord ? 1: -1);
//        int mod2 = (random.nextInt(1) == 1 ? 1: -1);

        enemyImage.setLayoutX(xcoord + random.nextInt(2)*xmod);
        if(ch.checkCollisionUnbreakables(getImageView(), unbreakables)) {
            enemyImage.setLayoutX(xcoord);
        }
        
        enemyImage.setLayoutY(ycoord + random.nextInt(2)*ymod);
        if(ch.checkCollisionUnbreakables(getImageView(), unbreakables)) {
            enemyImage.setLayoutY(ycoord);
        }      
    }


}
