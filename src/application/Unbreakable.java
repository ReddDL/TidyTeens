package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class Unbreakable extends Rectangle{
	
	@FXML private Rectangle Bed;
	@FXML private Rectangle Plant;
	@FXML private Rectangle Shelf1;
	@FXML private Rectangle Shelf2;
	@FXML private Rectangle Box1;
	@FXML private Rectangle Box2;
	@FXML private Rectangle Box3;
	@FXML private Rectangle Box4;
	@FXML private Rectangle Cabinet1;
	@FXML private Rectangle TVset;
	@FXML private Rectangle chair1;
	@FXML private Rectangle chair2;
	@FXML private Rectangle Table1;
	@FXML private Rectangle Desk1;
	@FXML private Rectangle Desk2;
	@FXML private Rectangle Box5;
	@FXML private Rectangle Box6;
	@FXML private Rectangle Cabinet2;
	@FXML private Rectangle RightWall;
	@FXML private Rectangle TopWall;
	@FXML private Rectangle LeftWall;
	@FXML private Rectangle BottomWall;
	
	private ArrayList<Rectangle> unbreakableObjects;

	
	public ArrayList<Rectangle> createUnbreakable() {
		unbreakableObjects = new ArrayList<>();

		unbreakableObjects.add(Bed);
		unbreakableObjects.add(Cabinet1);
		unbreakableObjects.add(Shelf1);
		unbreakableObjects.add(Shelf2);
		unbreakableObjects.add(Box1);
		unbreakableObjects.add(Box2);
		unbreakableObjects.add(Box3);
		unbreakableObjects.add(Box4);
		unbreakableObjects.add(Plant);
		unbreakableObjects.add(TVset);
		unbreakableObjects.add(chair1);
		unbreakableObjects.add(chair2);
		unbreakableObjects.add(Table1);
		unbreakableObjects.add(Desk1);
		unbreakableObjects.add(Desk2);
		unbreakableObjects.add(Box5);
		unbreakableObjects.add(Box6);
		unbreakableObjects.add(Cabinet2);
		unbreakableObjects.add(RightWall);
		unbreakableObjects.add(TopWall);
		unbreakableObjects.add(LeftWall);
		unbreakableObjects.add(BottomWall);
		return unbreakableObjects;
	}
	
    public Bounds getUnbreakableBounds(Rectangle rectangle) {
        return rectangle.getBoundsInParent();
    }
}
