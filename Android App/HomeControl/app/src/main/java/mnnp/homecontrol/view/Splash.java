package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/12/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import mnnp.homecontrol.R;

public class Splash extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread()
        {

            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch(InterruptedException e )
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent i = new Intent(Splash.this, Login.class);
                    startActivity(i);

                }
            }

        };
        timer.start();
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

}