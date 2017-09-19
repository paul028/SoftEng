package mnnp.homecontrol.view;


/**
 * Created by paulv on 9/19/2017.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;

public class Remove_Controls extends Fragment
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
    private EditText Eusername;
    private TextView pageTitle,username_warning;
    private CustomListSwitchAdapter adapter;

    Intent in;

    View myView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.removecontrols_layout,container,false);

        pageTitle = (TextView) myView.findViewById(R.id.textView1);
        username_warning =(TextView) myView.findViewById(R.id.textView2);
        pageTitle.setText("Remove Controls");
        Eusername =(EditText) myView.findViewById(R.id.username);
        listView = (ListView) myView.findViewById(R.id.mealsLV);
        adapter = new CustomListSwitchAdapter(getActivity(), ProductList);
        listView.setAdapter(adapter);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        username = sharedPreferences.getString("userLoggedIn","");
        load_Controls(Eusername.getText().toString());
        Eusername.addTextChangedListener(new TextWatcher()
        {
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {}

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                load_Controls(Eusername.getText().toString());//
                validate_account(Eusername.getText().toString());
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                final String name = ((TextView) view.findViewById(R.id.switchname)).getText().toString();
                final String user = Eusername.getText().toString();

                pDialog = new ProgressDialog(getActivity());
                pDialog.setMessage("Remove Controls ?");
                pDialog.setCancelable(false);
                pDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete_controls(user,name);
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
    private void validate_account(String users)
    {
        if(users.equals(""))
        {
            username_warning.setText("");
        }
        else
        {
            String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/validate_account.php?username="+users;
            StringRequest productReq = new StringRequest(url,new Response.Listener<String>()
            {
                public void onResponse(String response)
                {
                    try
                    {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.names().get(0).equals("success"))
                        {

                            username_warning.setText("Account Found!");
                        }
                        if (jsonObject.names().get(0).equals("error"))
                        {
                            username_warning.setText("Account not Found!");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
    private void delete_controls(String username, String app_name)
    {
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/remove_controls.php?app_name="+app_name+"&username="+username;
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

        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/view_controls.php?username="+users;
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