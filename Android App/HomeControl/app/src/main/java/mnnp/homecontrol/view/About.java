package mnnp.homecontrol.view;

/**
 * Created by paulv on 9/12/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mnnp.homecontrol.R;

public class About extends Fragment
{
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.layout_about,container,false);


        return myView;
    }

}