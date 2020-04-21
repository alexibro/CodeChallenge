package com.kairosds.tweetsreader.service;

import com.kairosds.tweetsreader.model.Tweet;
import twitter4j.Status;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> getTweets();

    List<Tweet> getValidatedTweetsByUser(String user);

    Optional<Tweet> getTweet(long id);

    Optional<Tweet> validateTweet(long id);

    List<String> topHashtags(int limit);

    Tweet save(Status status);

    Tweet save(Tweet tweet);

}
