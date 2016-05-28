
package com.abed.orion.model;

import java.io.Serializable;

public class Geo implements Serializable {

    private String lat;
    private String lng;

    /**
     * No args constructor for use in serialization
     */
    public Geo() {
    }

    /**
     * @param lng
     * @param lat
     */
    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    /**
     * @return The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * @return The lng
     */
    public String getLng() {
        return lng;
    }

    /**
     * @param lng The lng
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

}
