package entity;

import entity.base.Interactable;
import javafx.scene.media.AudioClip;
import scene.controller.GameSceneController;

public class Shield extends Item implements Interactable {
	
	private AudioClip sound = new AudioClip(ClassLoader.getSystemResource("component/res/Shield.wav").toString());
	
	public Shield() {
		super("shield", 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean interact(char side) {
		// TODO Auto-generated method stub
		if(side == 'l') {
			GameSceneController.setKenShielded(true);
			GameSceneController.setKenShieldText(true);
			sound.play();
			return true;
		}else  if(side == 'r') {
			GameSceneController.setRyuShielded(true);
			GameSceneController.setRyuShieldText(true);
			sound.play();
			return true;
		}
		
		return false;
	}

}
