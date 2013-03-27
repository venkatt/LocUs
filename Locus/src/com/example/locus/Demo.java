package com.example.locus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Demo extends Activity {

	 double latitude = 0;
	 double longitude = 0;
	 String username;
	 private ListView listView;
	 private TextView latituteField;
	 private TextView longitudeField;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
		Intent intent = getIntent();
		username = intent.getStringExtra("userName");
		latitude = Double.parseDouble(intent.getStringExtra("latitude"));
		longitude = Double.parseDouble(intent.getStringExtra("longitude"));
		latituteField = (TextView) findViewById(R.id.textView1);
	    longitudeField = (TextView) findViewById(R.id.textView2);
	    latituteField.setText(String.valueOf(latitude));
	    longitudeField.setText(String.valueOf(longitude));
	    //----------------------------- FOR LIST VIEW ---------------------------------------------------------
	    
	    ListDetails data[] = new ListDetails[]{
	    		
	    		new ListDetails(R.drawable.a, "Car1"),
	    		new ListDetails(R.drawable.b, "Car2"),
	    		new ListDetails(R.drawable.c, "Car3"),
	    		new ListDetails(R.drawable.d, "Car4"),
	    		new ListDetails(R.drawable.d, "Car5"),
	    		new ListDetails(R.drawable.d, "Car6"),
	    		new ListDetails(R.drawable.d, "Car7"),
	    		new ListDetails(R.drawable.d, "Car8"),
	    		new ListDetails(R.drawable.a, "Car9"),
	    		new ListDetails(R.drawable.b, "Car10"),
	    		new ListDetails(R.drawable.c, "Car11"),
	    		new ListDetails(R.drawable.d, "Car12"),
	    		new ListDetails(R.drawable.e, "Car13"),
	    		new ListDetails(R.drawable.a, "Car14"),
	    		new ListDetails(R.drawable.b, "Car15"),
	    		new ListDetails(R.drawable.c, "Car15"),
	    		
	    		new ListDetails(R.drawable.e, "Car5")
	    };
	    AdapterList adapter = new AdapterList (this, R.layout.activity_list_adapter, data);
	    
	    listView = (ListView)findViewById(R.id.listView);
	    listView.setAdapter(adapter);
	    
	    /*listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
                                           int position, long id) {
				// user clicked a list item, make it "selected"
				
				Toast.makeText(getApplicationContext(),listView.getItemAtPosition(position).toString()+" selected", Toast.LENGTH_SHORT).show();
			}
        });*/
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	        @Override
	        public void onItemClick(AdapterView<?> adapter, View view, int position,
	                long id) {
	            // TODO Auto-generated method stub
	            ListDetails o = (ListDetails)adapter.getItemAtPosition(position);
	            String str_text = o.name;
	            Toast.makeText(getApplicationContext(),str_text+" SelecteD ", Toast.LENGTH_SHORT).show();
	        }

	    });  
	 }
	 
	 //------------------------------------------------------------------------------------------------------------------------
	 /* Request updates at startup */
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_users, menu);
		return true;
	}

}
