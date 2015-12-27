package com.habib.articulationpointbridge;

import android.app.Activity;
import android.os.Bundle;


public class SubActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DrawGrap drawGrap = new DrawGrap(this);
		setContentView(drawGrap);
	}
}
