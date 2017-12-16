package com.deng.schultegrid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class CommonButton extends Button implements OnTouchListener {

	public CommonButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundColor(Color.WHITE);
		this.setOnTouchListener(this);
		// TODO 自动生成的构造函数存根
	}

	public CommonButton(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
		this.setOnTouchListener(this);
		// TODO 自动生成的构造函数存根
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
		int width = getWidth();
		int height = getHeight();
		canvas.drawLine(0, 0, width, 0, paint);
		canvas.drawLine(0, 0, 0, height, paint);
		canvas.drawLine(width, height, 0, height, paint);
		canvas.drawLine(width, height, width, 0, paint);
	}

}
