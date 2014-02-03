package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class PingPongState extends State{
	private Image paddleImage = new Image(R.drawable.paddle);
	private Image ballImage = new Image(R.drawable.ball);
	private Image borderImage = new Image(R.drawable.border);
	private Sprite paddleUp;
	private Sprite paddleDown;
	private Sprite ball;
	private Sprite border;
	private Font pointsUpText = new Font(255, 255, 255, 80.0f , Typeface.SANS_SERIF, Typeface.BOLD); 
	private Font pointsDownText = new Font(255, 255, 255, 80.0f , Typeface.SANS_SERIF, Typeface.BOLD); 
	int pointsUp; 
	int pointsDown; 
	int speedIncrease = 30;
	private static PingPongState instance = null; 

	private PingPongState(){
		paddleUp = new Sprite(paddleImage);
		paddleDown = new Sprite(paddleImage);
		ball = new Sprite(ballImage);
		border = new Sprite(borderImage);

		ball.setPosition(232,364);
		border.setPosition(232,364); 
		paddleUp.setPosition(232,16); 
		paddleDown.setPosition(232,728); 

		paddleUp.setSpeed(80,0); 
		paddleDown.setSpeed(80,0); 
		ball.setSpeed(100,100); 
		
		pointsUp = 0; 
		pointsDown = 0; 
		
	}
	
	public static PingPongState getInstance(){
		if (instance == null){
			instance = new PingPongState(); 
		}
		return instance; 
	}

	@Override
	public void draw(Canvas canvas){
		canvas.drawColor(Color.CYAN);
		border.draw(canvas); 
		paddleUp.draw(canvas); 
		paddleDown.draw(canvas); 
		ball.draw(canvas);
		canvas.drawText("" + pointsUp, 15, 310 , pointsUpText); 
		canvas.drawText("" + pointsDown, 15, 460 , pointsDownText); 
	}

	@Override
	public void update(float dt){
		if (ball.collides(paddleUp)){
			WhenCollides(ball, paddleUp); 
		}
		// forsvinner opp
		else if (ball.getY() <= 8){
			ball.setPosition(232, 364); 
			UpdateDownScore(); 
		}
		if (ball.collides(paddleDown)){
			WhenCollides(ball, paddleDown); 
		}
		// forsviner ned
		else if(ball.getY() >= 736){
			ball.setPosition(232, 364);
			UpdateUpScore(); 
		}

		
		// grense høyre side
		if (paddleUp.getX() <= 60){
			paddleUp.setSpeed(-paddleUp.getSpeed().getX(), 0); 
		}
		if (paddleDown.getX() <= 60){
			paddleDown.setSpeed(-paddleDown.getSpeed().getX(), 0); 
		}
		
		if (ball.getX() <= 18){
			ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY()); 
		}
		// grense venstre side
		if (paddleUp.getX() > 404){
			paddleUp.setSpeed(-paddleUp.getSpeed().getX(), 0);
		}
		
		if (paddleDown.getX() > 404){
			paddleDown.setSpeed(-paddleDown.getSpeed().getX(), 0);
		}
		if (ball.getX() > 462){
			ball.setSpeed(-ball.getSpeed().getX(), ball.getSpeed().getY()); 
		}


		ball.update(dt);
		paddleDown.update(dt); 
		paddleUp.update(dt); 
		border.update(dt); 	
	}
	public void WhenCollides(Sprite ball, Sprite paddle){
		ball.setSpeed(paddle.getSpeed().getX()/Math.abs(paddle.getSpeed().getX())*(ball.getSpeed().getX()+speedIncrease), -ball.getSpeed().getY()-speedIncrease); 
	}

	@Override
	public boolean onTouchDown(MotionEvent touch){
		float x = touch.getX();
		float y = touch.getY(); 
		
		// trykker til høyre oppe
		if(x > 232 && y < 364){
			if(paddleUp.getSpeed().getX() > 0){
			paddleUp.setSpeed(paddleUp.getSpeed().getX(), 0); 
			}
			else{
				paddleUp.setSpeed(-paddleUp.getSpeed().getX(), 0); 
			}
		}
		// trykker til venstre oppe
		else if (x < 232 && y < 364){
			if(paddleUp.getSpeed().getX() > 0 ){
				paddleUp.setSpeed(-paddleUp.getSpeed().getX(), 0);
			}
			else{
				paddleUp.setSpeed(paddleUp.getSpeed().getX(), 0); 
			}
		}
		// trykker til høyre nede
		if(x > 232 && y > 364){
			if(paddleDown.getSpeed().getX() > 0){
			paddleDown.setSpeed(paddleDown.getSpeed().getX(), 0); 
			}
			else{
				paddleDown.setSpeed(-paddleDown.getSpeed().getX(), 0); 
			}
		}
		// trykker til venstre nede
		else if (x < 232 && y > 364){
			if(paddleDown.getSpeed().getX() > 0 ){
				paddleDown.setSpeed(-paddleDown.getSpeed().getX(), 0);
			}
			else{
				paddleDown.setSpeed(paddleDown.getSpeed().getX(), 0); 
			}
		}
		return true; 
	}
	
	public void UpdateUpScore(){
		 pointsUp += 1; 
	}
	
	public void UpdateDownScore(){
		pointsDown += 1; 
	}
}