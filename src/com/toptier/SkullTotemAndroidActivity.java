package com.toptier;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

//Main activity of the App
public class SkullTotemAndroidActivity extends Activity {
	
	String[] images;
	String[] texts;
	int id,idArray;
	private Handler mHandler = new Handler();
	
	//Attribute to handle the callback of the timer 
	private Runnable mUpdateTimeTask = new Runnable() {
 	   public void run() {
 		   if(idArray != images.length){
 			  Resources resources = getResources();
 		       id = resources.getIdentifier( "com.toptier:raw/" + images[idArray], null, null);
	 		  
 		       ImageView image = (ImageView) findViewById(R.id.imageView1);
	 		   image.setImageResource(id);
	 		   TextView textView = (TextView) findViewById(R.id.textView1);
	 		   textView.setText(texts[idArray]);
	 		   
	 		   idArray++;
	 		   
	 		   mHandler.postDelayed(mUpdateTimeTask, 10000);
 		   }
 		   else{
 			  Intent i = new Intent("com.toptier.ENDBUTTONS");
 			  startActivityForResult(i, 0);
 		   }
 			   
 	   }
 	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         setContentView(R.layout.main);
         
         idArray = 0;
         
         //Get arrays from resources 
         Resources res = getResources();
         images = res.getStringArray(R.array.images_array);
         texts  = res.getStringArray(R.array.texts_array);
       
         //Sort the arrays with a ramdom algorithm 
         images = ramdomSort(images);        
         texts = ramdomSort(texts);
        
         //Reset the timer handler
         mHandler.removeCallbacks(mUpdateTimeTask);
         //Init the timer
         mHandler.postDelayed(mUpdateTimeTask, 0);

    }
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//If the buttons activity return OK then finish the app
		if (resultCode == RESULT_OK) { 
			this.finish(); 
		} 
	}

    //Method to do a ramdom sort of an Array 
	private String[] ramdomSort(String[] array){

    	Random random = new Random();
        for (int i = 0; i < array.length; i += 1)
        {
            int swapIndex = random.nextInt(array.length);
            if (swapIndex != i)
            {
                String temp = array[i];
                array[i] = array[swapIndex];
                array[swapIndex] = temp;
            }
        }
        
        return array;
    }
    
}