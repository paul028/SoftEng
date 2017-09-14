package mnnp.homecontrol.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mnnp.homecontrol.R;

import java.util.List;


public class CustomListLogsAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Logs> productItems;

    public CustomListLogsAdapter(Activity activity, List<Logs> productItems)
    {
        this.activity = activity;
        this.productItems = productItems;
    }

    public int getCount()
    {
        return productItems.size();
    }

    public Object getItem(int location)
    {
        return productItems.get(location);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        if (inflater == null)
        {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_logs, null);
        }

        TextView Productname = (TextView) convertView.findViewById(R.id.sensornameTV);
        TextView ProductState = (TextView) convertView.findViewById(R.id.StatusTV);
        TextView user = (TextView) convertView.findViewById(R.id.nameTV) ;
        TextView DateTime = (TextView) convertView.findViewById(R.id.datetimeTV);
        //getting product data for the row
        Logs p = productItems.get(position);


        // Product name
        Productname.setText(p.getSensor_Name());
        ProductState.setText("State:"+p.getStatus());
        user.setText("Updated By:"+p.getName());
        DateTime.setText("Date"+p.getDate_time());



        return convertView;
    }

}