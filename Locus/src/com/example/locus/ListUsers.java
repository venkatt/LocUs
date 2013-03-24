package com.example.locus;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListUsers extends ListActivity implements LocationListener {

	 private TextView latituteField;
	 private TextView longitudeField;
	 private LocationManager locationManager;
	 private String provider;
	 double latitude = 0;
	 double longitude = 0;
	 String username;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
		Intent intent = getIntent();
		username = intent.getStringExtra("userName");
		latituteField = (TextView) findViewById(R.id.textView1);
	    longitudeField = (TextView) findViewById(R.id.textView2);
	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, true);
	    Location location = locationManager.getLastKnownLocation(provider);

	    // Initialize the location fields
	    if (location != null) {
	    	Toast.makeText(getBaseContext(),"Provider " + provider + " has been selected.",Toast.LENGTH_SHORT).show();
	      onLocationChanged(location);
	    } else {
	    	Toast.makeText(getBaseContext(),"Location NOt available", Toast.LENGTH_SHORT).show();
	    	latituteField.setText("Location not available");
	    	longitudeField.setText("Location not available");
	    }
	    
	    //----------------------------- FOR LIST VIEW ---------------------------------------------------------
	    
	    String[] values = new String[] { "Car1", "Car2", "Car3", "Car4", "Car5", "Car6" };
	    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_list_adapter, R.id.NameTextView, values);
	    AdapterList adapter = new AdapterList(this, values);
	    setListAdapter(adapter);
	}
	 //---------------------------------------------------------------------------------------------------------------------------
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		    //String item = (String).getListAdapter().getItem(position);
		    String item = (String)l.getItemAtPosition(position);
		    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	 }
	 
	 //------------------------------------------------------------------------------------------------------------------------
	 /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }

	  @Override
	  public void onLocationChanged(Location location) {
	    latitude = (int) (location.getLatitude());
	    longitude = (int) (location.getLongitude());
	    latituteField.setText(String.valueOf(latitude));
	    longitudeField.setText(String.valueOf(longitude));
	  }

	  @Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void onProviderEnabled(String provider) {
	    Toast.makeText(this, "Enabled new provider " + provider,
	        Toast.LENGTH_SHORT).show();

	  }

	  @Override
	  public void onProviderDisabled(String provider) {
	    Toast.makeText(this, "Disabled provider " + provider,
	        Toast.LENGTH_SHORT).show();
	  }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_users, menu);
		return true;
	}

}
