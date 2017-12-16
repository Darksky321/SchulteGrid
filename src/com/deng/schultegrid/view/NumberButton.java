package com.deng.schultegrid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;

public class NumberButton extends Button {

	private int number = 1;
	private int width = 0;

	public NumberButton(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	public NumberButton(Context context, int width, int number) {
		super(context);
		this.width = width;
		this.setTextSize(TypedValue.COMPLEX_UNIT_PX, width * 0.35f);
		this.setNumber(number);
		this.setBackgroundColor(Color.WHITE);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		this.setText(number + "");
		this.setGravity(Gravity.CENTER);
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

}
