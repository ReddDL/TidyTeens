package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Enemy extends Sprite {
    private Random random;
    private CollisionHandler ch = new CollisionHandler();
//    private ArrayList<ImageView> parent;

    public Enemy(ImageView image, double x, double y) {
        super(x, y, image);
//        this.player = player;
        this.random = new Random();
//        this.ch = new CollisionHandler();
//        this.parent = parent;
    }

//    private static Enemy createEnemy(ImageView image, double x, double y, Player player, ArrayList<ImageView> parent) {
//        Enemy newEnemy = new Enemy(image, x, y, player, parent);
//        System.out.println(image.getId());
//        return newEnemy;
//    }

    // TO ADD:
    // rotate enemy model depending on xmod and y mod
    public void moveEnemy(Player player, ArrayList<Rectangle> unbreakables, ArrayList<ImageView> breakables) {
        ImageView enemyImage = getImageView();
        double xcoord = enemyImage.getLayoutX();
        double ycoord = enemyImage.getLayoutY();
        double xmod = (player.getImageView().getLayoutX() > xcoord ? 1: -1);
        double ymod = (player.getImageView().getLayoutY() > ycoord ? 1: -1);
        int mod2 = (random.nextInt(1) == 1 ? 1: -1);
//        System.out.println("x: " + this.getImageView().getLayoutX() + " y: " + this.getImageView().getLayoutY());
//        System.out.println("xmod: " + xmod + " ymod: " + ymod);
//        if (ymod == 1) {System.out.println("Enemy below.");}

        enemyImage.setLayoutX(xcoord + random.nextInt(2)*xmod);
        if(ch.checkCollisionUnbreakables(getImageView(), unbreakables)) {
            enemyImage.setLayoutX(xcoord);
        }
        
        enemyImage.setLayoutY(ycoord + random.nextInt(2)*ymod);
        if(ch.checkCollisionUnbreakables(getImageView(), unbreakables)) {
            enemyImage.setLayoutY(ycoord);
        }

//        if(enemyImage.getBoundsInLocal().intersects(this.player.getPlayerBounds())) {
//            System.out.println("Colliding with player.");
//        }
//        
    }

//    public static void makeEnemiesMove(ArrayList<Enemy> enemyList, ArrayList<Rectangle> unbreakables) {
////        System.out.println("Start of makeEnemiesMove().");
//        for(Enemy enemy: enemyList) {
//            enemy.moveEnemy(unbreakables);
//            // ImageView currimage = enemy.getImageView();
//            // currimage.setX(currimage.getX()+5);
//        }
//    }

//    public boolean playerCollision(Player player) {
//        if(this.getImageView().getBoundsInParent().intersects(player.getImageView().getBoundsInParent())) {
//            System.out.println("Colliding with player");
//        	return true;
//        }
//        return false;
//    }

    // TO FIX :
    // Stop enemy from moving when colliding with each other
//    private boolean enemytoenemycollision() {
//        for(ImageView enemy: this.parent) {
//            if(enemy.getId() == this.getImageView().getId()) {System.out.println("saw self");}
//            
//            Bounds others = enemy.getBoundsInLocal();
//            Bounds self = getImageView().getBoundsInLocal();
//            if (self.intersects(others)) {
//                System.out.println(this.getImageView().getId() + " collides with" + enemy.getId());
//                return true;
//            }
//        }
//        return false;
//    }

//    public static ArrayList<Enemy> generateEnemies(ArrayList<ImageView> enemyImages, Player player) {
//        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
//        for(ImageView image: enemyImages) {
//            enemyList.add(createEnemy(image, 0, 0, player, enemyImages));
////            System.out.println("Added enemy: " + image.getLayoutX());
//        }
////        System.out.println("Created enemies.");
//        return enemyList;
//    }
    

}
