package com.example.androidcameratest;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 0;
	ImageView photoImage = null;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        photoImage = (ImageView) findViewById(R.id.photo_image);
        
        Button callCameraButton = (Button) findViewById(R.id.button_take_photo);
        callCameraButton.setOnClickListener(new View.OnClickListener() {
          public void onClick(View view) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQ );
          }
        });
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
    	    if (resultCode == RESULT_OK) {
    	      Uri photoUri = data.getData();
    	        Toast.makeText(this, "Image saved successfully in: " + data.getData(), 
    	                       Toast.LENGTH_LONG).show();
    	        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");  
    	        photoImage.setImageBitmap(thumbnail);  
    	        
    	    } else if (resultCode == RESULT_CANCELED) {
    	      Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
    	    } else {
    	      Toast.makeText(this, "Callout for image capture failed!", 
    	                     Toast.LENGTH_LONG).show();
    	    }
    	  }
    	}

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
