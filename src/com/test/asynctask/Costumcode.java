package com.test.asynctask;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Costumcode extends JSONTask{
	
	public MainActivity ctx;
	public View view;
	
	public void loadStations(View v, MainActivity mainActivity ) {
		ctx = mainActivity;
		view = v;
	    this.execute("http://192.168.1.18/house/stations.php");
	}
	
	@Override
	public void lodaed(JSONArray stations) {
		Button button;
		
		for (int i = 0; i < stations.length(); i++) {
		    JSONObject row;
			try {
				row = stations.getJSONObject(i);
				
				Log.d("f=",row.getString("f"));
				
				button = new Button(ctx);
				button.setText("f");
			    ((ViewGroup) view).addView(button);
				button.setText(row.getString("f") +" "+ row.getString("name"));
				button.setOnClickListener(ctx);
				button.setTag(row.getString("f"));

			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void setFrequency(Object f) {
//		 TODO Auto-generated method stub
		 String matchtemper = "";
         try
         {
 
             URL url = new URL("http://192.168.1.18/house/stations.php?f="+f.toString());
             URLConnection conn = url.openConnection();
             InputStreamReader rd = new InputStreamReader(conn.getInputStream());
             StringBuilder allpage = new StringBuilder();
             int n = 0;
             char[] buffer = new char[40000];
             while (n >= 0)
             {
                 n = rd.read(buffer, 0, buffer.length);
                 if (n > 0)
                 {
                     allpage.append(buffer, 0, n);                    
                 }
             }
         }
         catch (Exception e)
         {
             
         }
		 
	}
	
	
}
