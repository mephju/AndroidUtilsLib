package com.blackmill.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Img {

	public static Bitmap decodeFile(File f, final int IMAGE_MAX_SIZE){
		Bitmap b = null;
		try {
			//Decode image size
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inJustDecodeBounds = true;

			FileInputStream fis = new FileInputStream(f);
			BitmapFactory.decodeStream(fis, null, opt);
			fis.close();

			int scale = 1;
			if (opt.outHeight > IMAGE_MAX_SIZE || opt.outWidth > IMAGE_MAX_SIZE) {
				scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(opt.outHeight, opt.outWidth)) / Math.log(0.5)));
			}

			//Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			fis 	= new FileInputStream(f);
			b 		= BitmapFactory.decodeStream(fis, null, o2);

			fis.close();
		} 
		catch (IOException e) {	e.printStackTrace();	}

		return b;
	}

	
	public static Bitmap decodeFromUrl(URL url, final int IMAGE_MAX_SIZE){
		Bitmap b = null;
		try {
			
			InputStream stream = (InputStream) url.getContent();
			
			BitmapFactory.Options optBounds 	= new BitmapFactory.Options();
			BitmapFactory.Options optScale	 	= new BitmapFactory.Options();
			
			optBounds.inJustDecodeBounds = true;
			
			BitmapFactory.decodeStream(stream, null, optBounds);
		
			stream.close();
			
			int scale = 1;
			if (optBounds.outHeight > IMAGE_MAX_SIZE || optBounds.outWidth > IMAGE_MAX_SIZE) {
				scale = (int)Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE / (double) Math.max(optBounds.outHeight, optBounds.outWidth)) / Math.log(0.5)));
			}
		
			optScale.inSampleSize = scale;
			
			stream = (InputStream) url.getContent();
		
			b 		= BitmapFactory.decodeStream(stream, null, optScale);

			stream.close();
		} 
		catch (IOException e) {	e.printStackTrace();	}

		return b;
	}
}
