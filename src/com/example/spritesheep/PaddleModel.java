package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class PaddleModel extends Sprite{
	private static Image paddleImage = new Image(R.drawable.paddle);

	public PaddleModel(){
		super(paddleImage); 
	}

	@Override
	public void update(float dt){
		// grense høyre side
		if (this.getX() <= 60){
			this.setSpeed(-this.getSpeed().getX(), 0); 
		}
		// grense venstre side
		if (this.getX() > 404){
			this.setSpeed(-this.getSpeed().getX(), 0);
		}

		this.update(dt);
	}
	
	public void goRightUp(){
		if(this.getSpeed().getX() > 0){
			this.setSpeed(this.getSpeed().getX(), 0); 
			}
			else{
				this.setSpeed(-this.getSpeed().getX(), 0); 
			}
	}
	public void goLeftUp(){
		if(this.getSpeed().getX() > 0 ){
			this.setSpeed(-this.getSpeed().getX(), 0);
		}
		else{
			this.setSpeed(this.getSpeed().getX(), 0); 
		}
	}
}