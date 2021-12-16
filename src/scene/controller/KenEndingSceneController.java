package scene.controller;

import component.EndingText;
import component.ExitText;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

public class KenEndingSceneController {

	private AnchorPane mainPane;
	private Scene mainScene;

	private AudioClip sound = new AudioClip(ClassLoader.getSystemResource("component/res/ken-ending.wav").toString());
	private final String IMGPATH = "/scene/controller/res/ken-ending.gif";
	private EndingText winnerText;
	private ExitText exitText;

	public KenEndingSceneController() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, 1024, 576);
		createBackground();
		createWinnerText("Winner!! is K E N");
		createExitText("Press space to "
				+ "exit.");
		exit();
		// TODO Auto-generated constructor stub
	}

	public void exit() {
		mainScene.setOnKeyPressed((KeyEvent e) -> {
			String new_code = e.getCode().toString();
			if (new_code.equals("SPACE"))
				System.exit(0);
		});
	}

	public void playSound() {
		sound.play();
	}

	public void createBackground() {
		Image kenEnding = new Image(IMGPATH, 1024, 576, false, true);
		BackgroundImage background = new BackgroundImage(kenEnding, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	public void createWinnerText(String text) {
		winnerText = new EndingText(text, Color.BLACK);
		mainPane.getChildren().add(winnerText);
		winnerText.relocate(120, 30);
	}

	public void createExitText(String text) {
		exitText = new ExitText(text, Color.BLACK);
		mainPane.getChildren().add(exitText);
		exitText.relocate(165, 450);
	}

	public AnchorPane getMainPane() {
		return mainPane;
	}

	public void setMainPane(AnchorPane mainPane) {
		this.mainPane = mainPane;
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

}
