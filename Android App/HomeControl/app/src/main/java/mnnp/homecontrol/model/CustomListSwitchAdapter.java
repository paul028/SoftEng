package mnnp.homecontrol.model;

/**
 * Created by paulv on 9/12/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import mnnp.homecontrol.R;
import java.util.List;

public class CustomListSwitchAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Switches> productItems;

    public CustomListSwitchAdapter(Activity activity, List<Switches> productItems) {
        this.activity = activity;
        this.productItems = productItems;
    }

    public int getCount() {
        return productItems.size();
    }

    public Object getItem(int location) {
        return productItems.get(location);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_switch, null);
        }

        TextView Switchname = (TextView) convertView.findViewById(R.id.switchname);
        TextView SwitchState = (TextView) convertView.findViewById(R.id.switchstate);
        //getting product data for the row
        Switches p = productItems.get(position);


        // Product name
        Switchname.setText(p.getName());
        if(p.getState()==1)
        {
            SwitchState.setText("ON");
        }
        else if(p.getState()==0) {

            SwitchState.setText("OFF");
        }

        return convertView;
    }
}