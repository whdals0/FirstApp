package com.roopre.firstapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    private int mColor = Color.BLUE;

    private TextView hello_text;

    public ColorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_color, container, false);

        hello_text = view.findViewById(R.id.hello_text);
        hello_text.setBackgroundColor(mColor);


        return view;


    }

    public void setColor(int color){

        mColor = color;
        if(hello_text != null) {
            hello_text.setBackgroundColor(mColor);
        }
    }
}
