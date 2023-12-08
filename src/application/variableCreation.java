/*
 * This is a class that handles repeated component creation
 * 
 * 
 * 
 */


package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class variableCreation implements EventHandler<ActionEvent> {
	// TODO: Create stage creator method
	// BUTTON STYLES
	public static final int BUTTON_HEIGHT = 60;
	public static final int BUTTON_WIDTH = 240;
	public static final String BUTTON_CORNER = " -fx-background-radius: 10;";
	public static final String BUTTON_TEAL = "-fx-background-color: #CEF8F4;";
	
	public final static String FONT_ARPONA_REGULAR = "/fonts/ArponaSans-Regular.otf";
	public final static String FONT_CSGORDON_REGULAR = "/fonts/CSGordon-Regular.otf";
	
	// This method creates a button given a text
	public Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle(BUTTON_TEAL + BUTTON_CORNER);
        button.setFont(this.getFont(FONT_CSGORDON_REGULAR, 20));
        button.setMinWidth(BUTTON_WIDTH);
        button.setMinHeight(BUTTON_HEIGHT);
        return button;
	}
	
	// This method sets the font style and size
	Font getFont(String path, int size) {
		return Font.loadFont(getClass().getResourceAsStream(path), size);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}