package com.habib.articulationpointbridge;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity{
	
	private static final int WHITE = 0;
	private static final int GRAY = 1;
	private static final int BLACK = 2;
	public static ArrayList<ArrayList<Integer>> grap;
	public static int gSize;
	private int[] dis;
	private int t,root,rootChild;
	private int[] color;
	private int[] low;
	private boolean[][] bridge;
	private boolean[] art;
	Context context;
	private int[] par;
	private DrawGrapView drawGrapView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		context = MainActivity.this;
		drawGrapView = new DrawGrapView(this);
		setContentView(drawGrapView);
	}
	private int min(int x, int y){
		if (x > y) {
			return y;
		}
		else {
			return x;
		}
	}
	public void articulationPointAndBridge(int u){
		
		
	    color[u] = GRAY;
	    dis[u] = low[u] = t++;
	    int sz = grap.get(u).size();
	    for(int i = 0; i < sz;i++){
	        
	    	int v = grap.get(u).get(i);
	        
	        if(color[v]==WHITE){
	            par[v] = u;
	            if(u == root) rootChild++;

	            articulationPointAndBridge(v);

	            if(low[v]>=dis[u] && u!=root)art[u] = true;
	            if(low[v]>dis[u])bridge[u][v] = bridge[v][u]=true;

	            low[u] = min(low[u],low[v]);
	        }
	        else if(par[u]!=v)
	            low[u] = min(dis[v],low[u]);

	    }
	    color[u] = BLACK;
	}
	public void dfs(int n){
		
	    t = 0;
		par = new int[gSize];
	    dis = new int[gSize];
	    low = new int[gSize];
	    art = new boolean[gSize];
	    color = new int[gSize];
	    bridge = new boolean[gSize][gSize];
	    for(int i = 0; i < n; i++)
	        for(int j = 0; j < n; j++)
	            bridge[i][j] = false;
	    for(int i = 0; i < n; i++){
	        if(color[i]==WHITE){
	            rootChild = 0;
	            root = i;
	            articulationPointAndBridge(i);
	            art[i] = (rootChild > 1);
	        }
	    }
	    setData();
	}
	private void setData(){
		DrawGrap.art = art;
		DrawGrap.bridge = bridge;

		Intent intent = new Intent(context, SubActivity.class);
		startActivity(intent);
	}
}

