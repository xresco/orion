package com.abed.orion.view.fragment;

/**
 * Created by mindvalley on 23/05/2016.
 */


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abed.orion.R;
import com.abed.orion.model.User;
import com.abed.orion.view.activity.UserDetailsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserDetailsFragment extends Fragment {
    private User User;

    @Bind(R.id.txt_website)
    TextView txt_website;

    @Bind(R.id.txt_city)
    TextView txt_city;

    @Bind(R.id.txt_email)
    TextView txt_email;

    @Bind(R.id.txt_phone)
    TextView txt_phone;

    @Bind(R.id.txt_name)
    TextView txt_name;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, rootView);
        User = (User) getActivity().getIntent().getExtras().getSerializable(UserDetailsActivity.User);
        if (User != null) {
            collapsingToolbar.setTitle(User.getName());
            txt_name.setText(User.getName());
            txt_phone.setText(User.getPhone());
            txt_city.setText("City: " + User.getAddress().getCity());
            txt_email.setText("Email: " + User.getEmail());
            txt_website.setText("Website: " + User.getWebsite());
        }
        return rootView;
    }



}