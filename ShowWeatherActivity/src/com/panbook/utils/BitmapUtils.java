package com.panbook.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtils {

	public static Bitmap zoomBitMap(Bitmap bitmapOrg, int newWidth, int newHeight) {
		
        //获取这个图片的宽和高    
        int width = bitmapOrg.getWidth();   
        int height = bitmapOrg.getHeight();   
    
        //计算缩放率，新尺寸除原始尺寸    
        float scaleWidth = ((float) newWidth) / width;   
        float scaleHeight = ((float) newHeight) / height;   
  
        // 创建操作图片用的matrix对象    
        Matrix matrix = new Matrix();   
        // 缩放图片动作    
        matrix.postScale(scaleWidth, scaleHeight);   
        //旋转图片 动作    
        //matrix.postRotate(45);   
  
        // 创建新的图片    
        return Bitmap.createBitmap(bitmapOrg, 0, 0,   
                          width, height, matrix, true);   	
	}
	
}
