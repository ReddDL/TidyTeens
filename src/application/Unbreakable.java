package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;

public class Unbreakable extends Rectangle{
	
	private Rectangle unbreakable;
	private Rectangle Bed;
	private Rectangle Plant;
	private Rectangle Shelf1;
	private Rectangle Shelf2;
	private Rectangle Box1;
	private Rectangle Box2;
	private Rectangle Box3;
	private Rectangle Box4;
	private Rectangle Cabinet1;
	private Rectangle TVset;
	private Rectangle chair1;
	private Rectangle chair2;
	private Rectangle Table1;
	private Rectangle Desk1;
	private Rectangle Desk2;
	private Rectangle Box5;
	private Rectangle Box6;
	private Rectangle Cabinet2;
	private Rectangle RightWall;
	private Rectangle TopWall;
	private Rectangle LeftWall;
	private Rectangle BottomWall;
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
	public Bounds getUnbreakableBounds() {
		return unbreakable.getBoundsInParent();
	}
}
