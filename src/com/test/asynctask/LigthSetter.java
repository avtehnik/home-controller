package com.test.asynctask;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.AsyncTask;


public class LigthSetter extends AsyncTask<boolean[], Void, String>{

    public LigthSetter(boolean[] state) {
    	this.execute(state);
	}
    
    public byte[] BitArrayToByteArray(boolean[] bits){
        int bytesize = (bits.length / 7)+1;
        byte[] bytes = new byte[bytesize];
        int bytepos = 1;
        int bitpos = 0;
        int pos = 0;
        String mybyte="";
        while(bitpos<bits.length){
        	  if(bits[bitpos]==true){
          	  mybyte+="1";
      	  }else{
          	  mybyte+="0";
      	  }
      	  pos++;
      	  if (pos==8){
      		  bytes[bytepos] = (byte) Integer.parseInt(mybyte, 2);
                bytepos++;
                mybyte="";
          	  pos=0;
      	  }
      	  bitpos++;
        }
        bytes[0]=(byte)(bytesize);
        return bytes;
      }

	@Override
    protected String doInBackground(boolean[]... data){
		try {
			final int server_port = 1099;  
			final DatagramSocket s = new DatagramSocket();  
			final InetAddress local = InetAddress.getByName("192.168.1.5");
			byte[] out = this.BitArrayToByteArray(data[0]);
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
