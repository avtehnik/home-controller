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

import android.os.AsyncTask;

public abstract class JSONTask extends AsyncTask<String, Void, String> {
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
    	JSONArray json = null;
		try {
			json = new JSONArray(result);
	        lodaed(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	abstract void lodaed(JSONArray result);
   
}