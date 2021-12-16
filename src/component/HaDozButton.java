package component;

import java.io.InputStream;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class HaDozButton extends Button {
	
	private final String FONT_PATH = "/component/res/PressStart2P-vaV7.ttf";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/component/res/green_button05_pressed.png');";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/component/res/green_button04_spacebar.png');";
	
	public HaDozButton(String text) {
		// TODO Auto-generated constructor stub
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListener();
		
	}
	
	public void setButtonFont() {
		InputStream i = getClass().getResourceAsStream(FONT_PATH);
		setFont(Font.loadFont(i, 14));
		
	}
	
	public void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
	
	}
	
	public void setButtonFreeStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
	}
	
	public void initializeButtonListener() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// press the button
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
			
			
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				// release the button
				if(e.getButton().equals(MouseButton.PRIMARY)) {
					setButtonFreeStyle();
				}
			}
			
		});

	}
}

