package com.deng.schultegrid;

import com.deng.schultegrid.view.SelectButton;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;

public class SelectActivity extends Activity {

	GridLayout selectGrid;
	int viewLength;
	int btnWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		DisplayMetrics dm = new DisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		if (dm.widthPixels < dm.heightPixels)
			viewLength = dm.widthPixels;
		else
			viewLength = dm.heightPixels;
		btnWidth = viewLength / 3;
		selectGrid = (GridLayout) findViewById(R.id.selectGrid);
		selectGrid.setColumnCount(3);
		selectGrid.setRowCount(3);
		selectGrid.addView(new SelectButton(this, 2, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 3, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 4, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 5, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 0, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 6, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 7, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 8, btnWidth), btnWidth,
				btnWidth);
		selectGrid.addView(new SelectButton(this, 9, btnWidth), btnWidth,
				btnWidth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.select, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
