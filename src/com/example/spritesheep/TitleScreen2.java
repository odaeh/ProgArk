package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.*; 
import sheep.input.TouchListener;
import android.graphics.Color;
import android.view.MotionEvent;

public class TitleScreen2 extends State implements TouchListener{

	private Image heliImage = new Image(R.drawable.heli); 
	private Sprite heliSprite; 
	private Font text = Font.BLUE_SANS_BOLD_20; 

	// Lages ved oppstart av program
	public TitleScreen2() {
		heliSprite = new Sprite(heliImage); 
		heliSprite.setPosition(130, 40); 
		heliSprite.setSpeed(80, 80);
	}
	
	// plasserer spriten på "spillebrettet"
	@Override
	public void draw(android.graphics.Canvas canvas){
		canvas.drawColor(Color.MAGENTA); 
		heliSprite.draw(canvas); 
		canvas.drawText("Koordinater: " + (int)heliSprite.getX() + " , " + (int)heliSprite.getY(), 40,40, text); 
	}

	@Override
	public void update(float dt){
		// grense høyre side
		if (heliSprite.getX() >= 390){
			heliSprite.setSpeed(-heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY()); 
			heliSprite.setScale(-1,1);
			heliSprite.setOffset(-(heliImage.getWidth()), 0); // legg til / trekk fra på første koordinat for å snu innenfor ramme
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
	
	// trykker på skjermen
	@Override
	public boolean onTouchDown(MotionEvent touch){
		float y = touch.getY(); 

		// trykker over
		if(heliSprite.getY() > y){
			if(heliSprite.getSpeed().getY() > 0 ){
				heliSprite.setSpeed(heliSprite.getSpeed().getX(),-heliSprite.getSpeed().getY());
			}
			else{
				heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY()); 
			}
		}
		
		// trykker under
		else if(heliSprite.getY() < y){
			if(heliSprite.getSpeed().getY() > 0 ){
				heliSprite.setSpeed(heliSprite.getSpeed().getX(), heliSprite.getSpeed().getY());
			}
			else{
				heliSprite.setSpeed(heliSprite.getSpeed().getX(), -heliSprite.getSpeed().getY()); 
			}
		}
		return true; 
	}
}