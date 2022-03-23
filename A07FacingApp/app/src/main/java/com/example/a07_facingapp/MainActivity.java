package com.example.a07_facingapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a07_facingapp.databinding.ActivityMainBinding;
import java.util.Locale;

public class MainActivity<getApplicationContext> extends AppCompatActivity implements SensorEventListener{
        //the URL I USED https://www.youtube.com/watch?v=ls1cjNcgdFI
    private ActivityMainBinding binding;

    // define the display assembly compass picture
    private ImageView imageview;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;
    //ImageView imageview;
    SensorManager sensorManager;
    TextView textView;
    TextToSpeech tts;
    EditText ed1;
    private float degree = 0f;
    private String direction;
    private String dir[] = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest", "North"};


    @Override
    //View for using binding
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
//                SensorManager.SENSOR_DELAY_GAME);

        // tts = new TextToSpeech(this, this);
        // ImageView imageview = (ImageView) findViewById(R.id.imageView);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.US);
                }
            }
        });
        // imageview click callback
        binding.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TextView textview = (TextView) findViewById(R.id.textView);
                // binding.textView.setText("Image is clicked");
                speakOut();
//                rotate();
//                clockwise(view);
//
//                //tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
//
//            }
//            private void rotate() {
//                //ImageView image = (ImageView)findViewById(R.id.imageView);
//                Animation animation1 =
//                        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
//                binding.imageview.startAnimation(animation1);
//            }
//
//            private void clockwise(View view) {
//                //ImageView image = (ImageView)findViewById(R.id.imageview);
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
//                        R.anim.myanimation);
//                binding.imageview.startAnimation(animation);
            }
            });



        ///FAB
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FAB", Toast.LENGTH_LONG).show();//speakOut(); //ask about this part of ver04

            }
        });

    }
        @Override
        protected void onResume () {
            super.onResume();
            //// for the system's orientation sensor registered listeners
            mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                    SensorManager.SENSOR_DELAY_GAME);
        }


        @Override
        protected void onPause () {
            super.onPause();
            //// to stop the listener and save battery
            mSensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged (SensorEvent sensorEvent){
// get the angle around the z-axis rotated

            float degree = Math.round(sensorEvent.values[0]);

            binding.textView.setText("Heading: " + Float.toString(degree) + " degrees"); //issue #1

            // create a rotation animation (reverse turn degree degrees)
            RotateAnimation ra = new RotateAnimation(
                    currentDegree,
                    -degree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f);

            // how long the animation will take place
            ra.setDuration(210);

            // set the animation after the end of the reservation status
            ra.setFillAfter(true);

            // Start the animation
            binding.imageview.startAnimation(ra);
            currentDegree = -degree;
        }

        @Override
        public void onAccuracyChanged (Sensor sensor,int accuracy){
//// not in use, NO CODE HERE
        }

        @Override
        public void onDestroy () {
            //// Don't forget to shutdown tts!
            if (tts != null) {
                tts.stop();
                tts.shutdown();
            }
            super.onDestroy();
        }
//    @Override
//    public void onInit(int status) {
//
//        CharSequence text;
//        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//        if (status == TextToSpeech.SUCCESS) {
//            int result = tts.setLanguage(Locale.US);
//            tts.setSpeechRate(1.0f);
//
//            if (result == TextToSpeech.LANG_MISSING_DATA
//                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//            } else {
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
//            } else {
//                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//            }
//
//        } else {
//            tts.speak(binding.direction.getText().toString(),TextToSpeech.QUEUE_ADD,null);
//        }
//    }


    private void speakOut() {
        String text = "You are facing ";
        if (degree<0){
            degree +=360;
        }
        if(degree >= 337.5 || (degree > 0 && degree < 22.5))
            text+= "North";
        else if(degree >= 22.5 && degree < 67.5)
            text+= "North East";
        else if(degree >= 67.5 && degree < 90)
            text+="East";
        else if(degree >= 90 && degree < 112.5)
            text+="South East";
        else if(degree >= 112.5 && degree <180 )
            text+="East";
        else if(degree >= 180 && degree < 202.5)
            text+="South";
        else if(degree >= 202.5 && degree < 247.5)
            text+="South West";
        else if(degree >= 247.5 && degree < 270)
            text+="West";
        else if(degree >= 270 && degree < 292.5)
            text+="North West";
        else
            text+="No Direction";

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
