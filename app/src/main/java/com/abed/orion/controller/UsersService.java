package com.abed.orion.controller;

import com.abed.orion.model.User;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Retrofit Service.
 * Here we list all the apis related to the users.
 */
public interface UsersService {
    @GET("/users")
    Observable<List<User>> getUsers();
}

