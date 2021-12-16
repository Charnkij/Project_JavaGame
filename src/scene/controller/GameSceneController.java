package scene.controller;

import java.util.*;
import application.ThreadMain;
import component.CountLabel;
import component.HpBar;
import component.ShieldBar;
import entity.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameSceneController {
	private static final Image KEN = new Image("/scene/controller/res/ken_player.gif");
	private static final Image RYU = new Image("/scene/controller/res/ryu_player.gif");
	private static final Image BACKGROUND = new Image("/scene/controller/res/Background1.png");
	private static int countPlayer1, countPlayer2;
	private static PowerBall nextBallKen, nextBallRyu;
	private static final int HEIGHT = 576, WIDTH = 1024;

	private static boolean playSound = true;
	private ImageView kenn, ryuu;
	private static int kenPosY, ryuPosY;
	private static HpBar kenHpBar, ryuHpBar;
	private int kenPosX = 70, ryuPosX = 900;
	private static int kenHp = 100, ryuHp = 100;
	private static ShieldBar kenShielded, ryuShielded;
	private static boolean isKenShielded = false, isRyuShielded = false;
	private static boolean isKenDie = false, isRyuDie = false;

	private static AudioClip sceneSound;
	private static Item mainItem;

	private Scene mainScene;
	private ThreadMain threadMain;
	private static Stage mainStage;
	private static AnchorPane mainPane;
	private static RyuEndingSceneController ryuEndingScene;
	private static KenEndingSceneController kenEndingScene;
	private ImageView firePicRyu = new ImageView(entity.FireBall.getFireballl());
	private ImageView EarthPicRyu = new ImageView(entity.EarthBall.getEarthball());
	private ImageView WaterPicRyu = new ImageView(entity.WaterBall.getWaterballl());
	private ImageView firePicKen = new ImageView(entity.FireBall.getFireballl());
	private ImageView EarthPicKen = new ImageView(entity.EarthBall.getEarthball());
	private ImageView WaterPicKen = new ImageView(entity.WaterBall.getWaterballl());

	private static CountLabel txtCount1, txtCount2;
	boolean trigger = false;

	/*
	 * ================================================ Constructor
	 * ================================================
	 */
	public GameSceneController() {
		// TODO Auto-generated constructor stub
		sceneSound = new AudioClip(ClassLoader.getSystemResource("scene/controller/res/BG_sound.wav").toString());

		ryuEndingScene = new RyuEndingSceneController();
		kenEndingScene = new KenEndingSceneController();
		threadMain = new ThreadMain();
		countPlayer1 = 0;
		countPlayer2 = 0;

		txtCount1 = new CountLabel("0");
		txtCount2 = new CountLabel("0");
		mainPane = new AnchorPane();
		mainStage = new Stage();
		drawBackground();
		initializePlayer();
		initializeNextBallBar();
		setClickedCountedFont();
		createKenShieldBar();
		createRyuShieldBar();
		mainItem = new Item();
		mainItem.respawnItem();
		createKenHpBar(getKenHp());
		createRynHpBar(getRyuHp());
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		setOnCharged();
		mainStage.setScene(mainScene);
		mainStage.setTitle("Hadoz");
	}

	/*
	 * ================================================ Method
	 * ================================================
	 */

	public void setOnCharged() {

		mainScene.setOnKeyPressed((KeyEvent e) -> {
			String new_code = e.getCode().toString();
			if (new_code.equals("SPACE") && getCountPlayer1() != 0) {
				FireBall fB = new FireBall(100, getKenPosY(), 5);
				EarthBall eB = new EarthBall(100, getKenPosY(), 5);
				WaterBall wB = new WaterBall(100, getKenPosY(), 5);
				if (nextBallKen == null) {
					FireBall temp = new FireBall(100, getKenPosY(), 5);
					temp.setCount(getCountPlayer1());
					temp.createFirstPowerBall(getCountPlayer1());
					threadMain.initalizeNewPlayer1(temp);
				} else {
					nextBallKen.setCount(getCountPlayer1());
					nextBallKen.setY(getKenPosY());
					nextBallKen.createFirstPowerBall(getCountPlayer1());
					threadMain.initalizeNewPlayer1(nextBallKen);
				}
				int r = randomBall();
				appearNextBallKen(r);
				if (r == 0)
					nextBallKen = fB;
				if (r == 1)
					nextBallKen = eB;
				if (r == 2)
					nextBallKen = wB;
				countPlayer1 = 0;
				threadMain.updatePlayerCount(countPlayer1, countPlayer2);
			}
			if (new_code.equals("ENTER") && getCountPlayer2() != 0) {
				FireBall fB = new FireBall(900, getRyuPosY(), -5);
				EarthBall eB = new EarthBall(900, getRyuPosY(), -5);
				WaterBall wB = new WaterBall(900, getRyuPosY(), -5);
				if (nextBallRyu == null) {
					FireBall temp2 = new FireBall(900, getRyuPosY(), -5);
					temp2.setCount(getCountPlayer2());
					temp2.createFirstPowerBall(getCountPlayer2());
					threadMain.initalizeNewPlayer2(temp2);
				} else {
					nextBallRyu.setY(getRyuPosY());
					nextBallRyu.setCount(getCountPlayer2());
					nextBallRyu.createFirstPowerBall(getCountPlayer2());
					threadMain.initalizeNewPlayer2(nextBallRyu);
				}
				int r = randomBall();
				appearNextBallRyu(r);
				if (r == 0)
					nextBallRyu = fB;
				if (r == 1)
					nextBallRyu = eB;
				if (r == 2)
					nextBallRyu = wB;
				countPlayer2 = 0;
				threadMain.updatePlayerCount(countPlayer1, countPlayer2);
			}
			if (new_code.equals("A") || new_code.equals("D")) {
				countPlayer1++;
				threadMain.updatePlayerCount(countPlayer1, countPlayer2);
			}
			if (new_code.equals("RIGHT") || new_code.equals("LEFT")) {
				countPlayer2++;
				threadMain.updatePlayerCount(countPlayer1, countPlayer2);
			}

			switch (new_code) {
			case "W": {
				if (getKenPosY() - 170 < 0)
					break;
				kenn.relocate((double) getKenPosX(), (double) getKenPosY() - 170);
				setKenPosY(getKenPosY() - 170);
				break;
			}
			case "S": {
				if (getKenPosY() + 170 > 406)
					break;
				kenn.relocate((double) getKenPosX(), (double) getKenPosY() + 170);
				setKenPosY(getKenPosY() + 170);
				break;
			}
			case "UP": {
				if (getRyuPosY() - 170 < 0)
					break;
				ryuu.relocate((double) getRyuPosX(), (double) getRyuPosY() - 170);
				setRyuPosY(getRyuPosY() - 170);

				break;
			}
			case "DOWN": {
				if (getRyuPosY() + 170 > 406)
					break;
				ryuu.relocate((double) getRyuPosX(), (double) getRyuPosY() + 170);
				setRyuPosY(getRyuPosY() + 170);
				break;
			}
			}
		});
		mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				trigger = false;
			}
		});
	}

	protected void drawBackground() {
		ImageView backgroundImgView = new ImageView(BACKGROUND);
		mainPane.getChildren().add(backgroundImgView);
	}

	public static void randomItem() {
		mainItem = Item.randomItem();
		ImageView itemImage = mainItem.getItemImage(mainItem.getItemLabel());
		itemImage.relocate(mainItem.getPosX(), mainItem.randomPosY());
		mainPane.getChildren().add(itemImage);
	}

	public static void itemGotCatched() {
		mainPane.getChildren().remove(getMainItem().getItemImage());
		getMainItem().getItemImage().relocate((double) (-100), (double) (-100));
	}

	protected void initializePlayer() {
		kenn = new ImageView(KEN);
		kenn.setFitHeight(0.3 * kenn.prefHeight(1));
		kenn.setFitWidth(0.3 * kenn.prefWidth(1));
		kenn.relocate((double) (70), (double) (340));
		setKenPosX(70);
		setKenPosY(340);
		mainPane.getChildren().add(kenn);

		ryuu = new ImageView(RYU);
		ryuu.setScaleX(-1);
		ryuu.setFitHeight(0.3 * ryuu.prefHeight(1));
		ryuu.setFitWidth(0.3 * ryuu.prefWidth(1));
		mainPane.getChildren().remove(ryuu);
		ryuu.relocate((double) (900), (double) (340));
		setRyuPosX(900);
		setRyuPosY(340);
		mainPane.getChildren().add(ryuu);

	}

	protected void initializeNextBallBar() {
		firePicKen.relocate((double) (-200), (double) (500));
		firePicKen.setFitHeight(0.1 * firePicKen.prefHeight(1));
		firePicKen.setFitWidth(0.1 * firePicKen.prefWidth(1));
		EarthPicKen.relocate((double) (-200), (double) (500));
		EarthPicKen.setFitHeight(0.1 * EarthPicKen.prefHeight(1));
		EarthPicKen.setFitWidth(0.1 * EarthPicKen.prefWidth(1));
		WaterPicKen.relocate((double) (-200), (double) (500));
		WaterPicKen.setFitHeight(0.2 * WaterPicKen.prefHeight(1));
		WaterPicKen.setFitWidth(0.2 * WaterPicKen.prefWidth(1));
		mainPane.getChildren().addAll(firePicKen, EarthPicKen, WaterPicKen);

		firePicRyu.relocate((double) (-900), (double) (500));
		firePicRyu.setFitHeight(0.1 * firePicRyu.prefHeight(1));
		firePicRyu.setFitWidth(0.1 * firePicRyu.prefWidth(1));
		EarthPicRyu.relocate((double) (-900), (double) (500));
		EarthPicRyu.setFitHeight(0.1 * EarthPicRyu.prefHeight(1));
		EarthPicRyu.setFitWidth(0.1 * EarthPicRyu.prefWidth(1));
		WaterPicRyu.relocate((double) (-900), (double) (500));
		WaterPicRyu.setFitHeight(0.2 * WaterPicRyu.prefHeight(1));
		WaterPicRyu.setFitWidth(0.2 * WaterPicRyu.prefWidth(1));
		mainPane.getChildren().addAll(firePicRyu, EarthPicRyu, WaterPicRyu);
	}

	protected void appearNextBallKen(int r) {
		switch (r) {
		case 0: {
			firePicKen.relocate((double) (50), (double) (500));
			EarthPicKen.relocate((double) (-200), (double) (500));
			WaterPicKen.relocate((double) (-200), (double) (500));
			break;
		}
		case 1: {
			EarthPicKen.relocate((double) (50), (double) (500));
			firePicKen.relocate((double) (-200), (double) (500));
			WaterPicKen.relocate((double) (-200), (double) (500));
			break;
		}
		case 2: {
			WaterPicKen.relocate((double) (50), (double) (500));
			EarthPicKen.relocate((double) (-200), (double) (500));
			firePicKen.relocate((double) (-200), (double) (500));
			break;
		}

		}
	}

	protected void appearNextBallRyu(int r) {
		switch (r) {
		case 0: {
			firePicRyu.relocate((double) (900), (double) (500));
			EarthPicRyu.relocate((double) (-200), (double) (500));
			WaterPicRyu.relocate((double) (-200), (double) (500));
			break;
		}
		case 1: {
			EarthPicRyu.relocate((double) (900), (double) (500));
			firePicRyu.relocate((double) (-200), (double) (500));
			WaterPicRyu.relocate((double) (-200), (double) (500));
			break;
		}
		case 2: {
			WaterPicRyu.relocate((double) (900), (double) (500));
			EarthPicRyu.relocate((double) (-200), (double) (500));
			firePicRyu.relocate((double) (-200), (double) (500));
			break;
		}

		}
	}

	public static void drawBall(PowerBall ball) {
		ImageView im = (ball).getImageView();
		mainPane.getChildren().remove(im);
		if (ball.getPlayerSide() < 0) { // Ryu attack
			if (ball.getX() == 0 && !ball.isAttack()) {
				int damage = (int) (ball.getCount() * 0.75);
				if (!isKenShielded) {
					setKenHp(getKenHp() - damage);
					setKenHpText(getKenHp());
				} else {
					setKenShielded(false);
					setKenShieldText(false);
				}
				if (getKenHp() <= 0)
					setKenDie(true);
				if (isKenDie() && playSound) { // End Game
					sceneSound.stop();
					Thread.currentThread().interrupt();
					getRyuEndingScene().playSound();
					switchScenes(getRyuEndingScene().getMainScene());
					playSound = false;
				}
				ball.setAttack(true);
			}
		} else if (ball.getPlayerSide() > 0) { // Ken attack
			if (ball.getX() == 1080 && !ball.isAttack()) {
				int damage = (int) (ball.getCount() * 0.75);
				if (!isRyuShielded) {
					setRyuHp(getRyuHp() - damage);
					setRyuHpText(getRyuHp());
				} else {
					setRyuShielded(false);
					setRyuShieldText(false);
				}
				if (getRyuHp() <= 0)
					setRyuDie(true);
				if (isRyuDie() && playSound) { // End Game
					sceneSound.stop();
					Thread.currentThread().interrupt();
					getKenEndingScene().playSound();
					switchScenes(getKenEndingScene().getMainScene());
					playSound = false;
				}
				ball.setAttack(true);
			}
		}
		im.relocate((double) (ball.getX()), (double) ball.getY());
		mainPane.getChildren().add(im);
	}

	private static void switchScenes(Scene scene) {
		StartSceneController.getMainStage().setScene(scene);
	}

	public void playSound() {
		getSceneSound().setCycleCount(100);
		getSceneSound().setVolume(10);
		getSceneSound().play();
	}

	public void createKenShieldBar() {
		kenShielded = new ShieldBar("not shield");
		kenShielded.relocate(20, 35);
		mainPane.getChildren().add(kenShielded);
	}

	public void createRyuShieldBar() {
		ryuShielded = new ShieldBar("not shield");
		ryuShielded.relocate(570, 35);
		mainPane.getChildren().add(ryuShielded);
	}

	public static void createKenHpBar(int khp) {
		kenHpBar = new HpBar(Integer.toString(khp) + " hp");
		kenHpBar.relocate(240, 10);
		mainPane.getChildren().add(kenHpBar);
	}

	public static void createRynHpBar(int rhp) {
		ryuHpBar = new HpBar(Integer.toString(rhp) + " hp");
		ryuHpBar.relocate(780, 10);
		mainPane.getChildren().add(ryuHpBar);
	}

	public static void setKenHpText(int khp) {
		kenHpBar.setText(Integer.toString(khp) + " hp");
	}

	public static void setRyuHpText(int rhp) {
		ryuHpBar.setText(Integer.toString(rhp) + " hp");
	}

	public static void setKenShieldText(boolean kshield) {
		if (kshield)
			kenShielded.setText("shielded !");
		else
			kenShielded.setText("not shield");
	}

	public static void setRyuShieldText(boolean rshield) {
		if (rshield)
			ryuShielded.setText("shielded !");
		else
			ryuShielded.setText("not shield");
	}

	public static void updateCount(int count1, int count2) {
		txtCount1.setText("Power " + Integer.toString(count1));
		txtCount2.setText("Power " + Integer.toString(count2));

	}

	public void setClickedCountedFont() {
		txtCount1.relocate(20, 10);
		txtCount2.relocate(570, 10);
		mainPane.getChildren().addAll(txtCount1, txtCount2);
	}

	public int randomBall() { // 0=fireBall,1=earthBall,2=waterBall;
		Random rand = new Random();
		int r = rand.nextInt(3);
		return r;
	}

	/*
	 * ================================================ Getter & Setter
	 * ================================================
	 */

	public static Image getBackground() {
		return BACKGROUND;
	}

	public static Image getKen() {
		return KEN;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		GameSceneController.mainStage = mainStage;
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

	public int getKenPosX() {
		return kenPosX;
	}

	public void setKenPosX(int kenPosX) {
		this.kenPosX = kenPosX;
	}

	public static int getKenPosY() {
		return kenPosY;
	}

	public void setKenPosY(int kenPosY) {
		GameSceneController.kenPosY = kenPosY;
	}

	public int getRyuPosX() {
		return ryuPosX;
	}

	public void setRyuPosX(int ryuPosX) {
		this.ryuPosX = ryuPosX;
	}

	public static int getRyuPosY() {
		return ryuPosY;
	}

	public void setRyuPosY(int ryuPosY) {
		GameSceneController.ryuPosY = ryuPosY;
	}

	public static int getCountPlayer1() {
		return countPlayer1;
	}

	public static void setCountPlayer1(int countPlayer1) {
		GameSceneController.countPlayer1 = countPlayer1;
	}

	public static int getCountPlayer2() {
		return countPlayer2;
	}

	public static void setCountPlayer2(int countPlayer2) {
		GameSceneController.countPlayer2 = countPlayer2;
	}

	public static int getKenHp() {
		return kenHp;
	}

	public static void setKenHp(int kenHp) {
		GameSceneController.kenHp = (kenHp < 0 || kenHp > 100) ? (kenHp < 0 ? 0 : 100) : kenHp;
	}

	public static int getRyuHp() {
		return ryuHp;
	}

	public static void setRyuHp(int ryuHp) {
		GameSceneController.ryuHp = (ryuHp < 0 || ryuHp > 100) ? (ryuHp < 0 ? 0 : 100) : ryuHp;
	}

	public static boolean isKenShielded() {
		return isKenShielded;
	}

	public static void setKenShielded(boolean isKenShielded) {
		GameSceneController.isKenShielded = isKenShielded;
	}

	public static boolean isRyuShielded() {
		return isRyuShielded;
	}

	public static void setRyuShielded(boolean isRyuShielded) {
		GameSceneController.isRyuShielded = isRyuShielded;
	}

	public ThreadMain getThreadMain() {
		return threadMain;
	}

	public static boolean isKenDie() {
		return isKenDie;
	}

	public static void setKenDie(boolean iskendie) {
		isKenDie = iskendie;
	}

	public static boolean isRyuDie() {
		return isRyuDie;
	}

	public static void setRyuDie(boolean isryudie) {
		isRyuDie = isryudie;
	}

	public static RyuEndingSceneController getRyuEndingScene() {
		return ryuEndingScene;
	}

	public void setRyuEndingScene(RyuEndingSceneController ryuEndingScene) {
		GameSceneController.ryuEndingScene = ryuEndingScene;
	}

	public static KenEndingSceneController getKenEndingScene() {
		return kenEndingScene;
	}

	public void setKenEndingScene(KenEndingSceneController kenEndingScene) {
		GameSceneController.kenEndingScene = kenEndingScene;
	}

	public static Item getMainItem() {
		return mainItem;
	}

	public static void setMainItem(Item mainItem) {
		GameSceneController.mainItem = mainItem;
	}

	public static AnchorPane getMainPane() {
		return mainPane;
	}

	public static void setMainPane(AnchorPane mainPane) {
		GameSceneController.mainPane = mainPane;
	}

	public static AudioClip getSceneSound() {
		return sceneSound;
	}

	public void setSceneSound(AudioClip sceneSound) {
		GameSceneController.sceneSound = sceneSound;
	}

}
