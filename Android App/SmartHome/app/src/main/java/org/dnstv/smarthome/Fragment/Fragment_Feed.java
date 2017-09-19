package org.dnstv.smarthome.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import org.dnstv.smarthome.R;



/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Feed extends Fragment
{
    ListView listView;
    View myView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_feed, container, false);


        return myView;
    }
}