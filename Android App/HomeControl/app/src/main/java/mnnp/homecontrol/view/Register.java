package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/12/2017.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import mnnp.homecontrol.R;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class Register extends Fragment
{
    String username;
    EditText Etfirstname, Etlastname,Etusername,Etpassword,Etrepeatpass,Etaddress;
    Button RegisterButton;
    RequestQueue requestQueue;
    ProgressDialog pDialog;
    static final String Username="Username";
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pDialog = new ProgressDialog(getActivity());
        myView = inflater.inflate(R.layout.register_layout,container,false);
        requestQueue = Volley.newRequestQueue(getActivity());
        Etfirstname = (EditText) myView.findViewById(R.id.etfirstname);
        Etlastname = (EditText) myView.findViewById(R.id.etlastname);
        Etusername = (EditText) myView.findViewById(R.id.etusername);
        Etaddress = (EditText) myView.findViewById(R.id.etaddress);//
        Etpassword = (EditText) myView.findViewById(R.id.etpassword);
        Etrepeatpass = (EditText) myView.findViewById(R.id.etrepeatpass);
        RegisterButton = (Button) myView.findViewById(R.id.SignupButton);
        Bundle bundle = this.getArguments();
        if(bundle !=null)
        {
            username=bundle.getString(Username,"Username");
        }
        else
        {

        }
        RegisterButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(		Etfirstname.getText().toString().matches("") &&
                        Etlastname.getText().toString().matches("") &&
                        Etusername.getText().toString().matches("") &&
                        Etpassword.getText().toString().matches("") &&
                        Etaddress.getText().toString().matches("") &&
                        Etrepeatpass.getText().toString().matches(""))
                {

                }
                else if (!Etfirstname.getText().toString().matches("") &&
                        !Etlastname.getText().toString().matches("") &&
                        !Etusername.getText().toString().matches("") &&
                        !Etpassword.getText().toString().matches("") &&
                        !Etaddress.getText().toString().matches("") &&
                        !Etrepeatpass.getText().toString().matches(""))
                {
                    pDialog.setMessage("Signing up...");
                    pDialog.show();
                    addUser();
                }
                else if (!Etfirstname.getText().toString().matches("") &&
                        !Etlastname.getText().toString().matches("") &&
                        !Etusername.getText().toString().matches("") &&
                        !Etpassword.getText().toString().matches("") &&
                        !Etaddress.getText().toString().matches("") &&
                        !Etrepeatpass.getText().toString().matches(""))
                {
                    pDialog.setMessage("An error occured.");
                    pDialog.show();
                }

            }

        });
        return myView;
    }

    private void addUser()
    {

        String URL  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/register.php";
        StringRequest request = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>()
        {
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success"))
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Successfully Registered\n "+jsonObject.getString("success").toString(), Toast.LENGTH_LONG).show();
                        pDialog.dismiss();
                                    Etfirstname.setText("");
                                    Etlastname.setText("");
                                    Etusername.setText("");
                                    Etaddress.setText("");
                                    Etpassword.setText("");
                                    Etrepeatpass.setText("");
                        refresh();

                    }
                    else if (jsonObject.names().get(0).equals("error"))
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
                hashMap.put("firstname", Etfirstname.getText().toString());
                hashMap.put("lastname", Etlastname.getText().toString());
                hashMap.put("emailaddress", Etaddress.getText().toString());
                hashMap.put("username", Etusername.getText().toString());
                hashMap.put("password", Etpassword.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);
    }
    void refresh()
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
