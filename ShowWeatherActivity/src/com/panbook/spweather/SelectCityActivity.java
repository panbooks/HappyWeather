/***************************************************************************
 *
 * Copyright (c) 2012 Baidu.com, Inc. All Rights Reserved
 *
 **************************************************************************/

/**   
* @Title: SelectCityActivity.java 
* @Package com.panbook.spweather 
* @Description: TODO 
* @author shupan@baidu.com
* @date 2012-12-5 下午12:03:54
* @version V1.0   
*/ 


package com.panbook.spweather;

import java.util.ArrayList;
import java.util.List;

import com.panbook.config.CommonConfig;
import com.panbook.db.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SelectCityActivity extends Activity {


	private Spinner provinceSpinner;
	private Spinner citySpinner;
	private Button OKBtn;
	
	private List<String> provinceList = new ArrayList<String>();
	private List<String> cityList = new ArrayList<String>();
	private String curProvince = "";
	private String curCity = "";
	private String curCityId = "";
	
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_city);
		
		initDB();
		
		provinceSpinner = (Spinner) findViewById(R.id.spinner_province);
		setProvinceAdapter();
		provinceSpinner.setOnItemSelectedListener(new SelectProvinceListener());
		
		citySpinner = (Spinner) findViewById(R.id.spinner_city);
		citySpinner.setOnItemSelectedListener(new SelectCityListener());
	
		OKBtn = (Button)findViewById(R.id.btn_select_city_ok);
		OKBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onSelectedFinished();
			}
		});
	}
	
	@Override
	public void finish() {
		db.close();
		super.finish();
	}

	
	/**
	 * 初始化该类的SQLiteDatabase对象
	 */
	private void initDB() {
		DatabaseHelper dbHelper = new DatabaseHelper(SelectCityActivity.this, CommonConfig.DBNAME);
		db = dbHelper.getReadableDatabase();
	}
	
	/**
	 * 为provinceSpinner设置adapter
	 */
	public void setProvinceAdapter() {
		getProvinceList();
		ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinceList);
		provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		provinceSpinner.setAdapter(provinceAdapter);
	}
	
	/**
	 * 为citySpinner设置adapter
	 */
	public void setCityAdapter() {
		getCityList();
		ArrayAdapter<String> cityAdapter =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
		cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		citySpinner.setAdapter(cityAdapter);
	}
	
	/**
	 * 获取省份列表
	 * @author shupan
	 *
	 */
	public void getProvinceList() {
		
/*		DatabaseHelper dbHelper = new DatabaseHelper(SelectCityActivity.this, CommonConfig.DBNAME);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
*/
		Cursor cursor = db.query("weather", new String[]{"distinct(province) p"}, null, null, null, null, null);
		String province;
		while(cursor.moveToNext()){
			province = cursor.getString(cursor.getColumnIndex("p"));
			System.out.println("province--->" + province);
			provinceList.add(province);
		}
		cursor.close();
//		db.close();
	}
	
	/**
	 * 获取当前省所有城市列表
	 * @param province
	 * @return
	 */
	public void getCityList() {

		cityList.clear();
/*		
		DatabaseHelper dbHelper = new DatabaseHelper(SelectCityActivity.this, CommonConfig.DBNAME);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
*/		
		Cursor cursor = db.query("weather", new String[]{"distinct(city) c"}, "province=?", new String[]{curProvince}, null, null, null);
		String city;
		while(cursor.moveToNext()){
			city = cursor.getString(cursor.getColumnIndex("c"));
			System.out.println("province--->" + city);
			cityList.add(city);
		}
		cursor.close();
//		db.close();
	}
	
	/**
	 * 根据城市名称获取城市编码
	 */
	public void getCityId() {
/*		
		DatabaseHelper dbHelper = new DatabaseHelper(SelectCityActivity.this, CommonConfig.DBNAME);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
*/
		Cursor cursor = db.query("weather", new String[]{"citycode"}, "city=?", new String[]{curCity}, null, null, null);
		while(cursor.moveToNext()){
			curCityId = cursor.getString(cursor.getColumnIndex("citycode"));
			System.out.println("cityId--->" + curCityId);
			break;
		}
		cursor.close();
//		db.close();
	}

	/**
	 * 选择城市完毕后的操作
	 */
	public void onSelectedFinished() {
		Intent intent = new Intent(SelectCityActivity.this, ShowWeatherActivity.class);
		intent.putExtra("cityId", curCityId);
//		startActivity(intent);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	/**
	 * 选择省份监听类
	 * @author shupan
	 *
	 */
	class SelectProvinceListener implements OnItemSelectedListener {

		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
		 */
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			curProvince = provinceList.get(position);
			setCityAdapter();
		}

		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
		 */
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	}
	
	/**
	 * 选择城市监听类
	 * @author shupan
	 *
	 */
	class SelectCityListener implements OnItemSelectedListener {

		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
		 */
		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			curCity = cityList.get(position);
			getCityId();
		}

		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(android.widget.AdapterView)
		 */
		@Override
		public void onNothingSelected(AdapterView<?> parent) {
		}
	}
	
}
