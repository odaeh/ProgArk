package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class BallModel extends Sprite {
	private static Image ballImage= new Image(R.drawable.ball);

	public BallModel(){
		super(ballImage);

	}

	@Override
	public void update(float dt){
		// grense høyre side
		if (this.getX() <= 18){
			this.setSpeed(-this.getSpeed().getX(), this.getSpeed().getY()); 
		}
		// grense venstre side
		if (this.getX() > 462){
			this.setSpeed(-this.getSpeed().getX(), this.getSpeed().getY()); 
		}
		this.update(dt);
	}

}
