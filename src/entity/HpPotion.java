package entity;

import entity.base.Interactable;
import javafx.scene.media.AudioClip;
import scene.controller.GameSceneController;

public class HpPotion extends Item implements Interactable {
	
	private AudioClip sound = new AudioClip(ClassLoader.getSystemResource("component/res/Shield.wav").toString());

	public HpPotion() {
		super("hpPotion", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean interact(char side) {
		// TODO Auto-generated method stub
		if(side == 'l') {
			GameSceneController.setKenHp(GameSceneController.getKenHp()+15);
			GameSceneController.setKenHpText(GameSceneController.getKenHp());
			sound.play();
			return true;
		}else  if(side == 'r') {
			GameSceneController.setRyuHp(GameSceneController.getRyuHp()+15);
			GameSceneController.setRyuHpText(GameSceneController.getRyuHp());
			sound.play();
			return true;
		}
		return false;
	}

}
