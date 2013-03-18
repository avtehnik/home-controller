package com.test.asynctask;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener {

	
 
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Costumcode cc = new Costumcode();
		super.onCreate(savedInstanceState);

  	    LayoutInflater inflater = LayoutInflater.from(this);
  	    List<View> pages = new ArrayList<View>();
	        

  	    
	    View kitchen = inflater.inflate(R.layout.kitchen, null);
	    pages.add(kitchen);

	    
	    View radio = inflater.inflate(R.layout.radio, null);
	    cc.loadStations(radio , this);
	    pages.add(radio);
	    
	    
	    
	    View page = inflater.inflate(R.layout.mainroom, null);
	    pages.add(page);
	    
        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);     
        
        setContentView(viewPager);
	}
	

	@Override
	public void onClick(View view) {
				
		HttpGetTask a =  new HttpGetTask();
		
		a.execute("http://192.168.1.18/house/simple_udp_client_server/tune.php?f="+view.getTag().toString());
				
	        // Do something
	}

}
