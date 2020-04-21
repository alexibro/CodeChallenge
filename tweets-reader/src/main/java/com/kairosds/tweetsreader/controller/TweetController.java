package com.kairosds.tweetsreader.controller;

import com.kairosds.tweetsreader.model.Tweet;
import io.swagger.annotations.*;

import javax.validation.Valid;
import java.util.List;

@Api
public interface TweetController {

    @ApiOperation(value = "Return the list of filter Tweets")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Filter Tweets")})
    List<Tweet> getTweets();

    @ApiOperation(value = "Return a filter Tweet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Filter Tweet"),
            @ApiResponse(code = 404, message = "Tweet not found")})
    Tweet getTweet(
            @ApiParam(required = true) @Valid long id);

    @ApiOperation(value = "Validate a Tweet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Validated Tweet"),
            @ApiResponse(code = 404, message = "Tweet not found")})
    Tweet validateTweet(
            @ApiParam(required = true) @Valid long id);

    @ApiOperation(value = "Return the list of the validated Tweets by user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Validated Tweets")})
    List<Tweet> getValidatedTweetsByUser(
            @ApiParam(required = true) @Valid String user);

    @ApiOperation(value = "Return the list of the most used Hashtags")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Top Hashtags")})
    List<String> topHashtags(
            @ApiParam() @Valid Integer limit);
}
