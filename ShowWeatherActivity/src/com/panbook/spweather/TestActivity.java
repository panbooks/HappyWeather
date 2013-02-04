package com.panbook.spweather;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * @author shupan
 *
 */
public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		String contextService=Context.LOCATION_SERVICE;
		//通过系统服务，取得LocationManager对象
		LocationManager locationManager=(LocationManager) getSystemService(contextService);


		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
		criteria.setAltitudeRequired(false);//不要求海拔
		criteria.setBearingRequired(false);//不要求方位
		criteria.setCostAllowed(true);//允许有花费
		criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗
		//从可用的位置提供器中，匹配以上标准的最佳提供器
		String provider = locationManager.getBestProvider(criteria, true);
		//获得最后一次变化的位置
		Location location = locationManager.getLastKnownLocation(provider);

		System.out.println("经度:" + location.getLongitude());
		System.out.println("纬度:" + location.getLatitude());

	}
		
}
