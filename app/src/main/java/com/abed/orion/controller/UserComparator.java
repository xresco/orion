package com.abed.orion.controller;

import com.abed.orion.model.User;

import java.util.Comparator;

/**
 * Created by mindvalley on 29/05/2016.
 */
public class UserComparator implements Comparator<User> {

    boolean is_ascending;

    public UserComparator(boolean is_ascending) {
        this.is_ascending = is_ascending;
    }

    @Override
    public int compare(User o1, User o2) {
        if (is_ascending)
            return o1.getName().compareTo(o2.getName());
        else
            return o2.getName().compareTo(o1.getName());

    }
}