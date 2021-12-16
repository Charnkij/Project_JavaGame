package entity;

import entity.base.Interactable;
import javafx.scene.media.AudioClip;
import scene.controller.GameSceneController;

public class DmPotion extends Item implements Interactable {
	
	private AudioClip sound = new AudioClip(ClassLoader.getSystemResource("component/res/DamageUp.wav").toString());

	public DmPotion() {
		// TODO Auto-generated constructor stub
		super("dmPotion", 2);
	}

	@Override
	public boolean interact(char side) {
		// TODO Auto-generated method stub
		if(side == 'l') {
			GameSceneController.setCountPlayer1(GameSceneController.getCountPlayer1()+10);
			GameSceneController.updateCount(GameSceneController.getCountPlayer1(), GameSceneController.getCountPlayer2());
			sound.play();
			return true;
		}else  if(side == 'r') {
			GameSceneController.setCountPlayer2(GameSceneController.getCountPlayer2()+10);
			GameSceneController.updateCount(GameSceneController.getCountPlayer1(), GameSceneController.getCountPlayer2());
			sound.play();
			return true;
		}
		
		return false;
	}

}
