package com.panbook.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

public class CommonUtils {

	public static String getDayStr(int i) {
		String dayStr = "";
		switch (i) {
		case 1:
			dayStr = "Monday";
			break;
		case 2:
			dayStr = "Tuesday";
			break;
		case 3:
			dayStr = "Wednesday";
			break;
		case 4:
			dayStr = "Thursday";
			break;
		case 5:
			dayStr = "Friday";
			break;
		case 6:
			dayStr = "Saturday";
			break;
		case 7:
			dayStr = "Sunday";
			break;

		default:
			break;
		}
		return dayStr;
	}
	
	/**
	 * 获取屏幕截图
	 * @param context
	 * @return
	 */
	private Bitmap getSnapShot(Context context) {

		View view = ((Activity)context).getWindow().getDecorView().getRootView();
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		return BitmapUtils.zoomBitMap(bitmap, 200, 300);
	}
	
}
