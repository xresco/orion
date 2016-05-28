
package com.abed.orion.model;

import java.io.Serializable;

public class Company implements Serializable {

    private String name;
    private String catchPhrase;
    private String bs;

    /**
     * No args constructor for use in serialization
     */
    public Company() {
    }

    /**
     * @param catchPhrase
     * @param name
     * @param bs
     */
    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The catchPhrase
     */
    public String getCatchPhrase() {
        return catchPhrase;
    }

    /**
     * @param catchPhrase The catchPhrase
     */
    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    /**
     * @return The bs
     */
    public String getBs() {
        return bs;
    }

    /**
     * @param bs The bs
     */
    public void setBs(String bs) {
        this.bs = bs;
    }

}
