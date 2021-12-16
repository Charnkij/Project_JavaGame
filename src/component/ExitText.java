package component;

import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ExitText extends Label {

	private final String FONT_PATH = "/component/res/PressStart2P-vaV7.ttf";

	public ExitText(String text, Paint color) {
		// TODO Auto-generated constructor stub
		setPrefSize(800, 75);
		setPadding(new Insets(40));
		setText(text);
		setWrapText(true);
		setLabelFont(color);
	}

	private void setLabelFont(Paint color) {
		InputStream i = getClass().getResourceAsStream(FONT_PATH);
		setFont(Font.loadFont(i, 32));
		setTextFill(color);
	}
}
