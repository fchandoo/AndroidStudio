package com.example.a09_json;

import android.content.Context;
import android.os.Bundle;
import android.view.View;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import  com.example.a09_json.databinding.ActivityMainBinding;


import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//public class MainActivity extends AppCompatActivity {
//    private ActivityMainBinding binding;
//    final String json = "{\"coord\":{\"lon\":-78.7492,\"lat\":42.9685},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03d\"}],\"base\":\"stations\",\"main\":{\"temp\":262.8,\"feels_like\":259.01,\"temp_min\":260.81,\"temp_max\":264.29,\"pressure\":1020,\"humidity\":64},\"visibility\":10000,\"wind\":{\"speed\":1.79,\"deg\":342,\"gust\":4.92},\"clouds\":{\"all\":40},\"dt\":1643479044,\"sys\":{\"type\":2,\"id\":2031741,\"country\":\"US\",\"sunrise\":1643459581,\"sunset\":1643494984},\"timezone\":-18000,\"id\":0,\"name\":\"Buffalo\",\"cod\":200}";
//    FloatingActionButton mAddFab;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);
//
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // https://www.tutorialspoint.com/how-to-read-a-simple-text-file-in-android-app
//                InputStream is = getResources().openRawResource(R.raw.weather);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                binding.textView.setText( "JSON->\n" + parseJson(reader) );
//                ////binding.textview.setText( "JSON->" + json + "\n\n" + parseJson(json) );
//            }
//        });
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.firstmenu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item1:
//                Toast.makeText(this, "First Selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item2:
//                Toast.makeText(this, "Second Selected", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item3:
//                Toast.makeText(this, "Third Selected", Toast.LENGTH_SHORT).show();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//    private String parseJson(BufferedReader reader) {
//        String output = "Error parsing Json";
//        JSONParser parser = new JSONParser();
//        try {
//            ////JSONObject jsonRootObject = (JSONObject) parser.parse(input);
//            JSONObject jsonRootObject = (JSONObject) parser.parse(reader);
//            JSONArray jsonWeatherArray = (JSONArray) jsonRootObject.get("weather");
//            String cond = ((JSONObject) jsonWeatherArray.get(0)).get("main").toString();
//
//            JSONObject  jsonMainObject = (JSONObject) jsonRootObject.get("main");
//            double temp = Double.parseDouble(jsonMainObject.get("temp").toString());
//            String city = jsonRootObject.get("name").toString();
//            output = city + " :condition is " + cond + ", at " + String.format( "%.2f", (temp-273.15)) + " celsius";
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return output;
//    }
//
//
public class MainActivity extends AppCompatActivity {
        private ActivityMainBinding binding;
        final String stringUrl = "https://ziptasticapi.com/48197";
        String json = "";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding = ActivityMainBinding.inflate(getLayoutInflater());
                View view = binding.getRoot();
                setContentView(view);
                binding.button.setOnClickListener(
                        new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Context ctx = MainActivity.this;
                                        binding.textview.setText("JSON->" + json + "\n\n" + parseJson(json));
                                }
                        });
                binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                httpInThread(stringUrl);
                        }
                });
        }
        //version 06
        private void httpInThread(String url) {
                new Thread(new Runnable() {
                        String result = "HTTPS unable to get";

                        @Override
                        public void run() {
                                // do your stuff
                                try {
                                        result = downloadUrl(url);
                                } catch (IOException e) {
                                        binding.textview.setText("Unable to retrieve web page. URL may be invalid.");
                                        return;
                                }
                                runOnUiThread(new Runnable() {
                                        public void run() {
                                                // do onPostExecute stuff
                                                binding.textview.setText(result);
                                                json = result;
                                        }
                                });
                        }
                }).start();
        }

        private String downloadUrl(String urlString) throws IOException {
                String result = "Download https error";
                InputStream in = null;
                BufferedReader reader = null;
                try {
                        URL url = new URL(urlString);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();
                        in = conn.getInputStream();

                        StringBuilder stringBuilder = new StringBuilder();
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                                stringBuilder.append(line);
                        }
                        result = stringBuilder.toString();
                } catch (IOException e) {
                } finally {
                        if (in != null)
                                try {
                                        in.close();
                                        reader.close();
                                } catch (IOException e) {
                                }
                }
                return result;
        }

//Version 03
        private String parseJson(String input) {

        //// HERE
        // JSON Simple
        // https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm
        // https://mkyong.com/java/json-simple-example-read-and-write-json/
        // https://mkyong.com/java/json-simple-how-to-parse-json/

        String output = "Error parsing Json";
        JSONParser parser = new JSONParser();
        try {
        JSONObject jsonRootObject = (JSONObject) parser.parse(input);
        JSONArray jsonWeatherArray = (JSONArray) jsonRootObject.get("weather");
        String cond = ((JSONObject) jsonWeatherArray.get(0)).get("main").toString();

        JSONObject jsonMainObject = (JSONObject) jsonRootObject.get("main");
        double temp = Double.parseDouble(jsonMainObject.get("temp").toString());
        String city = jsonRootObject.get("name").toString();
        output = city + " :condition is " + cond + ", at " + String.format("%.2f", (temp - 273.15)) + " celsius";
        } catch (ParseException e) {
        e.printStackTrace();
        }
        return output;
        }
}
