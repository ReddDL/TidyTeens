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
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public ImageView getImageView() {
		return this.imageview;
	}
	
	// setters
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public Bounds getBounds() {
		return getImageView().getBoundsInParent();
	}
	
}
