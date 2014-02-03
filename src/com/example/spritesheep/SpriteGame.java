package com.example.spritesheep;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import sheep.game.Game; 

public class SpriteGame extends Activity {

	// FERDIG!!
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spite);
        
        // create game
        Game game = new Game(this, null); 
        
        game.pushState(PingPongState.getInstance()); 
        
        // view game
        setContentView(game); 
    }
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spite, menu);
        return true;
    }
    
}
