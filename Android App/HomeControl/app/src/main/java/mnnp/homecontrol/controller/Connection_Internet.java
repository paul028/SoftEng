package mnnp.homecontrol.controller;

/**
 * Created by paulv on 9/12/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import mnnp.homecontrol.view.Login;
import mnnp.homecontrol.R;

public class Connection_Internet extends AppCompatActivity {
    Button inputButton;
    TextView inputText;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_connection_internet);

        inputButton = (Button) findViewById(R.id.internetipbutton);
        inputText = (TextView) findViewById(R.id.internetiptext);

        String value = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Internet Address",
                "http://");

        inputText.setText(value);


        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Internet Address",
                        inputText.getText().toString()).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Server IP Address",
                        inputText.getText().toString()).commit();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);

            }
        });





    }

    public void onBackPressed() {
        super.onBackPressed();

    }
}