package com.panbook.bean;

import java.util.List;

public class WeatherInfo {

	private String pub;
	private String name;
	
	private WindInfo wind;
	private AstronomyInfo astronomy;
	private AtmosphereInfo atmosphere;
	private List<ForecastsInfo> forecasts;

	public String getPub() {
		return pub;
	}

	public String getName() {
		return name;
	}

	public WindInfo getWind() {
		return wind;
	}

	public AstronomyInfo getAstronomy() {
		return astronomy;
	}

	public AtmosphereInfo getAtmosphere() {
		return atmosphere;
	}

	public List<ForecastsInfo> getForecasts() {
		return forecasts;
	}

	
	public static class WindInfo {
		
		private int chill;
		private int direction;
		private int speed;
		
		public int getChill() {
			return chill;
		}
		
		public int getDirection() {
			return direction;
		}
		
		public int getSpeed() {
			return speed;
		}
	}
	
	public static class AstronomyInfo {
		
		private String sunrise;
		private String sunset;
		
		public String getSunrise() {
			return sunrise;
		}
		
		public String getSunset() {
			return sunset;
		}
	}
	
	public static class AtmosphereInfo {
		
		private int humidity;
		private int visibility;
		private int pressure;
		private int rising;
		
		public int getHumidity() {
			return humidity;
		}
		
		public int getVisibility() {
			return visibility;
		}
		
		public int getPressure() {
			return pressure;
		}
		
		public int getRising() {
			return rising;
		}
	}
	
	public static class ForecastsInfo {
		
		private String date;
		private int day;
		private int code;
		private String text;
		private int low;
		private int high;
		private String image_large;
		private String image_small;
		
		public String getDate() {
			return date;
		}
		
		public int getDay() {
			return day;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getText() {
			return text;
		}
		
		public int getLow() {
			return low;
		}
		
		public int getHigh() {
			return high;
		}
		
		public String getImage_large() {
			return image_large;
		}
		
		public String getImage_small() {
			return image_small;
		}	
	}
}
