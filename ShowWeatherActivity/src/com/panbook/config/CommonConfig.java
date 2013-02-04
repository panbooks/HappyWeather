package com.panbook.config;

public class CommonConfig {
	
	//百度地图申请的key，Place API限制1000次/天，Geocoding API无限使用。
	public static final String BaiduMapKey = "C20ADDE2C63832C42A0E1F68D7C3F700A215387D";
	
	//百度地图api，通过经纬度获取地理位置信息
	//http://api.map.baidu.com/geocoder?output=json&key=e3493f2f91edcc6ba20048a459892c08&location=40.05,116.29
	/*
	{
	    "status":"OK",
	    "result":{
	        "location":{
	            "lng":116.29,
	            "lat":40.05
	        },
	        "formatted_address":"北京市海淀区西北旺路",
	        "business":"西北旺",
	        "addressComponent":{
	            "city":"北京市",
	            "district":"海淀区",
	            "province":"北京市",
	            "street":"西北旺路",
	            "street_number":""
	        },
	        "cityCode":131
	    }
	}
	*/
	public static final String BaiduGetLocationUrl = "http://api.map.baidu.com/geocoder?output=json&key=%s&location=%s,%s";
	
	//中央气象台获取天气信息api
	//http://m.weather.com.cn/data/101010100.html
	public static final String GetWeatherUrl = "http://m.weather.com.cn/data/%s.html";

	
	public static final String DBNAME = "weather.db";
	
/*	public static final String[] BACKGROUDPNG = {
		
		"sunny",  //晴
		"cloudy",  //多云
		"overcast",  //阴
		"showers", //阵雨
		"thunder_showers", //雷雨
	
		"showers_with_hail", //雷阵雨伴有冰雹
		"sleet", //雨夹雪
		"drizzle", //小雨
		"middle_rain", //中雨
		"big_rain", //大雨
		
		"pour", //暴雨
		"big_pour", //大暴雨
		"heavy_pour", // 特大暴雨
		"snow_showers", //阵雪
		"light_snow", //小雪
		
		"middle_snow", //中雪
		"big_snow", //大雪
		"heavy_snow", //暴雪
		"fog", //雾
		"freezing_rain", //冻雨
		
		"sandstorm", //沙尘暴
		"small_middle_rain", //小雨-中雨
		"middle_big_rain", //中雨-大雨 
		"big_rain_pour", //大雨-暴雨
		"pour_big_pour",  //暴雨-大暴雨
		
		"big_heavy_pour", //大暴雨-特大暴雨
		"small_middle_snow", //小雪-中雪
		"middle_big_snow", //中雪-暴雪
		"big_heavy_snow", //大雪-暴雪
		"float_sand", //浮尘
		
		"sand", //扬沙
		"strong_sandstorm" //强沙尘暴
	};*/
	
	
	/**
	 * 目前暂设置这几种背景图片 pic_sunny, pic_cloudy, pic_overcast, pic_rain, pic_snow,
	 * pic_fog, pic_other
	 * 
	 */
	public static final String[] BACKGROUDPNG = {

			"pic_sunny", // 晴
			"pic_cloudy", // 多云
			"pic_overcast", // 阴
			"pic_rain", // 阵雨
			"pic_rain", // 雷雨

			"pic_rain", // 雷阵雨伴有冰雹
			"pic_rain", // 雨夹雪
			"pic_rain", // 小雨
			"pic_rain", // 中雨
			"pic_rain", // 大雨

			"pic_rain", // 暴雨
			"pic_rain", // 大暴雨
			"pic_rain", // 特大暴雨
			"pic_snow", // 阵雪
			"pic_snow", // 小雪

			"pic_snow", // 中雪
			"pic_snow", // 大雪
			"pic_snow", // 暴雪
			"pic_fog", // 雾
			"pic_rain", // 冻雨

			"sandstorm", // 沙尘暴
			"pic_rain", // 小雨-中雨
			"pic_rain", // 中雨-大雨
			"pic_rain", // 大雨-暴雨
			"pic_rain", // 暴雨-大暴雨

			"pic_rain", // 大暴雨-特大暴雨
			"pic_snow", // 小雪-中雪
			"pic_snow", // 中雪-暴雪
			"pic_snow", // 大雪-暴雪
			"pic_other", // 浮尘

			"pic_other", // 扬沙
			"pic_other", // 强沙尘暴
	};

	public static final int[] PIC_SUNNY = { 1 };
	public static final int[] PIC_CLOUNDY = { 2 };
	public static final int[] PIC_OVERCAST = { 3 };
	public static final int[] PIC_RAIN = { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
			20, 22, 23, 24, 25, 26 };
	public static final int[] PIC_SNOW = { 14, 15, 16, 17, 18, 27, 28, 29 };
	public static final int[] PIC_FOG = { 19 };
	public static final int[] PIC_OTHER = { 21, 30, 31, 32 };

	public static final int[] PIC_SMALL_RAIN = {};
	public static final int[] PIC_BIG_RAIN = {};
	public static final int[] PIC_SMALL_SNOW = {};
	public static final int[] PIC_BIG_SNOW = {};

}
