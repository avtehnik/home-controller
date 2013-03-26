package com.test.asynctask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ToggleButton;


public class LigthState extends AsyncTask<Void, Void, boolean[]>{
	View view;
	MainActivity mainActivity;
	
	public LigthState(View view, MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.view = view;
		 this.execute();
	}

	private boolean[] toBooleanA(byte[] bs) {
		int maxSize = (bs[0]-1)*8;
		boolean[] result = new boolean[maxSize];
		 int offset = 0;
		 short s;
		 for (int j=1; j<bs[0]; j++){			 
			  for (int i=0; i<8; i++){
				  s = (short) (bs[j] & 0xFF);
				  if(offset<maxSize){
					  result[offset] = (s & (0x80 >> i))>0;
				  }
				  offset++;
			  }
		  }
		 return result;
	}
	
	@Override
	protected boolean[] doInBackground(Void... params) {
		boolean[] buttons = null;
		try {
			final int server_port = 1099;  
			final DatagramSocket s = new DatagramSocket();  
			final InetAddress local = InetAddress.getByName("192.168.1.5");
		    byte[] out = {(byte)50};

		    byte[] message = new byte[100];
			DatagramPacket p = new DatagramPacket(out, out.length, local, server_port);  
			s.send(p);  
			DatagramPacket state = new DatagramPacket(message, message.length);
			s.receive(state);
			buttons = this.toBooleanA(state.getData());
		
			s.close(); 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buttons;
	}
	

	protected void onPostExecute(boolean[] state ){
		ToggleButton tb;
    	for (int i = 0; i <state.length; i++) {
    		this.mainActivity.lampsState[i] = state[i];
    		tb = (ToggleButton) this.view.findViewById(this.mainActivity.lamps[i]);
    		tb.setTag(i);
			if(state[i]){
				tb.setChecked(true);
			}
	    }
    }

 

}
