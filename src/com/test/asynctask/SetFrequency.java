package com.test.asynctask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.AsyncTask;


public class SetFrequency extends AsyncTask<String, Void, String>{

    public SetFrequency(String string) {
    	this.execute(string);
	}

	@Override
    protected String doInBackground(String... strings){
		try {
			final int server_port = 1093;  
			final DatagramSocket s = new DatagramSocket();  
			final InetAddress local = InetAddress.getByName("192.168.1.5");
  			String[] frequencyParts = strings[0].split("\\.");
		    byte[] out = {(byte) Integer.parseInt(frequencyParts[0]),(byte) Integer.parseInt(frequencyParts[1])};
			DatagramPacket p = new DatagramPacket(out, out.length, local, server_port);  
			s.send(p);  
			s.close(); 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    protected void onPostExecute(String result){
       //callback.onTaskComplete(result);
    }
}
