package com.deng.schultegrid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.deng.schultegrid.MainActivity;
import com.deng.schultegrid.RecordActivity;

public class SelectButton extends Button implements OnClickListener,
		OnTouchListener {

	private int index = 5;
	private int width;

	public SelectButton(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	public SelectButton(Context context, int index, int width) {
		super(context);
		this.index = index;
		this.width = width;
		this.setTextSize(width * 0.2f);
		this.setText(index + "×" + index);
		if (index == 0) {
			this.setText("rec");
		}
		this.setBackgroundColor(Color.WHITE);
		this.setOnClickListener(this);
		this.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO 自动生成的方法存根
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			v.setBackgroundColor(Color.GRAY);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			v.setBackgroundColor(Color.WHITE);
		}
		return false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存根
		super.onDraw(canvas);
		Paint paint = getPaint();
		paint.setColor(Color.BLACK);
		canvas.drawLine(0, 0, width, 0, paint);
		canvas.drawLine(0, 0, 0, width, paint);
		canvas.drawLine(width, width, 0, width, paint);
		canvas.drawLine(width, width, width, 0, paint);
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		if (index != 0) {
			Intent intent = new Intent(getContext(), MainActivity.class);
			intent.putExtra("index", index);
			getContext().startActivity(intent);
		} else {
			Intent intent = new Intent(getContext(), RecordActivity.class);
			getContext().startActivity(intent);
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
