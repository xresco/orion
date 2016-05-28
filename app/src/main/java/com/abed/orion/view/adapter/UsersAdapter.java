package com.abed.orion.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abed.orion.R;
import com.abed.orion.model.User;

import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {
    private List<User> users;
    private ViewHolderClicks clicksListener;

    public UsersAdapter(List<User> users,
                        ViewHolderClicks clicksListener) {
        this.users = users;
        this.clicksListener = clicksListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (users == null || users.size() == 0) {
            return;
        }
        holder.setUser(users.get(position));

    }

    @Override
    public int getItemCount() {
        if (users == null) {
            return 0;
        }

        return users.size();
    }

    public void updateList(List<User> itemsList) {
        this.users = itemsList;
        notifyDataSetChanged();
    }

    public void addToList(List<User> itemsList) {
        this.users.addAll(itemsList);
        notifyDataSetChanged();
    }

    public void clear() {
        this.users.clear();
        notifyDataSetChanged();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView txt_body;
        ViewHolderClicks clicksListener;
        User User;

        public CustomViewHolder(View view, ViewHolderClicks clicksListener) {
            super(view);
            this.clicksListener = clicksListener;
            txt_body = (TextView) view.findViewById(R.id.txt_body);
            view.setOnClickListener(this);
        }


        public void setUser(User User) {
            this.User = User;
            txt_body.setText(User.getName());
        }

        @Override
        public void onClick(View v) {
            if (clicksListener != null)
                this.clicksListener.onStorySelected(v, getLayoutPosition(), User);
        }
    }


    public interface ViewHolderClicks {
        void onStorySelected(View view, int position, User User);
    }
}
