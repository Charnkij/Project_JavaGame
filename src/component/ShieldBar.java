package component;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class ShieldBar extends Label{
	
	private final String FONT_PATH = "/component/res/PressStart2P-vaV7.ttf";

	public ShieldBar(String text) {
		// TODO Auto-generated constructor stub
		setPrefSize(350, 50);
		setPadding(new Insets(40));
		setText(text);
		setWrapText(true);
		setLabelfont();
	}
	
	private void setLabelfont() {
		// TODO Auto-generated method stub
		InputStream i = getClass().getResourceAsStream(FONT_PATH);
		setFont(Font.loadFont(i, 23));
	}
}
