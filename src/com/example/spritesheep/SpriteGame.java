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
        
       // Kommenter ut den staten som skal kjøres
       //game.pushState(new TitleScreen()); // TASK 1
       //game.pushState(new TitleScreen2()); // TASK 2
      // game.pushState(new TitleScreen3()); // TASK 3
      // game.pushState(new PingPongState()); // TASK 4
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
