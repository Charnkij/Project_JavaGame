package component;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HowToLabel extends Label {
	
	private final String FONT_PATH = "/component/res/PressStart2P-vaV7.ttf";
	

	public HowToLabel(String text) {
		// TODO Auto-generated constructor stub
		setPrefSize(750, 500);
		setPadding(new Insets(40));
		setText(text);
		setWrapText(true);
		setLabelfont();
		
	}
	
	public void setLabelfont() {
		InputStream i = getClass().getResourceAsStream(FONT_PATH);
		setFont(Font.loadFont(i, 23));
		
	}
}
