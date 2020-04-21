package com.kairosds.tweetsreader.controller;

import com.kairosds.tweetsreader.model.Tweet;

import java.util.List;

public interface TweetController {

    List<Tweet> getTweets();

    List<Tweet> getValidatedTweetsByUser(String user);

    Tweet getTweet(long id);

    Tweet validateTweet(long id);

    List<String> topHashtags(Integer limit);
}
