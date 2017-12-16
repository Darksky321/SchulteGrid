package com.deng.schultegrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		List<Map<String, String>> info = new ArrayList<Map<String, String>>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("key", "author");
		map1.put("value", "´ï¿Ë¡¤Ë¹¸Ç");
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("key", "contact");
		map2.put("value", "http://weibo.com/darksky321");
		info.add(map1);
		info.add(map2);
		ListView aboutList = (ListView) findViewById(R.id.aboutList);
		aboutList.setAdapter(new SimpleAdapter(this, info,
				R.layout.about_list_item, new String[] { "key", "value" },
				new int[] { android.R.id.text1, android.R.id.text2 }));
	}

}
