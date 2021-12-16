package component;

import javafx.scene.control.Button;

public class BackButton extends Button{
	
	private final String BACK_BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/component/res/grey_boxCross.png');";
	
	public BackButton() {
		// TODO Auto-generated constructor stub
		setPrefSize(37, 36);
		setStyle(BACK_BUTTON_STYLE);
	}

}
