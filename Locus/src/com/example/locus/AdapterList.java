package com.example.locus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterList extends ArrayAdapter<ListDetails> {
  
	Context context; 
    int layoutResourceId;    
    ListDetails data[] = null;
    
    static class ListDetailsHolder
    {
        ImageView ImageView;
        TextView NameTextView;
    }
    
    public AdapterList(Context context, int layoutResourceId, ListDetails[] data){
    	 super(context, layoutResourceId, data);
         this.layoutResourceId = layoutResourceId;
         this.context = context;
         this.data = data;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListDetailsHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ListDetailsHolder();
            holder.ImageView = (ImageView)row.findViewById(R.id.ImageView);
            holder.NameTextView = (TextView)row.findViewById(R.id.NameTextView);
            
            row.setTag(holder);
        }
        else
        {
            holder = (ListDetailsHolder)row.getTag();
        }
        
        ListDetails listdetails = data[position];
        holder.NameTextView.setText(listdetails.name);
        holder.ImageView.setImageResource(listdetails.profilePic);
        
        return row;
    }
    
    
}
  
  
