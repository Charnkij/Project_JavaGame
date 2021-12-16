package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FireBall extends PowerBall{
	private static final Image FIREBALL = new Image("/entity/res/fireBall.png");
	private double Height;
	private double Width;
	public FireBall(int x, int highLevel, int playerSide) {
		super(x, highLevel, playerSide);
		imageView = new ImageView(FIREBALL);
		Height = imageView.prefHeight(1);
		Width = imageView.prefWidth(1);
	}

	@Override
	public void createFirstPowerBall(int count) {
		System.out.println(count);
		// TODO Auto-generated method stub
		double size = 0.05+0.004*count;
		imageView.setFitHeight(size*Height);
		imageView.setFitWidth(size*Width);
	}

	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
//		imageView = new ImageView(fireBall);
		return imageView;
	}

	public static Image getFireballl() {
		return FIREBALL;
	}

	@Override
	public PowerBallType getElement() {
		// TODO Auto-generated method stub
		return PowerBallType.FIRE;
	}
	

}
