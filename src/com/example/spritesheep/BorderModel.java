package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class BorderModel extends Sprite {
	private static Image borderImage = new Image(R.drawable.border);

	public BorderModel(){
		super(borderImage); 
	}
	
}
