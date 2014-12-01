package com.example.bluetape;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;


public class viewFlyer extends Activity{
	String imageUrl; 
	ImageView imView; 
	int reqHeight=500;
	int reqWidth=300;
	int inSampleSize;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the message from the intent
		 Intent intent = getIntent();
		 imageUrl = intent.getStringExtra(requirement.EXTRA_MESSAGE);
		 super.onCreate(savedInstanceState);   
			setContentView(R.layout.viewflyer);   
			imView = (ImageView) findViewById(R.id.imagetoshow);   
			//			imView.setImageBitmap(returnBitMap(imageUrl)); 
			calculateSizeAndreturnBitMap(imageUrl, true);
			imView.setImageBitmap(calculateSizeAndreturnBitMap(imageUrl, false));
	}
	
	public Bitmap calculateSizeAndreturnBitMap(String url, boolean calculateSize) {   
		URL myFileUrl = null;   
		Bitmap bitmap = null;  
		// Ïê¼ûStrictModeÎÄµµ
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads()
		.detectDiskWrites()
		.detectNetwork()   // or .detectAll() for all detectable problems
		.penaltyLog()
		.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects()
		.detectLeakedClosableObjects()
		.penaltyLog()
		.penaltyDeath()
		.build());
		try {   
			myFileUrl = new URL(url);   
		} catch (MalformedURLException e) {   
			e.printStackTrace();   
		}   
		try {   
			HttpURLConnection conn = (HttpURLConnection) 
					myFileUrl.openConnection();   
			conn.setDoInput(true);   
			conn.connect();   
			InputStream is = conn.getInputStream();
			final BitmapFactory.Options options = new BitmapFactory.Options();
			if(calculateSize) {
				options.inJustDecodeBounds = true;
				bitmap = BitmapFactory.decodeStream(is,null,options);
				final int height = options.outHeight;
				final int width = options.outWidth;
				inSampleSize = 1;
				if (height > reqHeight || width > reqWidth) {
					// Calculate ratios of height and width to requested height and width
					final int heightRatio = Math.round((float) height / (float) reqHeight);
					final int widthRatio = Math.round((float) width / (float) reqWidth);

					// Choose the smallest ratio as inSampleSize value, this will guarantee
					// a final image with both dimensions larger than or equal to the
					// requested height and width.
					inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
				}	
			}
			else{
			options.inJustDecodeBounds = false; 
			options.inSampleSize =inSampleSize;
			bitmap = BitmapFactory.decodeStream(is,null,options);   
			}
			is.close();   
		} catch (IOException e) {   
			e.printStackTrace();   
		}   
		return bitmap;   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
