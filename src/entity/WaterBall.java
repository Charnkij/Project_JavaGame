package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WaterBall extends PowerBall{
	private static final Image WATERBALL = new Image("/entity/res/waterBall.gif");
	private double Height;
	private double Width;
	public WaterBall(int x, int highLevel, int playerSide) {
		super(x, highLevel, playerSide);
		imageView = new ImageView(WATERBALL);
		Height = imageView.prefHeight(1);
		Width = imageView.prefWidth(1);
	}

	@Override
	public void createFirstPowerBall(int count) {
		// TODO Auto-generated method stub
		System.out.println(count);
		double size = 0.13+0.006*count;
		imageView.setFitHeight(size*Height);
		imageView.setFitWidth(size*Width);
	}

	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
//		imageView = new ImageView(waterBall);
		return imageView;
	}

	public static Image getWaterballl() {
		return WATERBALL;
	}

	@Override
	public PowerBallType getElement() {
		// TODO Auto-generated method stub
		return PowerBallType.WATER;
	}
	

}
