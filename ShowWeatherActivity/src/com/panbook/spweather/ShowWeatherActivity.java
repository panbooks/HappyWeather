package com.panbook.spweather;



import com.panbook.bean.LocationInfo;
import com.panbook.bean.WeatherEntity;
import com.panbook.config.CommonConfig;
import com.panbook.db.DatabaseHelper;
import com.panbook.net.HttpDownloader;
import com.panbook.utils.BitmapUtils;
import com.panbook.utils.FileUtils;
import com.panbook.utils.JsonUtils;
import com.panbook.utils.LocationUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShowWeatherActivity extends Activity {

	private final static String TAG = "panbook";
	
	private double longitude;
	private double latitude;
	private String city = "";
	private String cityId = "";
	private Bitmap bitmap;
	
	private WeatherEntity weatherEntity;
	
	private ImageView imageWeather;
	private TextView textCityName;
	private TextView textTemp;
	private TextView textWind;
	private TextView textDay;
	private TextView textDate;
	private TextView textWeather;
	private TextView textAdvice;

	public ShowWeatherActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_weather);

		imageWeather = (ImageView) findViewById(R.id.weatherImage);
		textCityName = (TextView) findViewById(R.id.textCityName);
		textWind = (TextView) findViewById(R.id.textWind);
		textAdvice = (TextView) findViewById(R.id.textAdvice);
		
		textTemp = (TextView) findViewById(R.id.textTemp);
		textDay = (TextView) findViewById(R.id.textDay);
		textDate = (TextView) findViewById(R.id.textDate);
		textWeather = (TextView) findViewById(R.id.textWeather);

		textCityName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startSelectCityActivity();
			}
		});
		
		getLocation();
		getCityId();
		getWeatherData();	
		displayWeatherInfo();
	}
	
	/**
	 * 启动选择城市的Activity
	 */
	public void startSelectCityActivity() {
		Intent intent = new Intent(ShowWeatherActivity.this, SelectCityActivity.class);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			Bundle bundle = data.getExtras();
			cityId = bundle.getString("cityId");
			getWeatherData();
			displayWeatherInfo();
			break;
		default:
			break;
		}
	}

	/**
	 * 渲染天气信息
	 */
	public void displayWeatherInfo() {

		textCityName.setText(weatherEntity.getCity());
		textWind.setText("风级： " + weatherEntity.getWind()[0]);
		//木有湿度
		//textHumidity.setText(String.format("湿度： %d", weatherEntity.getAtmosphere().getHumidity()));
		textTemp.setText("温度： " + weatherEntity.getTemp()[0]);
		textDay.setText(weatherEntity.getWeek());
		textDate.setText(weatherEntity.getDate());
		textWeather.setText("天气： " + weatherEntity.getWeather()[0]);
		textAdvice.setText("	" + weatherEntity.getAdvice());

		setWeatherBitmap();
	}
	

	
	/**
	 * 获取经纬度，并通过经纬度获取地理位置信息
	 */
	public void getLocation() {

		Location location = LocationUtil.getLocalLocation(this);
		if (null == location) {
			city = "北京";
			Toast.makeText(getApplicationContext(), "获取地理位置失败，默认城市" + city, Toast.LENGTH_LONG);
			return ;
		}
		longitude = location.getLongitude();
		latitude = location.getLatitude();
		
		String getLocationUrl = String.format(CommonConfig.BaiduGetLocationUrl, CommonConfig.BaiduMapKey, Double.toString(latitude), Double.toString(longitude));
		System.out.println(getLocationUrl);
		String locationStr = HttpDownloader.download(getLocationUrl);
		System.out.println(locationStr);
		LocationInfo locationInfo = JsonUtils.parseLocationFromJson(locationStr);
		
		LocationInfo.AddressComponent addressComponet = locationInfo.getResult().getAddressComponent();
		//String district = addressComponet.getDistrict();
		city = addressComponet.getCity();
		int index = city.indexOf("市");
		city = index > 0 ? city.substring(0, index) : city;;
		//String province = addressComponet.getProvince();
		Toast.makeText(getApplicationContext(), "您在" + locationInfo.getResult().getFormatted_address(), Toast.LENGTH_LONG);
	}
	
	/**
	 * 根据城市名称获取城市编码
	 */
	public void getCityId() {
		
		DatabaseHelper dbHelper = new DatabaseHelper(ShowWeatherActivity.this, CommonConfig.DBNAME);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("weather", new String[]{"citycode"}, "city=?", new String[]{city}, null, null, null);
		while(cursor.moveToNext()){
			cityId = cursor.getString(cursor.getColumnIndex("citycode"));
			System.out.println("query--->" + cityId);
			break;
		}
		cursor.close();
		db.close();
	}	

	/**
	 * 根据城市编码获取天气原始json信息
	 */
	public void getWeatherData() {	
		String getWeatherUrl = String.format(CommonConfig.GetWeatherUrl, cityId);
		String jsonData = HttpDownloader.download(getWeatherUrl);
		System.out.println("天气信息------" + jsonData);
		// 将原始天气信息转换为天气对象
		weatherEntity = JsonUtils.parseWeatherEntityFromJson(jsonData);
		System.out.println(weatherEntity);
	}
	
	/**
	 * 将格式化的天气对象渲染出来，从assets中读取图片，展现出来
	 */
	public void setWeatherBitmap() {
		String fullPath = String.format("weather_png/%s.png", weatherEntity.getImg_icon()[0].trim());
		bitmap = FileUtils.getBitmapFromAssets(this, fullPath);
		System.out.println("宽：" + bitmap.getWidth() + "高：" + bitmap.getHeight());
		bitmap = BitmapUtils.zoomBitMap(bitmap, 320, 320);
		imageWeather.setImageBitmap(bitmap);
		
		// 根据天气信息渲染背景图片
		setBackgroudBitmap();
	}
	
	/**
	 * 根据天气信息设置不同的背景图片
	 */
	public void setBackgroudBitmap() {
		int picIndex = Integer.parseInt(weatherEntity.getImg_icon()[0].trim());
		String picName = CommonConfig.BACKGROUDPNG[picIndex];
		String fullPath = String.format("background_pic/%s.jpg", picName);
		bitmap = FileUtils.getBitmapFromAssets(this, fullPath);
		Drawable d = new BitmapDrawable(bitmap);
		
		LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		RelativeLayout layout = (RelativeLayout) layoutInflater.inflate(R.layout.show_weather, null);
		layout.setBackgroundDrawable(d);
		
		getWindow().setBackgroundDrawable(d);
	}
	
}
