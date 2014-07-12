package com.mti.ad220lab1;

import java.util.Random;

import android.view.View;

public class Circle {
	//positioning
	private float x;
	private float xSpeed; //must be signed
	private float y;
	private float ySpeed; //must be signed
	//appearance
	private final int COLOR_A;
	private final int COLOR_R;
	private final int COLOR_G;
	private final int COLOR_B;
	private final int RADIUS;
	Random spawner;
	
	//TODO, int radius, int speed
	
	public Circle(float x, float y, View db, int size) {
		spawner = new Random();
		
		//size
		if (db.getHeight() < db.getWidth()) {
			this.RADIUS = db.getHeight()/(11 - size)/10;
			//TODO + radius
		}//endif - (db.getHeight() < db.getWidth())
		else {
			this.RADIUS = db.getWidth()/(11 - size)/10;
			//TODO + radius
		}//endelse - (db.getHeight() < db.getWidth())
		
		//positioning
		this.x = x;
		this.y = y;
		
		//velocity
		final float SPEED = spawner.nextFloat() * RADIUS /10;
		//TODO speed + 
		xSpeed = SPEED - spawner.nextFloat() * SPEED * 2;
		ySpeed = SPEED - spawner.nextFloat() * SPEED * 2;
		
		//appearance
			//Max value is 255.
		COLOR_A = 255;
		COLOR_R = spawner.nextInt(255);
		COLOR_G = spawner.nextInt(255);
		COLOR_B = spawner.nextInt(255);
	}//constructor
	
	//movement
	public void move(View db) {
		int leftLimit = RADIUS;
		int rightLimit = db.getWidth() - RADIUS;
		int topLimit = RADIUS;
		int bottomLimit = db.getHeight() - RADIUS;
		
		if (x <= leftLimit || x >= rightLimit) {
			xSpeed = xSpeed * -1;
		}//endif - (x <= leftLimit || x >= rightLimit)
		if (y <= topLimit || y >= bottomLimit) {
			ySpeed = ySpeed * -1;
		}//endif - (y <= topLimit || y >= bottomLimit)
		
		x = x+xSpeed;
		y = y+ySpeed;
	}//move()

	public void accelerate(float shake) {
		switch (spawner.nextInt(2)) {
		case 0:
			xSpeed = xSpeed + shake/2;
			break;
		case 1:
			ySpeed = ySpeed + shake/2;
			break;
		case 2:
			xSpeed = xSpeed - shake/2;
			break;
		default:
			ySpeed = ySpeed - shake/2;
		}//endswitch - (spawner.nextInt(2))
	}//accelerate(float shake)
	
	//getters
	public float getX() {
		return x;
	}//getHorizontalLocation()
	
	public float getxSpeed() {
		return xSpeed;
	}//getHorizontalSpeed()
	
	public float getY() {
		return y;
	}//getVerticalLocation()
	
	public float getYSpeed() {
		return ySpeed;
	}//getVerticalSpeed()
	
	public int getCOLOR_A() {
		return COLOR_A;
	}//getCOLOR_A()
	
	public int getCOLOR_R() {
		return COLOR_R;
	}//getCOLOR_R()
	
	public int getCOLOR_G() {
		return COLOR_G;
	}//getCOLOR_G()
	
	public int getCOLOR_B() {
		return COLOR_B;
	}//getCOLOR_B()
	
	public int getRADIUS() {
		return RADIUS;
	}//getHEIGHT()
	
	//setters
	public void setX(float x) {
		this.x = x;
	}//setHorizontalLocation(int horizontalLocation)

	public void setXSpeed(int speed) {
		this.xSpeed = speed;
	}//setHorizontalSpeed(int horizontalSpeed)

	public void setY(float y) {
		this.y = y;
	}//setVerticalLocation(int verticalLocation)

	public void setYSpeed(int speed) {
		this.ySpeed = speed;
	}//setVerticalSpeed(int verticalSpeed)
	
}//class
