package mnnp.homecontrol.controller;

/**
 * Created by paulv on 9/12/2017.
 */
import mnnp.homecontrol.R;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import mnnp.homecontrol.view.Login;
import android.widget.TextView;

public class Connection_Local extends AppCompatActivity {
    Button inputButton;
    TextView inputText;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_connection_local);

        inputButton = (Button) findViewById(R.id.localipbutton);
        inputText = (TextView) findViewById(R.id.localiptext);

        String value = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("Local IP Address",
                "http://");

        inputText.setText(value);


        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("Local IP Address",
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