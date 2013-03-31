package com.test.asynctask;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ToggleButton;



public class MainActivity extends FragmentActivity implements OnClickListener {
 	
	View mainroom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
  	    LayoutInflater inflater = LayoutInflater.from(this);
  	    List<View> pages = new ArrayList<View>();
	    
	    View radio = inflater.inflate(R.layout.radio, null);
		new StationLoader(radio, this);
	    pages.add(radio);
	    
	    View tv = inflater.inflate(R.layout.tvcontrol, null);
	    //pages.add(tv);
        
	    View kitchen = inflater.inflate(R.layout.kitchen, null);
	   // pages.add(kitchen);

	    
	    this.mainroom = inflater.inflate(R.layout.mainroom, null);
  	    new LigthState(mainroom, this);
	    pages.add(mainroom);
	    
	    View lan = inflater.inflate(R.layout.lan, null);
	    pages.add(lan);
	    
        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);     
        
        setContentView(viewPager);
        
	    WebView myWebView = (WebView) findViewById(R.id.lanwebview);
	    if (myWebView == null) {
	    	System.out.print("mWebView is null");
	    }else{
		    //myWebView.loadUrl("http://192.168.1.5/lan");
		    myWebView.getSettings().setJavaScriptEnabled(true);
		    myWebView.setWebViewClient(new HelloWebViewClient());
		    myWebView.loadUrl("http://google.com");
	    }
	}

	 private class HelloWebViewClient extends WebViewClient {
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);
	            return true;
	        }
	    }

	@Override
	public void onClick(View view) {
	   new SetFrequency(view.getTag().toString());
	}
	
	private void updateBtns(boolean type) {
		ToggleButton tb;
		for (int i = 0; i <this.lamps.length; i++) {
    		this.lampsState[i] = type;
    		tb = (ToggleButton) this.mainroom.findViewById(this.lamps[i]);
    		tb.setTag(i);
    		tb.setChecked(type);
	    }
	}
	
	
	public void ligthControlOnClick(View btn) {
		int id = btn.getId();
		if(id==R.id.all_off || id==R.id.all_on ){
			if(id==R.id.all_on){
				this.updateBtns(true);
			}
			if(id==R.id.all_off){
				this.updateBtns(false);
			}
		}else{
			String tag = btn.getTag().toString();
			int index = Integer.parseInt(tag);
			this.lampsState[index]=!this.lampsState[index]; 		
		}
		new LigthSetter(this.lampsState);
	}
	final boolean[] lampsState = new boolean[40];
			
	final int[] lamps = {
			R.id.lamp1,
			R.id.lamp2,
			R.id.lamp3,
			R.id.lamp4,
			R.id.lamp5,
			R.id.lamp6,
			R.id.lamp7,
			R.id.lamp8,
			R.id.lamp9,
			R.id.lamp10,
			R.id.lamp11,
			R.id.lamp12,
			R.id.lamp13,
			R.id.lamp14,
			R.id.lamp15,
			R.id.lamp16,
			R.id.lamp17,
			R.id.lamp18,
			R.id.lamp19,
			R.id.lamp20,
			R.id.lamp21,
			R.id.lamp22,
			R.id.lamp23,
			R.id.lamp24,
			R.id.lamp25,
			R.id.lamp26,
			R.id.lamp27,
			R.id.lamp28,
			R.id.lamp29,
			R.id.lamp30,
			R.id.lamp31,
			R.id.lamp32,
			R.id.lamp33,
			R.id.lamp34,
			R.id.lamp35,
			R.id.lamp36,
			R.id.lamp37,
			R.id.lamp38,
			R.id.lamp39,
			R.id.lamp40
			};
}
