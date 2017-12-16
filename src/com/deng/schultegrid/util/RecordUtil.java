package com.deng.schultegrid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class RecordUtil {

	private Context context;

	public RecordUtil(Context context) {
		this.context = context;
	}

	public boolean writeRecord(int index, long record) {
		SharedPreferences records = context.getSharedPreferences("record",
				Context.MODE_PRIVATE);
		Editor editor = records.edit();
		editor.putLong("record" + index, record);
		return editor.commit();
	}

	public long readRecord(int index) {
		SharedPreferences records = context.getSharedPreferences("record",
				Context.MODE_PRIVATE);
		return records.getLong("record" + index, Long.MAX_VALUE);
	}

}
