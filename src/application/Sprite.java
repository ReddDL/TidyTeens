package application;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Sprite {
	private ImageView imageview;
	private double x; 
	private double y;
	
	public Sprite(double x, double y, ImageView imageview) {
		this.x = x;
		this.y = y;
		this.imageview = imageview;
	}
	
	// getters
	public ImageView getImageView() {
		return this.imageview;
	}
	public double getX() {
		return this.getImageView().getLayoutX();
	}
	
	public double getY() {
		return this.getImageView().getLayoutY();
	}
	
	
	// setters
	public void setX(double x) {
		this.getImageView().setLayoutX(x);
	}
	
	public void setY(double y) {
		this.getImageView().setLayoutY(y);
	}

	public Bounds getBounds() {
		return getImageView().getBoundsInParent();
	}
	
}
