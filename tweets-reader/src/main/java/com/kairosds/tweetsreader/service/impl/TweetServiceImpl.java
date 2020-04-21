package com.kairosds.tweetsreader.service.impl;

import com.kairosds.tweetsreader.model.Tweet;
import com.kairosds.tweetsreader.persistance.TweetRepository;
import com.kairosds.tweetsreader.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    private FilterQuery filter = new FilterQuery();

    public List<Tweet> getTweets() {
        return this.tweetRepository.findAll();
    }

    public Optional<Tweet> getTweet(long id) {
        return this.tweetRepository.findById(id);
    }

    public Optional<Tweet> validateTweet(long id) {
        Optional<Tweet> tweet = this.getTweet(id);
        if (tweet.isPresent()) {
            tweet.get().validate();
            return Optional.of(this.save(tweet.get()));
        }
        return Optional.empty();
    }

    public List<Tweet> getValidatedTweetsByUser(String user) {
        return this.tweetRepository.findByUserAndValidatedTrue(user);
    }

    public List<String> topHashtags(int limit) {
        return this.tweetRepository.findTopHashtags(limit);
    }

    // Convert Status (Twitter4j tweet) to Tweet entity and save it
    public Tweet save(Status status) {
        String geoLocation = status.getGeoLocation() == null ? "" : status.getGeoLocation().toString();
        Tweet tweet = new Tweet(status.getId(), status.getUser().getName(), status.getText(), geoLocation);
        tweet.setHashtags(
                Arrays.stream(status.getHashtagEntities())
                        .map(HashtagEntity::getText)
                        .collect(Collectors.toList()));
        return this.tweetRepository.save(tweet);
    }

    public Tweet save(Tweet tweet) {
        return this.tweetRepository.save(tweet);
    }

}
