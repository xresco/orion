package com.abed.orion.model.eventBuses;

import com.abed.orion.model.User;

import java.util.List;


public class UsersLoadedEvent {

    private List<User> users;

    public UsersLoadedEvent(List<User> users) {
        this.users = users;
    }

    public List<User> getusers() {
        return users;
    }
}
