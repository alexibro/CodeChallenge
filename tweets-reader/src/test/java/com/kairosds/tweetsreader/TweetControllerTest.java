package com.kairosds.tweetsreader;

import com.google.gson.Gson;
import com.kairosds.tweetsreader.model.Tweet;
import com.kairosds.tweetsreader.service.impl.TweetServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TweetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TweetServiceImpl TweetServiceImpl;

    private final String url = "/api/tweet";

    private Gson jsonParser = new Gson();

    @Before
    public void initialize() {
        Tweet tweet = new Tweet(1L, "Test", "Test", "Test");
        List<String> hashtags = new ArrayList<>();
        hashtags.add("Test");
        tweet.setHashtags(hashtags);

        Tweet validatedTweet = new Tweet(1L, "Test", "Test", "Test");
        validatedTweet.validate();

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet);

        List<Tweet> validatedTweets = new ArrayList<>();
        validatedTweets.add(validatedTweet);

        given(TweetServiceImpl.getTweets()).willReturn(tweets);
        given(TweetServiceImpl.getTweet(1)).willReturn(Optional.of(tweet));
        given(TweetServiceImpl.getValidatedTweetsByUser("Test")).willReturn(validatedTweets);
        given(TweetServiceImpl.validateTweet(1)).willReturn(Optional.of(validatedTweet));
        given(TweetServiceImpl.topHashtags(1)).willReturn(hashtags);
        given(TweetServiceImpl.save(any(Tweet.class))).willReturn(tweet);
        given(TweetServiceImpl.save(any(Status.class))).willReturn(tweet);
    }

    @Test
    public void shouldGetTweets() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text", is("Test")));
    }

    @Test
    public void shouldGetTweet() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Test")));
    }

    @Test
    public void shouldValidateTweet() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put(url + "/1/validate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Test")));
    }

    @Test
    public void shouldGetValidatedTweetsByUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url + "/validated?user=Test")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text", is("Test")));
    }

    @Test
    public void shouldGetMostUsedHashtags() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(url + "/hashtags?limit=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
