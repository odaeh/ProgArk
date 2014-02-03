package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.*; 
import android.graphics.Color;

public class TitleScreen extends State {

	private Image heliImage = new Image(R.drawable.heli); 
	private Sprite heliSprite; 

	// Lages ved oppstart av program
	public TitleScreen() {
		heliSprite = new Sprite(heliImage); 
		heliSprite.setPosition(130, 40); 
		heliSprite.setSpeed(60, 60); 
	}

	// plasserer spriten på spillebrettet
	@Override
	public void draw(android.graphics.Canvas canvas){
		canvas.drawColor(Color.MAGENTA); 
		heliSprite.draw(canvas); 
		}

	@Override
	// grense høyre side
	public void update(float dt){
		if (heliSprite.getX() >= 390){
			heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY()); 
			heliSprite.setScale(-1,1);
			heliSprite.setOffset(-(heliImage.getWidth()), 0);
		}
		// grense venstre side
		else if (heliSprite.getX() < 90){
			heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
			heliSprite.setScale(1,1);
			heliSprite.setOffset((heliImage.getWidth()),0);
		}
		// grense oppe
		else if (heliSprite.getY() < 0){
			heliSprite.setSpeed(heliSprite.getSpeed().getX(), -heliSprite.getSpeed().getY());
			heliSprite.setOffset((heliImage.getHeight()),0);
		}
		// grense nede
		else if (heliSprite.getY() >= 680){
				heliSprite.setSpeed(heliSprite.getSpeed().getX(), -heliSprite.getSpeed().getY()); 
				heliSprite.setOffset(-(heliImage.getHeight()), 0);
		}
		
		heliSprite.update(dt); 
	}

}

