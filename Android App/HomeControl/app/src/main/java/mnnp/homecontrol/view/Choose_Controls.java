package mnnp.homecontrol.view;


/**
 * Created by paulv on 9/19/2017.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import mnnp.homecontrol.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mnnp.homecontrol.controller.AppController;
import mnnp.homecontrol.model.Switches;
import mnnp.homecontrol.model.CustomListSwitchAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Choose_Controls extends Fragment
{
    String name,state;
    private final String Name = "Name";
    private final String Username="Username";
    String username="";
    private TextView pageTitle;

    private ProgressDialog pDialog;
    private List<Switches> ProductList = new ArrayList<Switches>();
    private ListView listView;
    private CustomListSwitchAdapter adapter;

    Intent in;

    View myView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.choosecontrols_layout,container,false);

        pageTitle = (TextView) myView.findViewById(R.id.textView1);
        pageTitle.setText("Choose New Controls");
        listView = (ListView) myView.findViewById(R.id.mealsLV);
        adapter = new CustomListSwitchAdapter(getActivity(), ProductList);
        listView.setAdapter(adapter);
      //  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Bundle bundle = this.getArguments();
        if(bundle !=null)
        {
            name = bundle.getString(Name, "Name"); //user_id of user
            username=bundle.getString(Username,"Username"); // admin user_id
        }
        else
        {
            name = "Error Loading Switch";
        }

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Lights");
        pDialog.setCancelable(false);
        pDialog.show();
        load_Controls(name);
        pDialog.dismiss();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
               final String appname = ((TextView) view.findViewById(R.id.switchname)).getText().toString();
                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Confirm Addition of Controls?");
                pDialog.setCancelable(false);
                pDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      //
                        add_Controls(appname,name);
                        refresh();
                    }
                });
                pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                pDialog.show();
            }
        });
        return myView;
    }
    private void add_Controls(String app_name,String username)
    {
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/addControls.php?app_name="+app_name+"&username="+username;
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
    private void load_Controls(String users)
    {
        ProductList.clear();

        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/viewAvailable_Controls.php?username="+users;
        JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {

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

            }
        });

        AppController.getInstance().addToRequestQueue(productReq);

    }
    void refresh()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}