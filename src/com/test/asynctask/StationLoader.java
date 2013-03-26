package com.test.asynctask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class StationLoader extends AsyncTask<String, Void, String> {
	View view;
	MainActivity mainActivity;
	
	public StationLoader(View view, MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.view = view;
	    this.execute("http://192.168.1.5/stations.php");
	}

    @Override
    protected String doInBackground(String... arg) {
        String linha = "";
        String retorno = "";
        String url = arg[0]; // Added this line

        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        try {
            HttpResponse response = client.execute(get);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                while ((linha = rd.readLine()) != null) {
                    retorno += linha;
                }
            }
        } catch (ClientProtocolException e) {
        	
            e.printStackTrace();
        } catch (IOException e) {
        	
            e.printStackTrace();
        }

        return retorno; // This value will be returned to your onPostExecute(result) method
    }

    @Override
    protected void onPostExecute(String result) {
    	JSONArray stations = null;
		try {
			stations = new JSONArray(result);
			
			Button button;
			
			for (int i = 0; i < stations.length(); i++) {
			    JSONObject row;
				try {
					row = stations.getJSONObject(i);
					button = new Button(this.mainActivity);
				    ((ViewGroup) this.view).addView(button);
					button.setText(row.getString("f") +" "+ row.getString("name"));
					button.setOnClickListener(this.mainActivity);
					button.setTag(row.getString("f"));

				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}