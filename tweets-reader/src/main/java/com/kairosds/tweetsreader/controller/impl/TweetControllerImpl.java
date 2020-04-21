package com.kairosds.tweetsreader.controller.impl;

import com.kairosds.tweetsreader.model.Tweet;
import com.kairosds.tweetsreader.controller.TweetController;
import com.kairosds.tweetsreader.service.impl.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/tweet")
public class TweetControllerImpl implements TweetController {

    @Autowired
    private TweetServiceImpl tweetService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Tweet> getTweets() {
        return this.tweetService.getTweets();
    }

    @GetMapping("/validated")
    public List<Tweet> getValidatedTweetsByUser(@RequestParam String user) {
        return this.tweetService.getValidatedTweetsByUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tweet getTweet(@PathVariable long id) {
        Optional<Tweet> tweet = this.tweetService.getTweet(id);
        if(!tweet.isPresent()) throw new NoSuchElementException();

        return tweet.get();
    }

    @PutMapping("/{id}/validate")
    @ResponseStatus(HttpStatus.OK)
    public Tweet validateTweet(@PathVariable long id) {
        Optional<Tweet> tweet = this.tweetService.validateTweet(id);
        if(!tweet.isPresent()) throw new NoSuchElementException();

        return tweet.get();
    }

    @GetMapping("/hashtags")
    @ResponseStatus(HttpStatus.OK)
    public List<String> topHashtags(@RequestParam(required = false) Integer limit) {
        if (limit == null) limit = 10;
        return this.tweetService.topHashtags(limit);
    }
}
