package com.example.know_your_alumni;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DashboardFragment extends Fragment {

    private CardView cv_home, cv_jobportal, cv_chatbot, cv_create_a_post, cv_events, cv_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //intializing  the cardviews
        cv_home = view.findViewById(R.id.home_cardview);
        cv_jobportal = view.findViewById(R.id.jobportal_cardview);
        cv_chatbot = view.findViewById(R.id.chatbot_cardview);
        cv_create_a_post = view.findViewById(R.id.create_cardview);
        cv_events = view.findViewById(R.id.events_cardview);
        cv_search = view.findViewById(R.id.search_cardview);

        cv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);
            }
        });

        cv_jobportal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),JobportalActivity.class);
                startActivity(intent);
            }
        });

        cv_chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ChatbotActivity.class);
                startActivity(intent);
            }
        });

        cv_create_a_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PostActivity.class);
                startActivity(intent);
            }
        });

        cv_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EventsActivity.class);
                startActivity(intent);
            }
        });

        cv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}