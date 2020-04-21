package com.kairosds.tweetsreader.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tweet {

    @Id
    private long id;

    private String user;
    private String text;
    private String geoLocation;
    private boolean validated;

    @ElementCollection
    private List<String> hashtags;

    public Tweet(){}

    public Tweet(long id, String user, String text, String geoLocation) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.geoLocation = geoLocation;
        this.hashtags = new ArrayList<>();
    }

    public void validate() {
        this.validated = true;
    }

    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public boolean isValidated() {
        return validated;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
