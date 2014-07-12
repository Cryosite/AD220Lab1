package com.mti.ad220lab1;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class DrawingBoard extends View {
	//private int h;
	//private int w;
	private Paint paint;
	private Handler h;
	private Runnable r;
	private final long FRAMERATE = 10L;
	private ArrayList<Circle> circles;
	
	public DrawingBoard(Context c) {
		super(c);
		paint = new Paint();
		circles = new ArrayList<>();
		h = new Handler();
		r = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}//run()
		};//Runnable()
	}//constructor
	
	public void addCircle(Circle circle) {
		circles.add(circle);
	}//addCircle(Circle circle)
	
	public int circleCount() {
		return circles.size();
	}//circleCount()
	
	public ArrayList<Circle> getCircles() {
		return circles;
	}//getCircles()
	
	@Override
	public void onDraw(Canvas board) {
		super.onDraw(board);
		if (!circles.isEmpty()) {
			for (Circle circle : circles) {
				paint.setARGB(circle.getCOLOR_A(),circle.getCOLOR_R(),circle.getCOLOR_G(),circle.getCOLOR_B());
				board.drawCircle(circle.getX(), circle.getY(), circle.getRADIUS(), paint);
				circle.move(this);
			}//endfor - (Circle circle : circles)
		}//endif - (!circles.isEmpty())
		
		h.postDelayed(r, FRAMERATE);
	}//onDraw(Canvas board)

}//class
