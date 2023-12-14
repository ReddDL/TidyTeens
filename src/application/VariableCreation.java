/*
 * variableCreation
 * - handles repeated variable creation
 * 
 * 
 */

package application;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VariableCreation{

	// STYLES
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
	
	/*
	 * createText()
	 * - creates a text field 
	 */
	public Text createText(String inputText) {
		Text text = new Text();
		text.setText(inputText);
		text.setFont(this.getFont(FONT_CSGORDON_REGULAR, BUTTON_HEIGHT));
		return text;
	}
	
	// This method sets the font style and size
	Font getFont(String path, int size) {
		return Font.loadFont(getClass().getResourceAsStream(path), size);
	}
	
	
}