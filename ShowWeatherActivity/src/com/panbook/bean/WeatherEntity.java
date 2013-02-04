package com.panbook.bean;

public class WeatherEntity {
	
	/**
	 * 获取的各项天气信息
	 */
	private String city; // 城市名 city
	private String cityid; //城市ID
	private String date; // 日期date_y
	private String week; // 星期week
	private String[] weather = new String[6]; // 天气描述 weather1-6
	private String[] temp = new String[6]; // 高低温度temp1-6
	private String[] wind = new String[6]; // 风力
	private String[] img_icon = new String[6]; //图片编号
	private String[] img_title = new String[6]; // 图片描述img_title1/3/5/7/9/11
	private String fchh; // 发布时间fchh
	private String advice; // 关心建议 index_d
	
	
	public WeatherEntity() {
	}
	
	public WeatherEntity(String city, String cityid, String date, String week, 
			String[] weather, String[] temp, String[] wind, String[] img_icon, 
			String[] img_title, String fchh, String advice) {
		this.city = city;
		this.cityid = cityid;
		this.date = date;
		this.week = week;
		this.weather = weather;
		this.temp = temp;
		this.wind = wind;
		this.img_icon = img_icon;
		this.img_title = img_title;
		this.fchh = fchh;
		this.advice = advice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String[] getWind() {
		return wind;
	}

	public void setWind(String[] wind) {
		this.wind = wind;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String[] getWeather() {
		return weather;
	}

	public void setWeather(String[] weather) {
		this.weather = weather;
	}

	public String[] getTemp() {
		return temp;
	}

	public void setTemp(String[] temp) {
		this.temp = temp;
	}

	public String[] getImg_icon() {
		return img_icon;
	}

	public void setImg_icon(String[] imgIcon) {
		img_icon = imgIcon;
	}

	public String[] getImg_title() {
		return img_title;
	}

	public void setImg_title(String[] imgTitle) {
		img_title = imgTitle;
	}

	public String getFchh() {
		return fchh;
	}

	public void setFchh(String fchh) {
		this.fchh = fchh;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	
	
}
