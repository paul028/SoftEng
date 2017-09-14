package mnnp.homecontrol.controller;

/**
 * Created by paulv on 9/12/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import mnnp.homecontrol.R;


public class Configure extends AppCompatActivity {
    Button local,internet;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_configure);

        local = (Button) findViewById(R.id.localhostbutton);
        internet = (Button) findViewById(R.id.internetbutton);
        local.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Connection_Local.class);
                startActivity(i);
            }});
        internet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Connection_Internet.class);
                startActivity(i);
            }});


    }


}
