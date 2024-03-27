package com.example.project.MainPkg;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;

import java.util.Random;


public class Homepage extends Fragment {
    TextView welcomeText, tips;
    ImageButton settings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        settings = view.findViewById(R.id.settingsButton);
        tips = view.findViewById(R.id.tvtips);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });


        welcomeText = view.findViewById(R.id.textView2);
        welcomeText.setText("Welcome " + MainActivity.getUsername());

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        Random random =  new Random();
        String[] tipsArray = {"Eating food makes you full", "Do not underestimate the cauliflower", "Once a carrot always a carrot", "Hummus much?", "Tuna rehen de", "The best writer in the world? John Green Beans"};
        tips.setText(tipsArray[random.nextInt(tipsArray.length)]);
    }
}
