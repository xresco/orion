package com.abed.orion.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abed.orion.R;
import com.abed.orion.model.User;

public class UserDetailsActivity extends AppCompatActivity {

    public static final String User = "com.abed.orion.view.activity.User";

    public static void startActivity(Context context, User user) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(User, user);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

}

