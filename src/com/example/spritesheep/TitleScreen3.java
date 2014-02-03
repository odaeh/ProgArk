package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.*; 
import android.graphics.Color;

public class TitleScreen3 extends State {

	private Image image1 = new Image(R.drawable.heli); 
	private Image baloon = new Image(R.drawable.luftballong); 
	private Sprite animationSprite; 
	private Sprite baloonSprite;
	private Image [] imageList = {new Image(R.drawable.animation1), new Image(R.drawable.animation2), new Image(R.drawable.animation3), new Image(R.drawable.animation4)};  
	private int frameCount;
	private int currentFrame;
	private float tickTime;
	private float timeLeft;
	private boolean isAnimated = true;

	// Lages ved oppstart av program
	public TitleScreen3() {
		animationSprite = new Sprite(image1);
		baloonSprite = new Sprite(baloon); 
		animationSprite.setPosition(90, 40);
		baloonSprite.setPosition(390, 580);
		animationSprite.setScale(-1,1); 
		animationSprite.setSpeed(60, 60);
		baloonSprite.setSpeed(80,80); 
		animationSprite.setShape(109,52); // virker ikke som om disse funker? 
		baloonSprite.setShape(60,109); 
		tickTime = 0.1f;
		frameCount = imageList.length;
		timeLeft = 0f;
	}

	// plasserer spriten på spillebrettet
	@Override
	public void draw(android.graphics.Canvas canvas){
		canvas.drawColor(Color.MAGENTA); 
		animationSprite.draw(canvas);
		baloonSprite.draw(canvas); 
	}

	@Override
	public void update(float dt){

		// grense høyre side
		if (animationSprite.getX() >= 390){
			animationSprite.setSpeed(-animationSprite.getSpeed().getX(), animationSprite.getSpeed().getY()); 
			animationSprite.setScale(1,1);
			animationSprite.setOffset(-image1.getWidth()/2, 0); // her
		}
		if (baloonSprite.getX() >= 390){
			baloonSprite.setSpeed(-baloonSprite.getSpeed().getX(), baloonSprite.getSpeed().getY()); 
			baloonSprite.setScale(-1,1);
			baloonSprite.setOffset(-baloon.getWidth(), 0);
		}

		
		// grense venstre side
		else if (animationSprite.getX() < 90){
			animationSprite.setSpeed(-animationSprite.getSpeed().getX(), animationSprite.getSpeed().getY());
			animationSprite.setScale(-1,1);
			animationSprite.setOffset(image1.getWidth()/2, 0);
		}
		else if (baloonSprite.getX() < 90){
			baloonSprite.setSpeed(-baloonSprite.getSpeed().getX(), baloonSprite.getSpeed().getY());
			baloonSprite.setScale(1,1);
			baloonSprite.setOffset(baloon.getWidth(), 0);
		}

		
		// grense oppe
		else if (animationSprite.getY() < 0){
			animationSprite.setSpeed(animationSprite.getSpeed().getX(), -animationSprite.getSpeed().getY());
			animationSprite.setOffset(image1.getHeight(),0);
		}
		else if (baloonSprite.getY() < 0){
			baloonSprite.setSpeed(baloonSprite.getSpeed().getX(), -baloonSprite.getSpeed().getY());
			baloonSprite.setOffset(baloon.getHeight()/2,0);
		}

		
		// grense nede
		else if (animationSprite.getY() >= 680){
			animationSprite.setSpeed(animationSprite.getSpeed().getX(), -animationSprite.getSpeed().getY()); 
			animationSprite.setOffset(-image1.getHeight(), 0);
		}
		else if (baloonSprite.getY() >= 680){
			baloonSprite.setSpeed(baloonSprite.getSpeed().getX(), -baloonSprite.getSpeed().getY()); 
			baloonSprite.setOffset(-baloon.getHeight()/2, 0);
		}

		
		// kollisjon
		else if(animationSprite.collides(baloonSprite)){
			animationSprite.setSpeed(- animationSprite.getSpeed().getX(), animationSprite.getSpeed().getY()); 
			baloonSprite.setSpeed(- animationSprite.getSpeed().getX(), -animationSprite.getSpeed().getY()); 
			animationSprite.setScale(-1,1); 
			animationSprite.setOffset(image1.getWidth(), 0); 
			baloonSprite.setOffset(baloon.getWidth(), 0); 
		}
		
		// animasjon
		if (isAnimated) {
			timeLeft += dt;
		}

		if(timeLeft >= tickTime){
			currentFrame = (currentFrame + 1) % frameCount;
			animationSprite.setView((SpriteView)imageList[currentFrame]);
			timeLeft -= tickTime;
		}
		animationSprite.update(dt); 
		baloonSprite.update(dt); 
	}
}







