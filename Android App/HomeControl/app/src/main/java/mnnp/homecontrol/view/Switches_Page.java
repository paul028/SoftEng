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
import mnnp.homecontrol.model.Switches;
import mnnp.homecontrol.model.CustomListSwitchAdapter;
import java.util.ArrayList;
import java.util.List;

public class Switches_Page extends Fragment
{
    private final String Name = "Name";
    private final String State = "State";
    private final String Description = "Description";
    private final String Username="Username";
    private String URL_FEED = "";
    String username="";


    private ProgressDialog pDialog;
    private List<Switches> ProductList = new ArrayList<Switches>();
    private ListView listView;
    private CustomListSwitchAdapter adapter;

    Intent in;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.switch_layout,container,false);

        TextView pageTitle = (TextView) myView.findViewById(R.id.textView1);
        pageTitle.setText("Switches");

        listView = (ListView) myView.findViewById(R.id.mealsLV);
        adapter = new CustomListSwitchAdapter(getActivity(), ProductList);
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        username = sharedPreferences.getString("userLoggedIn","");
        ProductList.clear();

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Lights");
        pDialog.setCancelable(false);
        pDialog.show();
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/view_myswitch.php?username="+username;
        JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                pDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Switches switches = new Switches();
                        switches.setName(obj.getString("app_name"));
                        switches.setState(obj.getInt("app_state"));
                        switches.setDescription(obj.getString("app_description"));
                        ProductList.add(switches);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                String name = ((TextView) view.findViewById(R.id.switchname)).getText().toString();
                String state = ((TextView) view.findViewById(R.id.switchstate)).getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString(Name,name);
                bundle.putString(State,state);
                bundle.putString(Description,"Description");
                bundle.putString(Username,username);
                View_Switch args = new View_Switch();
                args.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, args).addToBackStack(null).commit();
            }
        });


        return myView;
    }

}