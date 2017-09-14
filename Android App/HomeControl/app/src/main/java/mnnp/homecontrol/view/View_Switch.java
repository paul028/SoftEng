package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/12/2017.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import mnnp.homecontrol.R;
import mnnp.homecontrol.controller.AppController;
import mnnp.homecontrol.model.Switches;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class View_Switch extends Fragment
{
    String name,state,description,username;
    TextView lblName,lblDescription;
    Switch toggleButton;
    RequestQueue requestQueue;

    static final String Name = "Name";
    static final String State = "State";
    static final String Description= "Description";
    static final String Username="Username";
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.view_sensor,container,false);
        requestQueue = Volley.newRequestQueue(getActivity());

        Bundle bundle = this.getArguments();
        if(bundle !=null)
        {
            name = bundle.getString(Name, "Name");
            state = bundle.getString(State, "State");
            description=bundle.getString(Description,"Description");
            username=bundle.getString(Username,"Username");
        }
        else
        {
            name = "Error Loading Switch";
        }

        lblName = (TextView) myView.findViewById(R.id.SensorName);
        lblDescription = (TextView) myView.findViewById(R.id.SensorDescription);
        toggleButton = (Switch) myView.findViewById(R.id.togglebutton);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(toggleButton.isChecked())
                {
                    String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/switch_config_on.php?app_name="+name+"&username="+username;
                    JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
                    {
                        public void onResponse(JSONArray response)
                        {


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                        }
                    });
                    AppController.getInstance().addToRequestQueue(productReq);
                }
                else
                {
                    String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/switch_config_off.php?app_name="+name+"&username="+username;
                    JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
                    {
                        public void onResponse(JSONArray response)
                        {


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                        }
                    });
                    AppController.getInstance().addToRequestQueue(productReq);
                }
            }
        });

        lblName.setText(name);
        if(state.equals("ON"))
            toggleButton.setChecked(true);
        else if (state.equals("OFF"))
            toggleButton.setChecked(false);
        return myView;
    }
}

