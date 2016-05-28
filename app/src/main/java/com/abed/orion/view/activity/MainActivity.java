package com.abed.orion.view.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.abed.orion.R;
import com.abed.orion.controller.API_Utils;
import com.abed.orion.controller.RxBus;
import com.abed.orion.controller.UserComparator;
import com.abed.orion.model.User;
import com.abed.orion.model.eventBuses.UsersLoadedEvent;
import com.abed.orion.view.adapter.UsersAdapter;
import com.abed.orion.view.misc.VerticalEqualSpaceItemDecoration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rx.Subscription;

/**
 * The main activity where all the users are listed
 */
public class MainActivity extends AppCompatActivity implements UsersAdapter.ViewHolderClicks {
    private final String TAG = getClass().getName();

    private Subscription storiesLoadingBusSubscription;
    private List<User> users;


    private UsersAdapter usersAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        initRx();
        API_Utils.loadUsers();
    }

    private void loadViews() {
        usersAdapter = new UsersAdapter(new LinkedList<>(), this);
        recyclerView = (RecyclerView) findViewById(R.id.rvItems);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalEqualSpaceItemDecoration(2));


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(() -> {
            usersAdapter.clear();
            API_Utils.loadUsers();
        });

        swipeContainer.post(() -> swipeContainer.setRefreshing(true));


    }

    private void initRx() {
        storiesLoadingBusSubscription = RxBus.getInstance()
                .register(UsersLoadedEvent.class,
                        event -> {
                            users = event.getusers();
                            usersAdapter.updateList(users);
                            swipeContainer.post(() -> swipeContainer.setRefreshing(false));

                        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        storiesLoadingBusSubscription.unsubscribe();
    }

    @Override
    public void onStorySelected(View view, int position, User User) {
        UserDetailsActivity.startActivity(this, User);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_sort_a_z:
                sortItems(true);
                return true;
            case R.id.item_sort_z_a:
                sortItems(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void sortItems(boolean is_ascending) {
        Collections.sort(users, new UserComparator(is_ascending));
        usersAdapter.updateList(users);
    }
}
