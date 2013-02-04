package com.panbook.bean;

public class LocationInfo {

	private String status;
	private ResultInfo result;
	
	public String getStatus() {
		return status;
	}
	public ResultInfo getResult() {
		return result;
	}

	public static class ResultInfo {
		SpecLocationInfo specLocation;
		private String formatted_address;
		private String business;
		AddressComponent addressComponent;
		int cityCode;
		
		public SpecLocationInfo getSpecLocation() {
			return specLocation;
		}
		public String getFormatted_address() {
			return formatted_address;
		}
		public String getBusiness() {
			return business;
		}
		public AddressComponent getAddressComponent() {
			return addressComponent;
		}
		public int getCityCode() {
			return cityCode;
		}	
	}
	
	public static class SpecLocationInfo {
		private String lng;
		private String lat;
		
		public String getLng() {
			return lng;
		}
		public String getLat() {
			return lat;
		}
	}

	public static class AddressComponent {
		private String city;
		private String district;
		private String province;
		private String street;
		private String street_number;
		
		public String getCity() {
			return city;
		}
		public String getDistrict() {
			return district;
		}
		public String getProvince() {
			return province;
		}
		public String getStreet() {
			return street;
		}
		public String getStreet_number() {
			return street_number;
		}
	}
	
}
