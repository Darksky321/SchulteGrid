package com.deng.schultegrid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.deng.schultegrid.util.RecordUtil;

public class RecordActivity extends Activity {

	private ListView recordList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		recordList = (ListView) findViewById(R.id.recordList);
		String[] recordData = new String[9];
		RecordUtil ru = new RecordUtil(this);
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS",
				Locale.getDefault());
		for (int i = 0; i < 8; i++) {
			int index = i + 2;
			Long time = ru.readRecord(index);
			if (time == Long.MAX_VALUE) {
				recordData[i] = index + "×" + index + ": " + "none";
			} else {
				Date date = new Date(time);
				recordData[i] = index + "×" + index + ": " + sdf.format(date);
			}
		}
		recordData[8] = "about";

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.record_list_item, recordData);
		recordList.setCacheColorHint(Color.TRANSPARENT);
		// recordList.setBackgroundColor(Color.TRANSPARENT);
		recordList.setAdapter(adapter);

		recordList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自动生成的方法存根
				if (position == 8) {
					startActivity(new Intent(RecordActivity.this,
							AboutActivity.class));
				}
			}

		});

	}
}
