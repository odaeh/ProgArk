package com.example.spritesheep;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

public class PingPongView extends State {
	private Sprite paddleUp;
	private Sprite paddleDown;
	private Sprite ball;
	private Sprite border;
	private Font pointsUpText = new Font(255, 255, 255, 80.0f , Typeface.SANS_SERIF, Typeface.BOLD); 
	private Font pointsDownText = new Font(255, 255, 255, 80.0f , Typeface.SANS_SERIF, Typeface.BOLD); 
	int pointsUp; 
	int pointsDown; 
	
	// CONSTRUCTOR
	public PingPongView(){
		paddleUp = new PaddleModel();
		paddleDown = new PaddleModel();
		ball = new BallModel();
		border = new BorderModel();

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
		
		
		ball.update(dt);
		paddleDown.update(dt); 
		paddleUp.update(dt); 
		border.update(dt); 	
		
	}
	
	public void WhenCollides(Sprite ball, Sprite paddle){
		ball.setSpeed(paddle.getSpeed().getX(), -ball.getSpeed().getY()); 
	}
	
	public void UpdateUpScore(){
		 pointsUp += 1; 
	}
	
	public void UpdateDownScore(){
		pointsDown += 1; 
	}
	@Override
	public boolean onTouchDown(MotionEvent touch){
		float x = touch.getX();
		float y = touch.getY(); 
		
		// trykker til høyre oppe
		if(x > 232 && y < 364){
			((PaddleModel) paddleUp).goRightUp(); 
		}
		
		// trykker til venstre oppe
		else if (x < 232 && y < 364){
			((PaddleModel) paddleUp).goLeftUp(); 
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
}
