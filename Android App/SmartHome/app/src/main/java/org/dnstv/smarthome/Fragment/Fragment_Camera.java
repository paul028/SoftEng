package org.dnstv.smarthome.Fragment;

/**
 * Created by paulv on 8/28/2017.
 */


import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.dnstv.smarthome.R;


/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Camera extends Fragment
{
    View myView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_camera, container, false);



        return myView;
    }
}