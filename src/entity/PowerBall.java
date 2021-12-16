package entity;

import javafx.scene.image.ImageView;

public abstract class PowerBall {

	private int x, y, highLevel, speedX, playerSide, count;
	private boolean isInMap, isAttack;
	protected ImageView imageView;

	public PowerBall(int x, int highLevel, int playerSide) {
		setX(x);
		setY(highLevel);
		setSpeedX(playerSide);
		setPlayerSide(playerSide);
		setInMap(true);
		setAttack(false);
		// TODO Auto-generated constructor stub
	}

	abstract public void createFirstPowerBall(int count);
	abstract public ImageView getImageView();
	abstract public PowerBallType getElement();
	public void setImageView(ImageView iV) {
		imageView = iV;
	}

	public void update() {
//		System.out.println(this.x);
		if(this.x>-400 && this.x<1100)
			this.x +=speedX;
		else {
//			GameSceneController.removeFromPane(imageView);
			Thread.currentThread().interrupt();
			isInMap = false;
		}
	}

	public boolean isInMap() {
		return isInMap;
	}
	
	public void setInMap(boolean isInMap) {
		this.isInMap = isInMap;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public int getHighLevel() {
		return highLevel;
	}

	public int getSpeedX() {
		return speedX;
	}
	

	public void setHighLevel(int highLevel) {
		this.highLevel = highLevel;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setPlayerSide(int playerSide) {
		this.playerSide = playerSide;
	}

	public int getPlayerSide() {
		return playerSide;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isAttack() {
		return isAttack;
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	

	
	

}
