package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/19/2017.
 */

import android.app.ProgressDialog;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import mnnp.homecontrol.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mnnp.homecontrol.controller.AppController;
import mnnp.homecontrol.model.Switches;
import mnnp.homecontrol.model.CustomListSwitchAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Change_Password extends Fragment
{
    private final String Name = "Name";
    private final String State = "State";
    private final String Description = "Description";
    private final String Username="Username";
    private String URL_FEED = "";
    String username="";
    RequestQueue requestQueue;
    private ProgressDialog pDialog;
    private List<Switches> ProductList = new ArrayList<Switches>();
    private ListView listView;
    private EditText Oldpass,Newpass,cNewpass;
    private Button CChangepass;
    private TextView pageTitle,username_warning;
    private CustomListSwitchAdapter adapter;

    Intent in;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.change_password,container,false);
        requestQueue = Volley.newRequestQueue(getActivity());
        pageTitle = (TextView) myView.findViewById(R.id.textView1);
        pageTitle.setText("Change Password");
        pDialog = new ProgressDialog(getActivity());

        CChangepass=(Button) myView.findViewById(R.id.Changepass);
        Oldpass =(EditText) myView.findViewById(R.id.oldpassword);
        Newpass =(EditText) myView.findViewById(R.id.newpassword);
        cNewpass =(EditText) myView.findViewById(R.id.cnewpassword);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        username = sharedPreferences.getString("userLoggedIn","");

        CChangepass.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(		Oldpass.getText().toString().matches("") &&
                        Newpass.getText().toString().matches("") &&
                        cNewpass.getText().toString().matches(""))
                {

                }
                else if (!Oldpass.getText().toString().matches("") &&
                        !Newpass.getText().toString().matches("") &&
                        !cNewpass.getText().toString().matches("") )
                {
                    pDialog.setMessage("Signing up...");
                    pDialog.show();
                    changePass();
                }
                else if (!Oldpass.getText().toString().matches("") &&
                        !Newpass.getText().toString().matches("") &&
                        !cNewpass.getText().toString().matches("") )
                {
                    pDialog.setMessage("An error occured.");
                    pDialog.show();
                }
            }
        });

        return myView;
    }
    private void changePass()
    {

        String URL  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/changepass.php";
        StringRequest request = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>()
        {
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success"))
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Password Updated Successfully\n "+jsonObject.getString("success").toString(), Toast.LENGTH_LONG).show();
                        pDialog.dismiss();
                        Oldpass.setText("");
                        Newpass.setText("");
                        cNewpass.setText("");
                        refresh();

                    }
                    if (jsonObject.names().get(0).equals("error"))
                    {
                        pDialog.setCancelable(true);
                        pDialog.setMessage(jsonObject.getString("error").toString());
                    }
                    else
                    {
                        pDialog.setCancelable(true);
                        pDialog.setMessage("Error!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            public void onErrorResponse(VolleyError arg0)
            {

            }
        }) {
            protected Map<String, String> getParams()
                    throws AuthFailureError
            {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("username", username);
                hashMap.put("old_pass", Oldpass.getText().toString());
                hashMap.put("new_pass", Newpass.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);
    }


    void refresh() // refresh every 100ms
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}