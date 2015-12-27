package com.habib.articulationpointbridge;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class DrawGrap extends View {

	public static ArrayList<Point> vertexPoint = new ArrayList<Point>();
	public static ArrayList<Point> edgeU = new ArrayList<Point>();
	public static ArrayList<Point> edgeV = new ArrayList<Point>();
	public static boolean[][] bridge;
	public static boolean[] art;
	private Paint textpaint = new Paint();
	Paint paint = new Paint();
	public DrawGrap(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint.setColor(Color.GREEN);
		textpaint.setColor(Color.BLACK);
		textpaint.setTextSize(45.0f);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		for(int i = 0; i < edgeU.size(); i++){
			Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			linePaint.setAntiAlias(true);
			linePaint.setStrokeWidth(6.0f);
			linePaint.setColor(Color.GREEN);
			
			int u = getVertexU(edgeU.get(i).x,edgeU.get(i).y);
			int v = getVertexV(edgeV.get(i).x,edgeV.get(i).y);
			
			if (bridge[u][v] || bridge[v][u]) {
				linePaint.setColor(Color.RED);
				canvas.drawLine(edgeU.get(i).x,edgeU.get(i).y,edgeV.get(i).x,edgeV.get(i).y, linePaint);
			}
			else {
				canvas.drawLine(edgeU.get(i).x,edgeU.get(i).y,edgeV.get(i).x,edgeV.get(i).y, linePaint);
			}
			
		}
		for(int i = 0; i < vertexPoint.size(); i++){
			int x = vertexPoint.get(i).x;
			int y = vertexPoint.get(i).y;
			if (art[i] == true) {
				paint.setColor(Color.RED);
				canvas.drawCircle(x, y, 40, paint);
				paint.setColor(Color.GREEN);
			}
			else {
				canvas.drawCircle(x, y, 40, paint);
			}
			canvas.drawText(""+ i, x - 15, y + 15,textpaint);
			
		}
	}
	private int getVertexU(int x, int y){
		for(int i = 0; i < vertexPoint.size();i++){
			if (vertexPoint.get(i).x == x && vertexPoint.get(i).y == y) {
				return i;
			}
		}
		return -1;
	}
	private int getVertexV(int x, int y){
		for(int i = 0; i < vertexPoint.size();i++){
			if (vertexPoint.get(i).x == x && vertexPoint.get(i).y == y) {
				return i;
			}
		}
		return -1;
	}

}
