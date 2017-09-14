package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/12/2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import mnnp.homecontrol.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mnnp.homecontrol.controller.AppController;
import mnnp.homecontrol.model.Logs;
import mnnp.homecontrol.model.CustomListLogsAdapter;
import java.util.ArrayList;
import java.util.List;

public class Logs_Page extends Fragment
{
    private final String Name = "Name";
    private final String State = "State";
    private final String Description = "Description";
    private final String Username="Username";
    private String URL_FEED = "";
    String username="";


    private ProgressDialog pDialog;
    private List<Logs> ProductList = new ArrayList<Logs>();
    private ListView listView;
    private CustomListLogsAdapter adapter;

    Intent in;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.switch_layout,container,false);

        TextView pageTitle = (TextView) myView.findViewById(R.id.textView1);
        pageTitle.setText("Logs");

        listView = (ListView) myView.findViewById(R.id.mealsLV);
        adapter = new CustomListLogsAdapter(getActivity(), ProductList);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        username = sharedPreferences.getString("userLoggedIn","");
        ProductList.clear();

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Lights");
        pDialog.setCancelable(false);
        pDialog.show();
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/getlogs.php?username="+username;
        JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                pDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Logs logs = new Logs();
                        logs.setSensor_Name(obj.getString("app_name"));
                        logs.setStatus(obj.getString("state"));
                        logs.setName(obj.getString("name"));
                        logs.setDate_time(obj.getString("log_time"));
                        ProductList.add(logs);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pDialog.dismiss();
            }
        });

        AppController.getInstance().addToRequestQueue(productReq);
        return myView;
    }

}