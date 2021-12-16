package component;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class StartSubScene extends SubScene {

	private static final String BACKGROUND_IMAGE = "/component/res/text_box_8-bit.png";

	private boolean toHide;

	public StartSubScene() {
		super(new AnchorPane(), 750, 500);
		prefWidth(750);
		prefHeight(500);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 750, 500, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane subRoot = (AnchorPane) this.getRoot();

		subRoot.setBackground(new Background(image));

		setLayoutX(150);
		setLayoutY(-500);

	}

	public void moveSubScene() {
		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.seconds(0.3));
		translate.setNode(this);
		if(!toHide) {
			translate.setToY(538);
		}else {
			translate.setToY(-538);
			toHide = false;
		}

		translate.play();

	}

	
	public void setToHide(boolean Hide) {
		this.toHide = Hide;
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
