package mnnp.homecontrol.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import mnnp.homecontrol.R;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class Admin_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        CustomActivityOnCrash.install(this);
        CustomActivityOnCrash.setShowErrorDetails(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new adminSwitches_Page()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_switch)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new adminSwitches_Page()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_alarms)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new adminAlarms_Page()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_light)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new adminLights_Page()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_logs)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new Logs_Page()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_addUser)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new Register()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_addControls)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new Add_Controls()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_removeControls)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new Remove_Controls()).addToBackStack(null).commit();

        }
        else if (id == R.id.nav_logout)
        {
            Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
            finish();
        }
        else if (id == R.id.nav_change_password)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new Change_Password()).addToBackStack(null).commit();
        }
        else if (id == R.id.nav_about)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame2, new About()).addToBackStack(null).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
