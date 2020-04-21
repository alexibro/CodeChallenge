package com.kairosds.tweetsreader.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(description = "Tweet")
public class Tweet {

    @Id
    @ApiModelProperty(notes = "The Tweet ID")
    private long id;

    @ApiModelProperty(notes = "The Tweet writer")
    private String user;
    @ApiModelProperty(notes = "The Tweet text (Tweet content)")
    private String text;
    @ApiModelProperty(notes = "The location from which the Tweet was written")
    private String geoLocation;
    @ApiModelProperty(notes = "Indicates if the Tweet has been validated")
    private boolean validated;

    @ElementCollection
    @ApiModelProperty(notes = "The Hashtags contained in the Tweet")
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
