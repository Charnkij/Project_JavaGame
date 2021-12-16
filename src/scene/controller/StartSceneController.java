package scene.controller;

import java.util.ArrayList;
import java.util.List;
import component.BackButton;
import component.HaDozButton;
import component.HowToLabel;
import component.StartSubScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class StartSceneController {

	private static final int HEIGHT = 576;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private GameSceneController gameScene;
	private static Stage mainStage;

	private final static int MENU_BUTTONS_START_X = 417;
	private final static int MENU_BUTTONS_START_Y = 296;
	private AudioClip clickSound = new AudioClip(
			ClassLoader.getSystemResource("scene/controller/res/click1.wav").toString());
	private AudioClip startSound = new AudioClip(
			ClassLoader.getSystemResource("scene/controller/res/starting.wav").toString());

	private StartSubScene startSubScene;

	private List<HaDozButton> menuBtn;

	public StartSceneController() {
		// TODO Auto-generated constructor stub
		gameScene = new GameSceneController();
		menuBtn = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setTitle("Hadoz");
		mainStage.setScene(mainScene);
		createHowButton();
		createStartButton();
		createExitButton();
		createBackground();
		createLogo();
		createSubScene();

	}

	public static Stage getMainStage() {
		return mainStage;
	}

	public static void setMainStage(Stage mainStage) {
		StartSceneController.mainStage = mainStage;
	}

	private void addMenuButton(HaDozButton btn) {
		btn.setLayoutX(MENU_BUTTONS_START_X);
		btn.setLayoutY(MENU_BUTTONS_START_Y + menuBtn.size() * 70);

		menuBtn.add(btn);
		mainPane.getChildren().add(btn);
	}

	private void createSubScene() {
		startSubScene = new StartSubScene();
		mainPane.getChildren().add(startSubScene);

		HowToLabel label = new HowToLabel("How To Play");
		label.setLayoutX(200);
		label.setLayoutY(-150);

		ImageView panel = new ImageView(new Image("/scene/controller/res/HowToplay.png"));
		panel.setFitHeight(0.45 * panel.prefHeight(1));
		panel.setFitWidth(0.45 * panel.prefWidth(1));
		panel.relocate((double) (125), (double) (130));
		ImageView ryu = new ImageView(new Image("/scene/controller/res/ryu_player.gif"));
		ImageView ken = new ImageView(new Image("/scene/controller/res/ken_player.gif"));
		ryu.setFitHeight(0.4 * ryu.prefHeight(1));
		ryu.setFitWidth(0.4 * ryu.prefWidth(1));
		ryu.relocate((double) (550), (double) (167));
		ryu.setScaleX(-1);
		ken.setFitHeight(0.4 * ken.prefHeight(1));
		ken.setFitWidth(0.4 * ken.prefWidth(1));
		ken.relocate((double) (440), (double) (160));
		BackButton bBtn = new BackButton();
		bBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clickSound.play();
				startSubScene.setToHide(true);
				startSubScene.moveSubScene();
			}
		});
		bBtn.setLayoutX(605);
		bBtn.setLayoutY(80);

		startSubScene.getPane().getChildren().addAll(label, panel, ryu, ken, bBtn);

	}

	private void createStartButton() {
		HaDozButton sBtn = new HaDozButton("Start !");
		sBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// transition to sub scene
				clickSound.play();
				startSound.play();
				switchScenes(gameScene.getMainScene());
			}
		});
		addMenuButton(sBtn);
	}

	private void createHowButton() {
		HaDozButton hBtn = new HaDozButton("How To Play");
		addMenuButton(hBtn);

		hBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// transition to sub scene
				clickSound.play();
				startSubScene.moveSubScene();
			}
		});
	}

	private void createExitButton() {
		HaDozButton eBtn = new HaDozButton("Exit");
		addMenuButton(eBtn);

		eBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				clickSound.play();
				mainStage.close();
				System.exit(-1);
			}
		});
	}

	private void switchScenes(Scene scene) {
		mainStage.setScene(scene);
		gameScene.playSound();
	}

	private void createLogo() {
		ImageView logo = new ImageView(new Image("/scene/controller/res/another-logo-gif.gif"));
		logo.setFitHeight(0.9 * logo.prefHeight(1));
		logo.setFitWidth(0.9 * logo.prefWidth(1));
		logo.relocate((double) (240), (double) (70));

		mainPane.getChildren().add(logo);

	}

	private void createBackground() {
		Image backgroundImage = new Image("/scene/controller/res/RoadInJapan.png", 1024, 576, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

}
