package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EarthBall extends PowerBall {
	private static final Image EARTHBALL = new Image("/entity/res/earthBall.gif");
	private double Height;
	private double Width;
	public EarthBall(int x, int highLevel, int playerSide) {
		super(x, highLevel, playerSide);
		imageView = new ImageView(EARTHBALL);
		Height = imageView.prefHeight(1);
		Width = imageView.prefWidth(1);
	}

	@Override
	public void createFirstPowerBall(int count) {
		// TODO Auto-generated method stub
		System.out.println(count);
		double size = 0.06+0.004*count;
		imageView.setFitHeight(size*Height);
		imageView.setFitWidth(size*Width);
	}

	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return imageView;
	}

	public static Image getEarthball() {
		return EARTHBALL;
	}

	@Override
	public PowerBallType getElement() {
		// TODO Auto-generated method stub
		return PowerBallType.EARTH;
	}


}
