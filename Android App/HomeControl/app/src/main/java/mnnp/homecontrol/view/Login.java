package mnnp.homecontrol.view;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import mnnp.homecontrol.controller.Configure;
import mnnp.homecontrol.R;
import mnnp.homecontrol.model.Loginlist;

public class Login extends Activity
{
    private SharedPreferences sharedPreferences;
    private ProgressDialog pDialog;
    EditText username, password;
    ImageButton configure;
    Button LoginButton;
    ProgressDialog bar;
    RequestQueue requestQueue;

    protected void onPause() {
        super.onPause();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        configure = (ImageButton) findViewById(R.id.imageButton);
        pDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);

        configure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                startActivity(new Intent(getApplicationContext(), Configure.class));

            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginProcess();
            }

        });
        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                LoginProcess();

            }

        });

    }

    private void LoginProcess()
    {
        if (username.getText().toString().matches("") && password.getText().toString().matches(""))
        {
            pDialog.setMessage("Blank username and password.");
            pDialog.show();
        }
        else if (username.getText().toString().matches(""))
        {
            pDialog.setMessage("Blank username.");
            pDialog.show();
        }
        else if (password.getText().toString().matches(""))
        {
            pDialog.setMessage("Blank password.");
            pDialog.show();
        }
        else if (!username.getText().toString().matches("") && !password.getText().toString().matches(""))
        {
            pDialog.setMessage("Logging in...");
            pDialog.show();
            String URL  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Server IP Address","http://")+"/SoftEng/phpFiles/login2.php";
            StringRequest request = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(
                                response);

                        if (jsonObject.names().get(0).equals("success"))
                        {
                            final String[] nameofUser={"",""};
                           // final String nameofUser = jsonObject.getString("success").toString();
                             final String temp = jsonObject.getString("success").toString();
                            int check =0;
                            for(int i=0;i<temp.length();i++)
                            {
                                if (temp.charAt(i) == ',') {
                                    check++;
                                }
                                if(check==0) {
                                    nameofUser[0]+=temp.charAt(i);
                                }
                                if(check==1) {
                                    nameofUser[1]+=temp.charAt(i);
                                }
                            }
                            pDialog.setMessage("Hello, "+nameofUser[0]+".");
                            Thread timer = new Thread()
                            {
                                public void run()
                                {
                                    try
                                    {
                                        sleep(1000);
                                    } catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    } finally
                                    {
                                        pDialog.dismiss();
                                        String usernamelogged= username.getText().toString();
                                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("userLoggedIn", usernamelogged);
                                        editor.putString("User Name",nameofUser[0]);
                                        editor.apply();

                                   //     startActivity(new Intent(getApplicationContext(), Home.class));
                                        if(nameofUser[1].equals(",user"))
                                        {
                                            startActivity(new Intent(getApplicationContext(), Home.class));
                                        }
                                        if(nameofUser[1].equals(",admin"))
                                        {
                                            startActivity(new Intent(getApplicationContext(), Admin_Home.class));
                                        }
                                     //   startActivity(new Intent(getApplicationContext(), Admin_Home.class));
                                    }
                                }

                            };
                            timer.start();
                        } else if (jsonObject.names().get(0)
                                .equals("error")) {
                            pDialog.setCancelable(true);
                            pDialog.setMessage(jsonObject.getString("error").toString());

                        } else if (jsonObject.names().get(0)
                                .equals("error2")) {
                            pDialog.setCancelable(true);
                            pDialog.setMessage(jsonObject
                                    .getString("").toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError arg0)
                {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Unable to Login. Please check if you're connected to the internet", Toast.LENGTH_LONG).show();
                }
            }) {
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("username", username.getText().toString());
                    hashMap.put("password", password.getText().toString());
                    return hashMap;
                }
            };
            requestQueue.add(request);

        }
    }
}