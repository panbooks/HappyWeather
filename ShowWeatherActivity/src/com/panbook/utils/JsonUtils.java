package com.panbook.utils;

import java.util.List;

import org.json.JSONObject;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.panbook.bean.LocationInfo;
import com.panbook.bean.WeatherEntity;
import com.panbook.bean.WeatherInfo;

public class JsonUtils {

	public static WeatherInfo parseWeatherFromJson(String jsonData) {
		Gson gson = new Gson();
		WeatherInfo weatherInfo = gson.fromJson(jsonData, WeatherInfo.class);
		System.out.println("pub--->" + weatherInfo.getPub());
		System.out.println("name---->" + weatherInfo.getName());
		return weatherInfo;
	}

	public static WeatherEntity parseWeatherEntityFromJson(String jsonData) {
		WeatherEntity weatherEntity = new WeatherEntity();
		try {
			JSONObject obj = new JSONObject(jsonData)
					.getJSONObject("weatherinfo");
			String[] weather = new String[] { obj.getString("weather1"),
					obj.getString("weather2"), obj.getString("weather3"),
					obj.getString("weather4"), obj.getString("weather5"),
					obj.getString("weather6") };
			String[] temp = new String[] { obj.getString("temp1"),
					obj.getString("temp2"), obj.getString("temp3"),
					obj.getString("temp4"), obj.getString("temp5"),
					obj.getString("temp6") };
			String[] wind = new String[] { obj.getString("wind1"),
					obj.getString("wind2"), obj.getString("wind3"),
					obj.getString("wind4"), obj.getString("wind5"),
					obj.getString("wind6") };
			String[] img_icon = new String[] { obj.getString("img1"),
					obj.getString("img3"), obj.getString("img5"),
					obj.getString("img7"), obj.getString("img9"),
					obj.getString("img11") };
			String[] img_title = new String[] { obj.getString("img_title1"),
					obj.getString("img_title3"), obj.getString("img_title5"),
					obj.getString("img_title7"), obj.getString("img_title9"),
					obj.getString("img_title11") };
			weatherEntity.setCity(obj.getString("city"));
			weatherEntity.setCityid(obj.getString("cityid"));
			weatherEntity.setWeather(weather);
			weatherEntity.setTemp(temp);
			weatherEntity.setWind(wind);
			weatherEntity.setDate(obj.getString("date_y"));
			weatherEntity.setWeek(obj.getString("week"));
			weatherEntity.setFchh(obj.getString("fchh"));
			weatherEntity.setAdvice(obj.getString("index_d"));
			weatherEntity.setImg_icon(img_icon); // 图片信息需要经过转换
			weatherEntity.setImg_title(img_title); 
			return weatherEntity;
		} catch (Exception e) {
			System.out.println("在给实体类赋值的时候出现错误！");
			e.printStackTrace();
			return null;
		}

	}
	
	public static LocationInfo parseLocationFromJson(String jsonData) {
		
		LocationInfo locationInfo = new Gson().fromJson(jsonData, LocationInfo.class);	
		System.out.println("完整地址：" + locationInfo.getResult().getFormatted_address());
		LocationInfo.AddressComponent addressComponet = locationInfo.getResult().getAddressComponent();
		System.out.println("district:" + addressComponet.getDistrict() + " city:" + addressComponet.getCity() + " province:" + addressComponet.getProvince());
		return locationInfo;	
	}
	
	
/*	public static void parsePersionFromJson(String jsonData) {

		Gson gson = new Gson();
		List<Person> personList = gson.fromJson(jsonData,
				new TypeToken<List<Person>>() {
				}.getType());

		for (int i = 0; i < personList.size(); i++) {
			Person person = personList.get(i);
			System.out.println("name--->" + person.name);
			System.out.println("age--->" + person.age);
		}
	}
*/
}
