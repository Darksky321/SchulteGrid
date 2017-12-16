package com.deng.schultegrid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.deng.schultegrid.util.RecordUtil;
import com.deng.schultegrid.view.CommonButton;
import com.deng.schultegrid.view.NumberButton;

public class MainActivity extends Activity {

	private int index = 5;
	private Timer timer;
	private long startTime = 0;
	private long totalTime = 0;
	private TextView curNumText;
	private TextView countText;
	private GridLayout gridView;
	private CommonButton restartBtn;
	private int viewLength;
	private int buttonWidth;

	private int currentNum = 1;
	private int[] numSeries;

	private RecordUtil ru;
	private boolean completeFlag = false;

	private static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO 自动生成的方法存根
			super.handleMessage(msg);
		}

	};

	private OnClickListener numClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			NumberButton numBtnView = (NumberButton) v;
			if (numBtnView.getNumber() == currentNum) {
				addNum();
			}
		}

	};

	private OnTouchListener numTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO 自动生成的方法存根
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				NumberButton nb = (NumberButton) v;
				if (nb.getNumber() == currentNum)
					v.setBackgroundColor(Color.GRAY);
				else
					v.setBackgroundColor(Color.RED);
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				v.setBackgroundColor(Color.WHITE);
			}
			return false;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ru = new RecordUtil(this);
		try {
			getWindow().addFlags(
					WindowManager.LayoutParams.class.getField(
							"FLAG_NEEDS_MENU_KEY").getInt(null));
		} catch (Exception e) {

		}
		Intent intent = getIntent();
		index = intent.getIntExtra("index", 5);
		curNumText = (TextView) findViewById(R.id.curNumText);
		countText = (TextView) findViewById(R.id.countText);
		gridView = (GridLayout) findViewById(R.id.gridView);
		restartBtn = (CommonButton) findViewById(R.id.restartBotton);
		DisplayMetrics dm = new DisplayMetrics();
		Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(dm);
		if (dm.widthPixels < dm.heightPixels)
			viewLength = dm.widthPixels;
		else
			viewLength = dm.heightPixels;
		buttonWidth = viewLength / index;
		restartBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				resetGame();
			}

		});
		resetGame();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_restart) {
			resetGame();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO 自动生成的方法存根
		if (timer != null) {
			timer.cancel();
		}
		super.onDestroy();
	}

	/**
	 * 游戏计时显示
	 * 
	 * @author Deng
	 * 
	 */
	private class CountTimerTask extends TimerTask {

		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS",
				Locale.getDefault());

		@Override
		public void run() {
			handler.post(new Runnable() {
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					// double currentTime = System.currentTimeMillis() -
					// startTime;
					// currentTime = currentTime / 1000;
					// DecimalFormat df = new DecimalFormat("0.00");
					// countText.setText(df.format(currentTime));
					if (!completeFlag) {
						totalTime = System.currentTimeMillis() - startTime;
						Date currentTime = new Date(totalTime);
						countText.setText(sdf.format(currentTime));
					}
				}

			});
		}

	}

	/**
	 * 开始计时
	 */
	private void startTimer() {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer();
		startTime = System.currentTimeMillis();
		timer.schedule(new CountTimerTask(), 0, 10);
	}

	/**
	 * 结束计时
	 */
	private void stopTimer() {
		if (timer != null) {
			timer.cancel();
		}
	}

	/**
	 * 当前数字加一
	 */
	private synchronized void addNum() {
		// System.out.println("currentNum = " + currentNum);
		if (currentNum == index * index) {
			completeFlag = true;
			stopTimer();
			restartBtn.setVisibility(View.VISIBLE);
			long record = ru.readRecord(index);
			if (totalTime < record) {
				ru.writeRecord(index, totalTime);
				Toast.makeText(this, "New Record!", Toast.LENGTH_SHORT).show();
			}
		} else {
			currentNum = currentNum + 1;
			curNumText.setText("next:" + currentNum);
		}
	}

	/**
	 * 随机排列1~length个数
	 * 
	 * @param length
	 *            随机数列长度
	 * @return
	 */
	private int[] generateRandomSeries(int length) {
		int[] numSeries = new int[length];
		int m, n;
		int temp;
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < length; i++) {
			numSeries[i] = i + 1;
		}
		for (int i = 0; i < length * length; i++) {
			m = random.nextInt(length);
			n = random.nextInt(length);
			temp = numSeries[m];
			numSeries[m] = numSeries[n];
			numSeries[n] = temp;
		}
		return numSeries;
	}

	private void resetGame() {
		if (timer != null) {
			timer.cancel();
		}
		completeFlag = false;
		startTime = 0;
		currentNum = 1;
		curNumText.setText("next:1");
		countText.setText("0.00");
		restartBtn.setVisibility(View.INVISIBLE);
		numSeries = generateRandomSeries(index * index);
		gridView.removeAllViews();
		gridView.setColumnCount(index);
		gridView.setRowCount(index);
		int numCount = index * index;
		for (int i = 0; i < numCount; i++) {
			NumberButton numBtn = new NumberButton(this, buttonWidth,
					numSeries[i]);
			numBtn.setOnClickListener(numClickListener);
			numBtn.setOnTouchListener(numTouchListener);
			gridView.addView(numBtn, buttonWidth, buttonWidth);
		}
		// for (int i = 0; i < index; i++) {
		// LinearLayout subLayout = new LinearLayout(this);
		// subLayout.setOrientation(LinearLayout.HORIZONTAL);
		// for (int j = 0; j < index; j++) {
		// NumberButton numBtn = new NumberButton(this, buttonWidth,
		// numSeries[i * index + j]);
		// numBtn.setOnClickListener(numClickListener);
		// subLayout.addView(numBtn, buttonWidth, buttonWidth);
		// }
		// gridView.addView(subLayout);
		// }
		startTimer();
	}

}